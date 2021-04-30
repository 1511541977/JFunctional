package com.jazng.extension.functional;

import java.util.function.ToDoubleBiFunction;

/**
 * @author JJ_yo
 * 自定义函数式接口
 * 自定义函数式接口，定义一个与ToDoubleBiFunction同样的方法，在实际使用中，我们可以使用此方法代替ToDoubleBiFunction中的applyAsDouble方法
 * 2021-03-01 14:27
 * @version: 1.0
 */
@FunctionalInterface
public interface JToDoubleBiFunction<T, U> {

    /**
     * 与ToDoubleBiFunction同样的方法，可抛异常
     *
     * @param t 参数
     * @param u 参数
     * @return 返回值
     * @throws Exception 异常
     */
    double applyAsDouble(T t, U u) throws Exception;

    /****************************************************************************************************/

    /**
     * 对JToDoubleBiFunction进行封装，返回一个ToDoubleBiFunction，内部将编译异常转成运行时异常
     *
     * @param jToDoubleBiFunction 自定义函数式接口
     * @param <T>                 泛型
     * @param <U>                 泛型
     * @return 返回值
     */
    static <T, U> ToDoubleBiFunction<T, U> allowThrowException(JToDoubleBiFunction<T, U> jToDoubleBiFunction) {
        return (t, u) -> {
            try {
                return jToDoubleBiFunction.applyAsDouble(t, u);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JToDoubleBiFunction进行封装，返回一个ToDoubleBiFunction，内部将编译异常转成运行时异常
     *
     * @param jToDoubleBiFunction 自定义函数式接口
     * @param description         业务描述
     * @param <T>                 泛型
     * @param <U>                 泛型
     * @return 返回值
     */
    static <T, U> ToDoubleBiFunction<T, U> allowThrowException(JToDoubleBiFunction<T, U> jToDoubleBiFunction, String description) {
        return (t, u) -> {
            try {
                return jToDoubleBiFunction.applyAsDouble(t, u);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
