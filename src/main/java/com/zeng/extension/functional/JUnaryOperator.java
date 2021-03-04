package com.zeng.extension.functional;

import java.util.function.UnaryOperator;

/**
 * @author JJ_yo
 * @title: 自定义函数式接口
 * @description: 自定义函数式接口，继承JFunction，泛型都为同一个类型，即T apply(T t)
 * @date 2021-03-01 09:59
 * @version: 1.0
 */
@FunctionalInterface
public interface JUnaryOperator<T> extends JFunction<T, T> {

    /**
     * 与UnaryOperator相同的方法，接口为自定义
     *
     * @param <T>
     * @return
     */
    static <T> JUnaryOperator<T> identity() {
        return t -> t;
    }

    /****************************************************************************************************/

    /**
     * 对JUnaryOperator进行封装，返回一个UnaryOperator，内部将编译异常转成运行时异常
     *
     * @param jUnaryOperator 自定义函数式接口
     * @param <T>
     * @return
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
     * @param description     业务描述
     * @param <T>
     * @return
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
