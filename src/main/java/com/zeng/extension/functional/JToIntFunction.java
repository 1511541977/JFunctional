package com.zeng.extension.functional;

import java.util.function.ToIntFunction;

/**
 * @author JJ_yo
 * @title: 自定义函数式接口
 * @description: 自定义函数式接口，定义一个与ToIntFunction同样的方法，在实际使用中，我们可以使用此方法代替ToIntFunction中的applyAsInt方法
 * @date 2021-03-01 11:41
 * @version: 1.0
 */
@FunctionalInterface
public interface JToIntFunction<T> {

    /**
     * 与ToIntFunction同样的方法，可抛异常
     *
     * @param value
     * @return
     */
    int applyAsInt(T value) throws Exception;

    /****************************************************************************************************/

    /**
     * 对JToIntFunction进行封装，返回一个ToIntFunction，内部将编译异常转成运行时异常
     *
     * @param jToIntFunction 自定义函数式接口
     * @param <T>
     * @return
     */
    static <T> ToIntFunction<T> allowThrowException(JToIntFunction<T> jToIntFunction) {
        return t -> {
            try {
                return jToIntFunction.applyAsInt(t);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JToIntFunction进行封装，返回一个ToIntFunction，内部将编译异常转成运行时异常
     *
     * @param jToIntFunction 自定义函数式接口
     * @param description     业务描述
     * @param <T>
     * @return
     */
    static <T> ToIntFunction<T> allowThrowException(JToIntFunction<T> jToIntFunction, String description) {
        return t -> {
            try {
                return jToIntFunction.applyAsInt(t);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
