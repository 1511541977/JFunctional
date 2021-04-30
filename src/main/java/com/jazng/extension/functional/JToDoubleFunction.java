package com.jazng.extension.functional;

import java.util.function.ToDoubleFunction;

/**
 * @author JJ_yo
 * 自定义函数式接口
 * 自定义函数式接口，定义一个与ToDoubleFunction同样的方法，在实际使用中，我们可以使用此方法代替ToDoubleFunction中的applyAsDouble方法
 * 2021-03-01 14:21
 * @version: 1.0
 */
@FunctionalInterface
public interface JToDoubleFunction<T> {

    /**
     * 与ToDoubleFunction同样的方法，可抛异常
     *
     * @param value 参数
     * @return 返回值
     * @throws Exception 异常
     */
    double applyAsDouble(T value) throws Exception;

    /****************************************************************************************************/

    /**
     * 对JToDoubleFunction进行封装，返回一个ToDoubleFunction，内部将编译异常转成运行时异常
     *
     * @param jToDoubleFunction 自定义函数式接口
     * @param <T>               泛型
     * @return 返回值
     */
    static <T> ToDoubleFunction<T> allowThrowException(JToDoubleFunction<T> jToDoubleFunction) {
        return t -> {
            try {
                return jToDoubleFunction.applyAsDouble(t);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JToDoubleFunction进行封装，返回一个ToDoubleFunction，内部将编译异常转成运行时异常
     *
     * @param jToDoubleFunction 自定义函数式接口
     * @param description       业务描述
     * @param <T>               泛型
     * @return 返回值
     */
    static <T> ToDoubleFunction<T> allowThrowException(JToDoubleFunction<T> jToDoubleFunction, String description) {
        return t -> {
            try {
                return jToDoubleFunction.applyAsDouble(t);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
