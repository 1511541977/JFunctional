package com.zeng.extension.functional;

import lombok.extern.log4j.Log4j2;

import java.util.function.*;

/**
 * @author JJ_yo
 * @title: 包装自定义函数式接口
 * @description: 包装自定义函数式接口，返回Java提供的函数式接口实例
 * @date 2021-02-25 14:49
 * @version: 1.0
 */
@Log4j2
public class FunctionalUtils {
    /**************************************** JSupplier -> Supplier ****************************************/

    /**
     * 对JSupplier进行封装，返回一个Supplier，内部将编译异常转成运行时异常
     *
     * @param jSupplier 自定义函数式接口
     * @param <T>
     * @return
     */
    public static <T> Supplier<T> supplier(JSupplier<T> jSupplier) {
        return () -> {
            try {
                return jSupplier.get();
            }
            catch (Exception e) {
                log.error(e);
                return null;
            }
        };
    }

    /**
     * 对JSupplier进行封装，返回一个Supplier，内部将编译异常转成运行时异常
     *
     * @param jSupplier  自定义函数式接口
     * @param description 业务描述
     * @param <T>
     * @return
     */
    public static <T> Supplier<T> supplier(JSupplier<T> jSupplier, String description) {
        return () -> {
            try {
                return jSupplier.get();
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return null;
            }
        };
    }

    /**************************************** JConsumer -> Consumer ****************************************/

