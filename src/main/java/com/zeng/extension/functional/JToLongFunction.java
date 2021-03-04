package com.zeng.extension.functional;

import java.util.function.ToLongFunction;

/**
 * @author JJ_yo
 * @title: 自定义函数式接口
 * @description: 自定义函数式接口，定义一个与ToLongFunction同样的方法，在实际使用中，我们可以使用此方法代替ToLongFunction中的applyAsLong方法
 * @date 2021-03-01 14:07
 * @version: 1.0
 */
@FunctionalInterface
public interface JToLongFunction<T> {

    /**
     * 与ToLongFunction同样的方法，可抛异常
     *
     * @param value
     * @return
     */
    long applyAsLong(T value) throws Exception;

    /****************************************************************************************************/

    /**
     * 对JToLongFunction进行封装，返回一个ToLongFunction，内部将编译异常转成运行时异常
     *
     * @param jToLongFunction 自定义函数式接口
     * @param <T>
     * @return
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
     * @param description      业务描述
     * @param <T>
     * @return
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
