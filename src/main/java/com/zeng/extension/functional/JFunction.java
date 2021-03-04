package com.zeng.extension.functional;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author JJ_yo
 * @title: 自定义函数式接口
 * @description: 自定义函数式接口，定义一个与Function同样的方法，在实际使用中，我们可以使用此方法代替Function中的apply方法
 * @date 2021-02-25 15:33
 * @version: 1.0
 */
@FunctionalInterface
public interface JFunction<T, R> {

    /**
     * 与Function同样的方法，可抛异常
     *
     * @param t
     * @return
     * @throws Exception
     */
    R apply(T t) throws Exception;

    /**
     * 与Function同样的方法，接口为自定义
     *
     * @param before
     * @param <V>
     * @return
     */
    default <V> JFunction<V, R> compose(JFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * 与Function同样的方法，接口为自定义
     *
     * @param after
     * @param <V>
     * @return
     */
    default <V> JFunction<T, V> andThen(JFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * 与Function同样的方法，接口为自定义
     *
     * @param <T>
     * @return
     */
    static <T> JFunction<T, T> identity() {
        return t -> t;
    }

    /****************************************************************************************************/

    /**
     * 对JFunction进行封装，返回一个Function，内部将编译异常转成运行时异常
     *
     * @param jFunction 自定义函数式接口
     * @param <T>
     * @param <R>
     * @return
     */
    static <T, R> Function<T, R> allowThrowException(JFunction<T, R> jFunction) {
        return t -> {
            try {
                return jFunction.apply(t);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JFunction进行封装，返回一个Function，内部将编译异常转成运行时异常
     *
     * @param jFunction  自定义函数式接口
     * @param description 异常所需信息
     * @param <T>
     * @param <R>
     * @return
     */
    static <T, R> Function<T, R> allowThrowException(JFunction<T, R> jFunction, String description) {
        return t -> {
            try {
                return jFunction.apply(t);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