    /**
     * 对JConsumer进行封装，返回一个Consumer，内部将编译异常转成运行时异常
     *
     * @param jConsumer 自定义函数式接口
     * @param <T>
     * @return
     */
    public static <T> Consumer<T> consumer(JConsumer<T> jConsumer) {
        return t -> {
            try {
                jConsumer.accept(t);
            }
            catch (Exception e) {
                log.error(e);
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
    public static <T> Consumer<T> consumer(JConsumer<T> jConsumer, String description) {
        return t -> {
            try {
                jConsumer.accept(t);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
            }
        };
    }

    /**************************************** JFunction -> Function ****************************************/

    /**
     * 对JFunction进行封装，返回一个Function，内部将编译异常转成运行时异常
     *
     * @param jFunction 自定义函数式接口
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Function<T, R> function(JFunction<T, R> jFunction) {
        return t -> {
            try {
                return jFunction.apply(t);
            }
            catch (Exception e) {
                log.error(e);
                return null;
            }
        };
    }

    /**
     * 对JFunction进行封装，返回一个Function，内部将编译异常转成运行时异常
     *
     * @param jFunction  自定义函数式接口
     * @param description 异常所需信息
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Function<T, R> function(JFunction<T, R> jFunction, String description) {
        return t -> {
            try {
                return jFunction.apply(t);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return null;
            }
        };
    }

    /**************************************** JPredicate -> Predicate ****************************************/

    /**
     * 对JPredicate进行封装，返回一个Predicate，内部将编译异常转成运行时异常
     *
     * @param jPredicate 自定义函数式接口
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> predicate(JPredicate<T> jPredicate) {
        return t -> {
            try {
                return jPredicate.test(t);
            }
            catch (Exception e) {
                log.error(e);
                return false;
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
    public static <T> Predicate<T> predicate(JPredicate<T> jPredicate, String description) {
        return t -> {
            try {
                return jPredicate.test(t);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return false;
            }
        };
    }

    /**************************************** JBiFunction -> BiFunction ****************************************/

    /**
     * 对JBiFunction进行封装，返回一个BiFunction，内部将编译异常转成运行时异常
     *
     * @param jBiFunction 自定义函数式接口
     * @param <T>
     * @param <U>
     * @param <R>
     * @return
     */
    public static <T, U, R> BiFunction<T, U, R> biFunction(JBiFunction<T, U, R> jBiFunction) {
        return (t, u) -> {
            try {
                return jBiFunction.apply(t, u);
            }
            catch (Exception e) {
                log.error(e);
                return null;
            }
        };
    }

    /**
     * 对JBiFunction进行封装，返回一个BiFunction，内部将编译异常转成运行时异常
     *
     * @param jBiFunction 自定义函数式接口
     * @param description  业务描述
     * @param <T>
     * @param <U>
     * @param <R>
     * @return
     */
    public static <T, U, R> BiFunction<T, U, R> biFunction(JBiFunction<T, U, R> jBiFunction, String description) {
        return (t, u) -> {
            try {
                return jBiFunction.apply(t, u);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return null;
            }
        };
    }

    /**************************************** JUnaryOperator -> UnaryOperator ****************************************/

    /**
     * 对JUnaryOperator进行封装，返回一个UnaryOperator，内部将编译异常转成运行时异常
     *
     * @param jUnaryOperator 自定义函数式接口
     * @param <T>
     * @return
     */
    public static <T> UnaryOperator<T> unaryOperator(JUnaryOperator<T> jUnaryOperator) {
        return t -> {
            try {
                return jUnaryOperator.apply(t);
            }
            catch (Exception e) {
                log.error(e);
                return null;
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
    public static <T> UnaryOperator<T> unaryOperator(JUnaryOperator<T> jUnaryOperator, String description) {
        return t -> {
            try {
                return jUnaryOperator.apply(t);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return null;
            }
        };
    }

    /**************************************** JBinaryOperator -> BinaryOperator ****************************************/

    /**
     * 对JBinaryOperator进行封装，返回一个BinaryOperator，内部将编译异常转成运行时异常
     *
     * @param jBinaryOperator 自定义函数式接口
     * @param <T>
     * @return
     */
    public static <T> BinaryOperator<T> binaryOperator(JBinaryOperator<T> jBinaryOperator) {
        return (T t, T u) -> {
            try {
                return jBinaryOperator.apply(t, u);
            }
            catch (Exception e) {
                log.error(e);
                return null;
            }
        };
    }

    /**
     * 对JBinaryOperator进行封装，返回一个BinaryOperator，内部将编译异常转成运行时异常
     *
     * @param jBinaryOperator 自定义函数式接口
     * @param description      业务描述
     * @param <T>
     * @return
     */
    public static <T> BinaryOperator<T> binaryOperator(JBinaryOperator<T> jBinaryOperator, String description) {
        return (T t, T u) -> {
            try {
                return jBinaryOperator.apply(t, u);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return null;
            }
        };
    }

    /**************************************** JBiConsumer -> BiConsumer ****************************************/

    /**
     * 对JBiConsumer进行封装，返回一个BiConsumer，内部将编译异常转成运行时异常
     *
     * @param jBiConsumer 自定义函数式接口
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> BiConsumer<T, U> biConsumer(JBiConsumer<T, U> jBiConsumer) {
        return (t, u) -> {
            try {
                jBiConsumer.accept(t, u);
            }
            catch (Exception e) {
                log.error(e);
            }
        };
    }

    /**
     * 对JBiConsumer进行封装，返回一个BiConsumer，内部将编译异常转成运行时异常
     *
     * @param jBiConsumer 自定义函数式接口
     * @param description  业务描述
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> BiConsumer<T, U> biConsumer(JBiConsumer<T, U> jBiConsumer, String description) {
        return (t, u) -> {
            try {
                jBiConsumer.accept(t, u);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
            }
        };
    }

    /**************************************** JBiPredicate -> BiPredicate ****************************************/

    /**
     * 对JBiPredicate进行封装，返回一个BiPredicate，内部将编译异常转成运行时异常
     *
     * @param jBiPredicate 自定义函数式接口
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> BiPredicate<T, U> biPredicate(JBiPredicate<T, U> jBiPredicate) {
        return (t, u) -> {
            try {
                return jBiPredicate.test(t, u);
            }
            catch (Exception e) {
                log.error(e);
                return false;
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
    public static <T, U> BiPredicate<T, U> biPredicate(JBiPredicate<T, U> jBiPredicate, String description) {
        return (t, u) -> {
            try {
                return jBiPredicate.test(t, u);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return false;
            }
        };
    }

    /**************************************** JToIntFunction -> ToIntFunction ****************************************/

    /**
     * 对JToIntFunction进行封装，返回一个ToIntFunction，内部将编译异常转成运行时异常
     *
     * @param jToIntFunction 自定义函数式接口
     * @param <T>
     * @return
     */
    public static <T> ToIntFunction<T> toIntFunction(JToIntFunction<T> jToIntFunction) {
        return t -> {
            try {
                return jToIntFunction.applyAsInt(t);
            }
            catch (Exception e) {
                log.error(e);
                return -1;
            }
        };
    }

    /**
     * 对JToIntFunction进行封装，返回一个ToIntFunction，内部将编译异常转成运行时异常
     *
     * @param jToIntFunction 自定义函数式接口
     * @param description     业务描述
     * @param <T>
     * @return
     */
    public static <T> ToIntFunction<T> toIntFunction(JToIntFunction<T> jToIntFunction, String description) {
        return t -> {
            try {
                return jToIntFunction.applyAsInt(t);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return -1;
            }
        };
    }

    /**************************************** JToIntBiFunction -> ToIntBiFunction ****************************************/

    /**
     * 对JToIntBiFunction进行封装，返回一个ToIntBiFunction，内部将编译异常转成运行时异常
     *
     * @param jToIntBiFunction 自定义函数式接口
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> ToIntBiFunction<T, U> toIntBiFunction(JToIntBiFunction<T, U> jToIntBiFunction) {
        return (t, u) -> {
            try {
                return jToIntBiFunction.applyAsInt(t, u);
            }
            catch (Exception e) {
                log.error(e);
                return -1;
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
    public static <T, U> ToIntBiFunction<T, U> toIntBiFunction(JToIntBiFunction<T, U> jToIntBiFunction, String description) {
        return (t, u) -> {
            try {
                return jToIntBiFunction.applyAsInt(t, u);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return -1;
            }
        };
    }

    /**************************************** JToLongFunction -> ToLongFunction ****************************************/

    /**
     * 对JToLongFunction进行封装，返回一个ToLongFunction，内部将编译异常转成运行时异常
     *
     * @param jToLongFunction 自定义函数式接口
     * @param <T>
     * @return
     */
    public static <T> ToLongFunction<T> toLongFunction(JToLongFunction<T> jToLongFunction) {
        return t -> {
            try {
                return jToLongFunction.applyAsLong(t);
            }
            catch (Exception e) {
                log.error(e);
                return -1;
            }
        };
    }

    /**
     * 对JToLongFunction进行封装，返回一个ToLongFunction，内部将编译异常转成运行时异常
     *
     * @param jToLongFunction 自定义函数式接口
     * @param description      业务描述
     * @param <T>
     * @return
     */
    public static <T> ToLongFunction<T> toLongFunction(JToLongFunction<T> jToLongFunction, String description) {
        return t -> {
            try {
                return jToLongFunction.applyAsLong(t);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return -1;
            }
        };
    }

    /**************************************** JToLongBiFunction -> ToLongBiFunction ****************************************/

    /**
     * 对JToLongBiFunction进行封装，返回一个ToLongBiFunction，内部将编译异常转成运行时异常
     *
     * @param jToLongBiFunction 自定义函数式接口
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> ToLongBiFunction<T, U> toLongBiFunction(JToLongBiFunction<T, U> jToLongBiFunction) {
        return (t, u) -> {
            try {
                return jToLongBiFunction.applyAsLong(t, u);
            }
            catch (Exception e) {
                log.error(e);
                return -1;
            }
        };
    }

    /**
     * 对JToLongBiFunction进行封装，返回一个ToLongBiFunction，内部将编译异常转成运行时异常
     *
     * @param jToLongBiFunction 自定义函数式接口
     * @param description        业务描述
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> ToLongBiFunction<T, U> toLongBiFunction(JToLongBiFunction<T, U> jToLongBiFunction, String description) {
        return (t, u) -> {
            try {
                return jToLongBiFunction.applyAsLong(t, u);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return -1;
            }
        };
    }

    /**************************************** JToDoubleFunction -> ToDoubleFunction ****************************************/

    /**
     * 对JToDoubleFunction进行封装，返回一个ToDoubleFunction，内部将编译异常转成运行时异常
     *
     * @param jToDoubleFunction 自定义函数式接口
     * @param <T>
     * @return
     */
    public static <T> ToDoubleFunction<T> toDoubleFunction(JToDoubleFunction<T> jToDoubleFunction) {
        return t -> {
            try {
                return jToDoubleFunction.applyAsDouble(t);
            }
            catch (Exception e) {
                log.error(e);
                return -1;
            }
        };
    }

    /**
     * 对JToDoubleFunction进行封装，返回一个ToDoubleFunction，内部将编译异常转成运行时异常
     *
     * @param jToDoubleFunction 自定义函数式接口
     * @param description        业务描述
     * @param <T>
     * @return
     */
    public static <T> ToDoubleFunction<T> toDoubleFunction(JToDoubleFunction<T> jToDoubleFunction, String description) {
        return t -> {
            try {
                return jToDoubleFunction.applyAsDouble(t);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return -1;
            }
        };
    }

    /**************************************** JToDoubleBiFunction -> ToDoubleBiFunction ****************************************/

    /**
     * 对JToDoubleBiFunction进行封装，返回一个ToDoubleBiFunction，内部将编译异常转成运行时异常
     *
     * @param jToDoubleBiFunction 自定义函数式接口
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> ToDoubleBiFunction<T, U> toDoubleBiFunction(JToDoubleBiFunction<T, U> jToDoubleBiFunction) {
        return (t, u) -> {
            try {
                return jToDoubleBiFunction.applyAsDouble(t, u);
            }
            catch (Exception e) {
                log.error(e);
                return -1;
            }
        };
    }

    /**
     * 对JToDoubleBiFunction进行封装，返回一个ToDoubleBiFunction，内部将编译异常转成运行时异常
     *
     * @param jToDoubleBiFunction 自定义函数式接口
     * @param description          业务描述
     * @param <T>
     * @param <U>
     * @return
     */
    public static <T, U> ToDoubleBiFunction<T, U> toDoubleBiFunction(JToDoubleBiFunction<T, U> jToDoubleBiFunction, String description) {
        return (t, u) -> {
            try {
                return jToDoubleBiFunction.applyAsDouble(t, u);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return -1;
            }
        };
    }

    /**************************************** JIntFunction -> IntFunction ****************************************/

    /**
     * 对JIntFunction进行封装，返回一个IntFunction，内部将编译异常转成运行时异常
     *
     * @param jIntFunction 自定义函数式接口
     * @param <R>
     * @return
     */
    public static <R> IntFunction<R> intFunction(JIntFunction<R> jIntFunction) {
        return value -> {
            try {
                return jIntFunction.apply(value);
            }
            catch (Exception e) {
                log.error(e);
                return null;
            }
        };
    }

    /**
     * 对JIntFunction进行封装，返回一个IntFunction，内部将编译异常转成运行时异常
     *
     * @param jIntFunction 自定义函数式接口
     * @param description   业务描述
     * @param <R>
     * @return
     */
    public static <R> IntFunction<R> intFunction(JIntFunction<R> jIntFunction, String description) {
        return value -> {
            try {
                return jIntFunction.apply(value);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return null;
            }
        };
    }

    /**************************************** JLongFunction -> LongFunction ****************************************/

    /**
     * 对JLongFunction进行封装，返回一个LongFunction，内部将编译异常转成运行时异常
     *
     * @param jLongFunction 自定义函数式接口
     * @param <R>
     * @return
     */
    public static <R> LongFunction<R> longFunction(JLongFunction<R> jLongFunction) {
        return value -> {
            try {
                return jLongFunction.apply(value);
            }
            catch (Exception e) {
                log.error(e);
                return null;
            }
        };
    }

    /**
     * 对JLongFunction进行封装，返回一个LongFunction，内部将编译异常转成运行时异常
     *
     * @param jLongFunction 自定义函数式接口
     * @param description    业务描述
     * @param <R>
     * @return
     */
    public static <R> LongFunction<R> longFunction(JLongFunction<R> jLongFunction, String description) {
        return value -> {
            try {
                return jLongFunction.apply(value);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return null;
            }
        };
    }

    /**************************************** JDoubleFunction -> DoubleFunction ****************************************/

    /**
     * 对JDoubleFunction进行封装，返回一个DoubleFunction，内部将编译异常转成运行时异常
     *
     * @param jDoubleFunction 自定义函数式接口
     * @param <R>
     * @return
     */
    public static <R> DoubleFunction<R> doubleFunction(JDoubleFunction<R> jDoubleFunction) {
        return value -> {
            try {
                return jDoubleFunction.apply(value);
            }
            catch (Exception e) {
                log.error(e);
                return null;
            }
        };
    }

    /**
     * 对JDoubleFunction进行封装，返回一个DoubleFunction，内部将编译异常转成运行时异常
     *
     * @param jDoubleFunction 自定义函数式接口
     * @param description      业务描述
     * @param <R>
     * @return
     */
    public static <R> DoubleFunction<R> doubleFunction(JDoubleFunction<R> jDoubleFunction, String description) {
        return value -> {
            try {
                return jDoubleFunction.apply(value);
            }
            catch (Exception e) {
                log.error(description + "异常", e);
                return null;
            }
        };
    }
}
