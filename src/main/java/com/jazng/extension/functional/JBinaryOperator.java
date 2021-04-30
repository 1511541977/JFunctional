package com.jazng.extension.functional;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * @author JJ_yo
 * 自定义函数式接口
 * 自定义函数式接口，继承JBiFunction，泛型都为同一个类型，即T apply(T t, T u)
 * 2021-03-01 10:11
 * @version: 1.0
 */
@FunctionalInterface
public interface JBinaryOperator<T> extends JBiFunction<T, T, T> {

    /**
     * 与BinaryOperator相同的方法，，接口为自定义
     *
     * @param comparator 参数
     * @param <T>        泛型
     * @return 返回值
     */
    public static <T> JBinaryOperator<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
    }

    /**
     * 与BinaryOperator相同的方法，，接口为自定义
     *
     * @param comparator 参数
     * @param <T>        泛型
     * @return 返回值
     */
    public static <T> JBinaryOperator<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
    }

    /****************************************************************************************************/

    /**
     * 对JBinaryOperator进行封装，返回一个BinaryOperator，内部将编译异常转成运行时异常
     *
     * @param jBinaryOperator 自定义函数式接口
     * @param <T>             泛型
     * @return 返回值
     */
    static <T> BinaryOperator<T> allowThrowException(JBinaryOperator<T> jBinaryOperator) {
        return (T t, T u) -> {
            try {
                return jBinaryOperator.apply(t, u);
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JBinaryOperator进行封装，返回一个BinaryOperator，内部将编译异常转成运行时异常
     *
     * @param jBinaryOperator 自定义函数式接口
     * @param description     业务描述
     * @param <T>             泛型
     * @return 返回值
     */
    static <T> BinaryOperator<T> allowThrowException(JBinaryOperator<T> jBinaryOperator, String description) {
        return (T t, T u) -> {
            try {
                return jBinaryOperator.apply(t, u);
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
