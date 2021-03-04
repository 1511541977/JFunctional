package com.zeng.extension.functional;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author JJ_yo
 * @title: 自定义函数式接口
 * @description: 自定义函数式接口，定义一个与Predicate同样的方法，在实际使用中，我们可以使用此方法代替Predicate中的test方法
 * @date 2021-02-25 15:37
 * @version: 1.0
 */
@FunctionalInterface
public interface JPredicate<T> {

    /**
     * 与Predicate同样的方法，可抛异常
     *
     * @param t
     * @return
     * @throws Exception
     */
    boolean test(T t) throws Exception;

    /**
     * 与Predicate同样的方法，接口为自定义
     *
     * @param other
     * @return
     */
    default JPredicate<T> and(JPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    /**
     * 与Predicate同样的方法，接口为自定义
     *
     * @return
     */
    default JPredicate<T> negate() {
        return (t) -> !test(t);
    }

    /**
     * 与Predicate同样的方法，接口为自定义
     *
     * @param other
     * @return
     */
    default JPredicate<T> or(JPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    /**
     * 与Predicate同样的方法，接口为自定义
     *
     * @param targetRef
     * @param <T>
     * @return
     */
    static <T> JPredicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : object -> targetRef.equals(object);
    }

    /****************************************************************************************************/

    /**
     * 对JPredicate进行封装，返回一个Predicate，内部将编译异常转成运行时异常
     *
     * @param jPredicate 自定义函数式接口
     * @param <T>
     * @return
     */
    static <T> Predicate<T> allowThrowException(JPredicate<T> jPredicate) {
        return t -> {
            try {
                return jPredicate.test(t);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JPredicate进行封装，返回一个Predicate，内部将编译异常转成运行时异常
     *
     * @param jPredicate 自定义函数式接口
     * @param description 业务描述
     * @param <T>
     * @return
     */
    static <T> Predicate<T> allowThrowException(JPredicate<T> jPredicate, String description) {
        return t -> {
            try {
                return jPredicate.test(t);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
