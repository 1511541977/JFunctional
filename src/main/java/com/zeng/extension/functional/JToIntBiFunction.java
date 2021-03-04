package com.zeng.extension.functional;

import java.util.function.ToIntBiFunction;

/**
 * @author JJ_yo
 * @title: 自定义函数式接口
 * @description: 自定义函数式接口，定义一个与ToIntBiFunction同样的方法，在实际使用中，我们可以使用此方法代替ToIntBiFunction中的applyAsInt方法
 * @date 2021-03-01 13:43
 * @version: 1.0
 */
@FunctionalInterface
public interface JToIntBiFunction<T, U> {

    /**
     * 与ToIntBiFunction同样的方法，可抛异常
     *
     * @param t
     * @param u
     * @return
     * @throws Exception
     */
    int applyAsInt(T t, U u) throws Exception;

    /****************************************************************************************************/

    /**
     * 对JToIntBiFunction进行封装，返回一个ToIntBiFunction，内部将编译异常转成运行时异常
     *
     * @param jToIntBiFunction 自定义函数式接口
     * @param <T>
     * @param <U>
     * @return
     */
    static <T, U> ToIntBiFunction<T, U> allowThrowException(JToIntBiFunction<T, U> jToIntBiFunction) {
        return (t, u) -> {
            try {
                return jToIntBiFunction.applyAsInt(t, u);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JToIntBiFunction进行封装，返回一个ToIntBiFunction，内部将编译异常转成运行时异常
     *
     * @param jToIntBiFunction 自定义函数式接口
     * @param description       业务描述
     * @param <T>
     * @param <U>
     * @return
     */
    static <T, U> ToIntBiFunction<T, U> allowThrowException(JToIntBiFunction<T, U> jToIntBiFunction, String description) {
        return (t, u) -> {
            try {
                return jToIntBiFunction.applyAsInt(t, u);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
