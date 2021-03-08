package com.zeng.extension.functional;

import java.util.function.DoubleFunction;

/**
 * @author JJ_yo
 * 自定义函数式接口
 * 自定义函数式接口，定义一个与DoubleFunction同样的方法，在实际使用中，我们可以使用此方法代替DoubleFunction中的apply方法
 * 2021-03-01 14:57
 * @version: 1.0
 */
@FunctionalInterface
public interface JDoubleFunction<R> {

    /**
     * 与DoubleFunction同样的方法，可抛异常
     *
     * @param value 参数
     * @return 返回值
     * @throws Exception 异常
     */
    R apply(double value) throws Exception;

    /****************************************************************************************************/

    /**
     * 对JDoubleFunction进行封装，返回一个DoubleFunction，内部将编译异常转成运行时异常
     *
     * @param jDoubleFunction 自定义函数式接口
     * @param <R>             泛型
     * @return 返回值
     */
    static <R> DoubleFunction<R> allowThrowException(JDoubleFunction<R> jDoubleFunction) {
        return value -> {
            try {
                return jDoubleFunction.apply(value);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JDoubleFunction进行封装，返回一个DoubleFunction，内部将编译异常转成运行时异常
     *
     * @param jDoubleFunction 自定义函数式接口
     * @param description     业务描述
     * @param <R>             泛型
     * @return 返回值
     */
    static <R> DoubleFunction<R> allowThrowException(JDoubleFunction<R> jDoubleFunction, String description) {
        return value -> {
            try {
                return jDoubleFunction.apply(value);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
