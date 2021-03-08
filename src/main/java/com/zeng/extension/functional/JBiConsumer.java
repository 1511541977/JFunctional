package com.zeng.extension.functional;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * @author JJ_yo
 * 自定义函数式接口
 * 自定义函数式接口，定义一个与BiConsumer同样的方法，在实际使用中，我们可以使用此方法代替BiConsumer中的accept方法
 * 2021-03-01 10:21
 * @version: 1.0
 */
@FunctionalInterface
public interface JBiConsumer<T, U> {

    /**
     * 与BiConsumer同样的方法，可抛异常
     *
     * @param t 参数
     * @param u 参数
     * @throws Exception 异常
     */
    void accept(T t, U u) throws Exception;

    /**
     * 与BiConsumer同样的方法，接口为自定义
     *
     * @param after 参数
     * @return 返回值
     */
    default JBiConsumer<T, U> andThen(JBiConsumer<? super T, ? super U> after) {
        Objects.requireNonNull(after);

        return (l, r) -> {
            accept(l, r);
            after.accept(l, r);
        };
    }

    /****************************************************************************************************/

    /**
     * 对JBiConsumer进行封装，返回一个BiConsumer，内部将编译异常转成运行时异常
     *
     * @param jBiConsumer 自定义函数式接口
     * @param <T>         泛型
     * @param <U>         泛型
     * @return 返回值
     */
    static <T, U> BiConsumer<T, U> allThrowException(JBiConsumer<T, U> jBiConsumer) {
        return (t, u) -> {
            try {
                jBiConsumer.accept(t, u);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JBiConsumer进行封装，返回一个BiConsumer，内部将编译异常转成运行时异常
     *
     * @param jBiConsumer 自定义函数式接口
     * @param description 业务描述
     * @param <T>         泛型
     * @param <U>         泛型
     * @return 返回值
     */
    static <T, U> BiConsumer<T, U> allThrowException(JBiConsumer<T, U> jBiConsumer, String description) {
        return (t, u) -> {
            try {
                jBiConsumer.accept(t, u);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
