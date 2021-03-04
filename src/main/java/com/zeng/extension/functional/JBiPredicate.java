package com.zeng.extension.functional;

import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * @author JJ_yo
 * @title: 自定义函数式接口
 * @description: 自定义函数式接口，定义一个与BiPredicate同样的方法，在实际使用中，我们可以使用此方法代替BiPredicate中的test方法
 * @date 2021-03-01 10:35
 * @version: 1.0
 */
@FunctionalInterface
public interface JBiPredicate<T, U> {

    /**
     * 与BiPredicate同样的方法，可抛异常
     *
     * @param t
     * @param u
     * @return
     * @throws Exception
     */
    boolean test(T t, U u) throws Exception;

    /**
     * 与BiPredicate同样的方法，接口为自定义
     *
     * @param other
     * @return
     */
    default JBiPredicate<T, U> and(JBiPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (T t, U u) -> test(t, u) && other.test(t, u);
    }

    /**
     * 与BiPredicate同样的方法，接口为自定义
     *
     * @return
     */
    default JBiPredicate<T, U> negate() {
        return (T t, U u) -> !test(t, u);
    }

    /**
     * 与BiPredicate同样的方法，接口为自定义
     *
     * @param other
     * @return
     */
    default JBiPredicate<T, U> or(JBiPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (T t, U u) -> test(t, u) || other.test(t, u);
    }

    /****************************************************************************************************/

    /**
     * 对JBiPredicate进行封装，返回一个BiPredicate，内部将编译异常转成运行时异常
     *
     * @param jBiPredicate 自定义函数式接口
     * @param <T>
     * @param <U>
     * @return
     */
    static <T, U> BiPredicate<T, U> allowThrowException(JBiPredicate<T, U> jBiPredicate) {
        return (t, u) -> {
            try {
                return jBiPredicate.test(t, u);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JBiPredicate进行封装，返回一个BiPredicate，内部将编译异常转成运行时异常
     *
     * @param jBiPredicate 自定义函数式接口
     * @param description   业务描述
     * @param <T>
     * @param <U>
     * @return
     */
    static <T, U> BiPredicate<T, U> allowThrowException(JBiPredicate<T, U> jBiPredicate, String description) {
        return (t, u) -> {
            try {
                return jBiPredicate.test(t, u);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
