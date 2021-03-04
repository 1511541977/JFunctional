package com.zeng.extension.functional;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author JJ_yo
 * @title: 自定义函数式接口
 * @description: 自定义函数式接口，定义一个与Consumer同样的方法，在实际使用中，我们可以使用此方法代替Consumer中的accept方法
 * @date 2021-02-25 15:28
 * @version: 1.0
 */
@FunctionalInterface
public interface JConsumer<T> {

    /**
     * 与Consumer同样的方法，可抛异常
     *
     * @param t
     * @throws Exception
     */
    void accept(T t) throws Exception;

    /**
     * 与Consumer同样的方法，接口为自定义
     *
     * @param after
     * @return
     */
    default JConsumer<T> andThen(JConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> {
            accept(t);
            after.accept(t);
        };
    }

    /****************************************************************************************************/

    /**
     * 对JConsumer进行封装，返回一个Consumer，内部将编译异常转成运行时异常
     *
     * @param jConsumer 自定义函数式接口
     * @param <T>
     * @return
     */
    static <T> Consumer<T> allowThrowException(JConsumer<T> jConsumer) {
        return t -> {
            try {
                jConsumer.accept(t);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JConsumer进行封装，返回一个Consumer，内部将编译异常转成运行时异常
     *
     * @param jConsumer  自定义函数式接口
     * @param description 业务描述
     * @param <T>
     * @return
     */
    static <T> Consumer<T> allowThrowException(JConsumer<T> jConsumer, String description) {
        return t -> {
            try {
                jConsumer.accept(t);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
