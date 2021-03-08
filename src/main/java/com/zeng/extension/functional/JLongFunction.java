package com.zeng.extension.functional;

import java.util.function.LongFunction;

/**
 * @author JJ_yo
 * 自定义函数式接口
 * 自定义函数式接口，定义一个与LongFunction同样的方法，在实际使用中，我们可以使用此方法代替LongFunction中的apply方法
 * 2021-03-01 14:45
 * @version: 1.0
 */
@FunctionalInterface
public interface JLongFunction<R> {

    /**
     * 与LongFunction同样的方法，可抛异常
     *
     * @param value 参数
     * @return 返回值
     * @throws Exception 异常
     */
    R apply(long value) throws Exception;

    /****************************************************************************************************/

    /**
     * 对JLongFunction进行封装，返回一个LongFunction，内部将编译异常转成运行时异常
     *
     * @param jLongFunction 自定义函数式接口
     * @param <R>           泛型
     * @return 返回值
     */
    static <R> LongFunction<R> allowThrowException(JLongFunction<R> jLongFunction) {
        return value -> {
            try {
                return jLongFunction.apply(value);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JLongFunction进行封装，返回一个LongFunction，内部将编译异常转成运行时异常
     *
     * @param jLongFunction 自定义函数式接口
     * @param description   业务描述
     * @param <R>           泛型
     * @return 返回值
     */
    static <R> LongFunction<R> allowThrowException(JLongFunction<R> jLongFunction, String description) {
        return value -> {
            try {
                return jLongFunction.apply(value);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
