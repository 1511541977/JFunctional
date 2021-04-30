package com.jazng.extension.functional;

import java.util.function.UnaryOperator;

/**
 * @author JJ_yo
 * 自定义函数式接口
 * 自定义函数式接口，继承JFunction，泛型都为同一个类型，即T apply(T t)
 * 2021-03-01 09:59
 * @version: 1.0
 */
@FunctionalInterface
public interface JUnaryOperator<T> extends JFunction<T, T> {

    /**
     * 与UnaryOperator相同的方法，接口为自定义
     *
     * @param <T> 泛型
     * @return 返回值
     */
    static <T> JUnaryOperator<T> identity() {
        return t -> t;
    }

    /****************************************************************************************************/

    /**
     * 对JUnaryOperator进行封装，返回一个UnaryOperator，内部将编译异常转成运行时异常
     *
     * @param jUnaryOperator 自定义函数式接口
     * @param <T>            泛型
     * @return 返回值
     */
    static <T> UnaryOperator<T> allowThrowException(JUnaryOperator<T> jUnaryOperator) {
        return t -> {
            try {
                return jUnaryOperator.apply(t);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JUnaryOperator进行封装，返回一个UnaryOperator，内部将编译异常转成运行时异常
     *
     * @param jUnaryOperator 自定义函数式接口
     * @param description    业务描述
     * @param <T>            泛型
     * @return 返回值
     */
    static <T> UnaryOperator<T> allowThrowException(JUnaryOperator<T> jUnaryOperator, String description) {
        return t -> {
            try {
                return jUnaryOperator.apply(t);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
