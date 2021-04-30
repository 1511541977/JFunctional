package com.jazng.extension.functional;

import java.util.function.ToLongFunction;

/**
 * @author JJ_yo
 * 自定义函数式接口
 * 自定义函数式接口，定义一个与ToLongFunction同样的方法，在实际使用中，我们可以使用此方法代替ToLongFunction中的applyAsLong方法
 * 2021-03-01 14:07
 * @version: 1.0
 */
@FunctionalInterface
public interface JToLongFunction<T> {

    /**
     * 与ToLongFunction同样的方法，可抛异常
     *
     * @param value 参数
     * @return 返回值\
     * @throws Exception 异常
     */
    long applyAsLong(T value) throws Exception;

    /****************************************************************************************************/

    /**
     * 对JToLongFunction进行封装，返回一个ToLongFunction，内部将编译异常转成运行时异常
     *
     * @param jToLongFunction 自定义函数式接口
     * @param <T>             泛型
     * @return 返回值
     */
    static <T> ToLongFunction<T> allowThrowException(JToLongFunction<T> jToLongFunction) {
        return t -> {
            try {
                return jToLongFunction.applyAsLong(t);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JToLongFunction进行封装，返回一个ToLongFunction，内部将编译异常转成运行时异常
     *
     * @param jToLongFunction 自定义函数式接口
     * @param description     业务描述
     * @param <T>             泛型
     * @return 返回值
     */
    static <T> ToLongFunction<T> allowThrowException(JToLongFunction<T> jToLongFunction, String description) {
        return t -> {
            try {
                return jToLongFunction.applyAsLong(t);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
