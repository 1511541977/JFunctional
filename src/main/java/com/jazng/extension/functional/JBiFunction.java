package com.jazng.extension.functional;

import java.util.Objects;
import java.util.function.BiFunction;

/**
 * @author JJ_yo
 * 自定义函数式接口
 * 自定义函数式接口，定义一个与BiFunction同样的方法，在实际使用中，我们可以使用此方法代替BiFunction中的apply方法
 * 2021-03-01 09:36
 * @version: 1.0
 */
@FunctionalInterface
public interface JBiFunction<T, U, R> {

    /**
     * 与BiFunction同样的方法，可抛异常
     *
     * @param t 参数
     * @param u 参数
     * @return 返回值
     * @throws Exception 异常
     */
    R apply(T t, U u) throws Exception;

    /**
     * 与BiFunction同样的方法，接口为自定义
     *
     * @param after 参数
     * @param <V>   泛型
     * @return 返回值
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
     * @param <T>         泛型
     * @param <U>         泛型
     * @param <R>         泛型
     * @return 返回值
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
     * @param description 业务描述
     * @param <T>         泛型
     * @param <U>         泛型
     * @param <R>         泛型
     * @return 返回值
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
