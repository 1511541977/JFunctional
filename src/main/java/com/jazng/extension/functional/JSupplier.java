package com.jazng.extension.functional;

import java.util.function.Supplier;

/**
 * @author JJ_yo
 * 自定义函数式接口
 * 自定义函数式接口，定义一个与Supplier同样的方法，在实际使用中，我们可以使用此方法代替Supplier中的get方法
 * 2021-02-25 14:32
 * @version: 1.0
 */
@FunctionalInterface
public interface JSupplier<T> {

    /**
     * 与Supplier同样的方法，可抛异常
     *
     * @return 返回值
     * @throws Exception 异常
     */
    T get() throws Exception;

    /****************************************************************************************************/

    /**
     * 对JSupplier进行封装，返回一个Supplier，内部将编译异常转成运行时异常
     *
     * @param jSupplier 自定义函数式接口
     * @param <T>       泛型
     * @return 返回值
     */
    static <T> Supplier<T> allowThrowException(JSupplier<T> jSupplier) {
        return () -> {
            try {
                return jSupplier.get();
            }
            catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    /**
     * 对JSupplier进行封装，返回一个Supplier，内部将编译异常转成运行时异常
     *
     * @param jSupplier   自定义函数式接口
     * @param description 业务描述
     * @param <T>         泛型
     * @return 返回值
     */
    static <T> Supplier<T> allowThrowException(JSupplier<T> jSupplier, String description) {
        return () -> {
            try {
                return jSupplier.get();
            }
            catch (Exception e) {
                //log.error(description + "异常", e);
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
