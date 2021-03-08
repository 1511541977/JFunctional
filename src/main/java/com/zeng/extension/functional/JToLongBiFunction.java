package com.zeng.extension.functional;

import java.util.function.ToLongBiFunction;

/**
 * @author JJ_yo
 * 自定义函数式接口
 * 自定义函数式接口，定义一个与ToLongBiFunction同样的方法，在实际使用中，我们可以使用此方法代替ToLongBiFunction中的applyAsLong方法
 * 2021-03-01 14:15
 * @version: 1.0
 */
@FunctionalInterface
public interface JToLongBiFunction<T, U> {

    /**
     * 与ToLongBiFunction同样的方法，可抛异常
     *
     * @param t 参数
     * @param u 参数
     * @return 返回值
     * @throws Exception 异常
     */
    long applyAsLong(T t, U u) throws Exception;

    /****************************************************************************************************/

    /**
     * 对JToLongBiFunction进行封装，返回一个JToLongBiFunction，内部将编译异常转成运行时异常
     *
     * @param jToLongBiFunction 自定义函数式接口
     * @param <T>               泛型
     * @param <U>               泛型
     * @return 返回值
     */
    static <T, U> ToLongBiFunction<T, U> allowThrowException(JToLongBiFunction<T, U> jToLongBiFunction) {
        return (t, u) -> {
            try {
                return jToLongBiFunction.applyAsLong(t, u);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JToLongBiFunction进行封装，返回一个JToLongBiFunction，内部将编译异常转成运行时异常
     *
     * @param jToLongBiFunction 自定义函数式接口
     * @param description       业务描述
     * @param <T>               泛型
     * @param <U>               泛型
     * @return 返回值
     */
    static <T, U> ToLongBiFunction<T, U> allowThrowException(JToLongBiFunction<T, U> jToLongBiFunction, String description) {
        return (t, u) -> {
            try {
                return jToLongBiFunction.applyAsLong(t, u);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
