package com.zeng.extension.functional;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author JJ_yo
 * 自定义函数式接口
 * 自定义函数式接口，定义一个与Function同样的方法，在实际使用中，我们可以使用此方法代替Function中的apply方法
 * 2021-02-25 15:33
 * @version: 1.0
 */
@FunctionalInterface
public interface JFunction<T, R> {

    /**
     * 与Function同样的方法，可抛异常
     *
     * @param t 参数
     * @return 返回值
     * @throws Exception 异常
     */
    R apply(T t) throws Exception;

    /**
     * 与Function同样的方法，接口为自定义
     *
     * @param before 参数
     * @param <V>    泛型
     * @return 返回值
     */
    default <V> JFunction<V, R> compose(JFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * 与Function同样的方法，接口为自定义
     *
     * @param after 参数
     * @param <V>   泛型
     * @return 返回值
     */
    default <V> JFunction<T, V> andThen(JFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * 与Function同样的方法，接口为自定义
     *
     * @param <T> 泛型
     * @return 返回值
     */
    static <T> JFunction<T, T> identity() {
        return t -> t;
    }

    /****************************************************************************************************/

    /**
     * 对JFunction进行封装，返回一个Function，内部将编译异常转成运行时异常
     *
     * @param jFunction 自定义函数式接口
     * @param <T>       泛型
     * @param <R>       泛型
     * @return 返回值
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
     * @param jFunction   自定义函数式接口
     * @param description 异常所需信息
     * @param <T>         泛型
     * @param <R>         泛型
     * @return 返回值
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
