package com.zeng.extension.functional;

import java.util.Objects;
import java.util.function.BiFunction;

/**
 * @author JJ_yo
 * @title: 自定义函数式接口
 * @description: 自定义函数式接口，定义一个与BiFunction同样的方法，在实际使用中，我们可以使用此方法代替BiFunction中的apply方法
 * @date 2021-03-01 09:36
 * @version: 1.0
 */
@FunctionalInterface
public interface JBiFunction<T, U, R> {

    /**
     * 与BiFunction同样的方法，可抛异常
     *
     * @param t
     * @param u
     * @return
     * @throws Exception
     */
    R apply(T t, U u) throws Exception;

    /**
     * 与BiFunction同样的方法，接口为自定义
     *
     * @param after
     * @param <V>
     * @return
     */
    default <V> JBiFunction<T, U, V> andThen(JFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.apply(apply(t, u));
    }

    /****************************************************************************************************/

    /**
     * 对JBiFunction进行封装，返回一个BiFunction，内部将编译异常转成运行时异常
     *
     * @param jBiFunction 自定义函数式接口
     * @param <T>
     * @param <U>
     * @param <R>
     * @return
     */
    static <T, U, R> BiFunction<T, U, R> allowThrowException(JBiFunction<T, U, R> jBiFunction) {
        return (t, u) -> {
            try {
                return jBiFunction.apply(t, u);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JBiFunction进行封装，返回一个BiFunction，内部将编译异常转成运行时异常
     *
     * @param jBiFunction 自定义函数式接口
     * @param description  业务描述
     * @param <T>
     * @param <U>
     * @param <R>
     * @return
     */
    static <T, U, R> BiFunction<T, U, R> allowThrowException(JBiFunction<T, U, R> jBiFunction, String description) {
        return (t, u) -> {
            try {
                return jBiFunction.apply(t, u);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
