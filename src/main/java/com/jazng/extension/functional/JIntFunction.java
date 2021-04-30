package com.jazng.extension.functional;

import java.util.function.IntFunction;

/**
 * @author JJ_yo
 * 自定义函数式接口
 * 自定义函数式接口，定义一个与IntFunction同样的方法，在实际使用中，我们可以使用此方法代替IntFunction中的apply方法
 * 2021-03-01 14:35
 * @version: 1.0
 */
@FunctionalInterface
public interface JIntFunction<R> {

    /**
     * 与IntFunction同样的方法，可抛异常
     *
     * @param value 参数
     * @return 返回值
     * @throws Exception 异常
     */
    R apply(int value) throws Exception;

    /****************************************************************************************************/

    /**
     * 对JIntFunction进行封装，返回一个IntFunction，内部将编译异常转成运行时异常
     *
     * @param jIntFunction 自定义函数式接口
     * @param <R>          泛型
     * @return 返回值
     */
    static <R> IntFunction<R> allowThrowException(JIntFunction<R> jIntFunction) {
        return value -> {
            try {
                return jIntFunction.apply(value);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JIntFunction进行封装，返回一个IntFunction，内部将编译异常转成运行时异常
     *
     * @param jIntFunction 自定义函数式接口
     * @param description  业务描述
     * @param <R>          泛型
     * @return 返回值
     */
    static <R> IntFunction<R> allowThrowException(JIntFunction<R> jIntFunction, String description) {
        return value -> {
            try {
                return jIntFunction.apply(value);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
