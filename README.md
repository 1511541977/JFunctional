# JFunctional
JFunctional，可以抛异常的函数式接口，是对Java内置的函数式接口的补充！

# Get Start
## 说明：
1、我们经常会使用CompletableFuture来开启线程，CompletableFuture的supplyAsync()方法的参数是一个Supplier(函数式接口)。

2、当我们在线程中遇到了异常时，由于Supplier中的get()方法是无法抛出异常的，对于编译时的异常，通常我们都会在Lambda表达式中写try-catch来捕获异常，但是当我们开启许多线程的时候，就需要在每个线程中都使用try-catch来捕获异常，非常的不方便，也不美观！



前提：

```java
public class TestUtils {

    /**
     * 传入数值型参数，返回对于字符串
     *
     * @param t   数值
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> String randomString(T t) throws Exception {
        if (t instanceof Integer || t instanceof Long || t instanceof Float || t instanceof Double ||
                t instanceof Boolean || t instanceof Character) {
            return String.valueOf(t);
        }
        else {
            throw new Exception("参数为非法数值！");
        }
    }

    /**
     *
     * @param value 整数，区间为[0,5]
     * @return 返回参数加上一个[0,100)的随机数
     * @throws Exception
     */
    public static String throwException(Integer value) throws Exception {
        if (value == null) {
            throw new NullPointerException("参数value为null！");
        }
        else if (value > 5 && value <= 10) {
            throw new IllegalAccessException("非法访问：5 < value <= 10");
        }
        else if (value > 10 && value <= 20) {
            throw new InvalidParameterException("值无效：10 < value <= 20");
        }
        else if (value > 20) {
            throw new InvalidParameterSpecException("值不规范：value > 20");
        }
        else if (value < 0) {
            throw new ArithmeticException("算法要求错误：value < 0");
        }
        else {
            return value + " ---> " + (value + ((int) (Math.random() * 100)));
        }
    }
}
```



例：

```java
ExecutorService executor = new ThreadPoolExecutor(5, 10, 5000,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

......

CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    try {
        return TestUtils.randomString(new Object());
    }
    catch (Exception e) {
        log.error(e);
        return null;
    }
}, executor);
```

***当开启许多线程的时候就会造成出现许多的try-catch代码块，究其原因，是Java内置的函数式接口的抽象方法并没有抛出异常，所以我定义了如下结构(以Supplier举例，其它函数式接口用法相同)***



## 一、JSupplier

#### 1、先看一下Supplier的源码

```java
@FunctionalInterface
public interface Supplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}
```

#### 2、JSupplier与Supplier基本上是一模一样，只不过JSupplier的抽象方法可以抛异常

```
@FunctionalInterface
public interface JSupplier<T> {

    /**
     * 与Supplier同样的方法，可抛异常
     *
     * @return
     * @throws Exception
     */
    T get() throws Exception;

    /****************************************************************************************************/

    /**
     * 对JSupplier进行封装，返回一个Supplier，内部将编译异常转成运行时异常
     *
     * @param jSupplier 自定义函数式接口
     * @param <T>
     * @return
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
     * @param jSupplier  自定义函数式接口
     * @param description 业务描述
     * @param <T>
     * @return
     */
    static <T> Supplier<T> allowThrowException(JSupplier<T> jSupplier, String description) {
        return () -> {
            try {
                return jSupplier.get();
            }
            catch (Exception e) {
                throw new RuntimeException(description + "异常，" + e.getMessage());
            }
        };
    }
}
```

***可见get()方法是可以抛异常的，其它的和Supplier基本上是一致的，JSupplier中还提供了两个重载的allowThrowException()方法，参数是JSupplier实现类对象，返回的是Supplier实现类对象，此方法会直接将异常抛出来***

#### 3、将上面的例子修改为使用JSupplier

```java
ExecutorService executor = new ThreadPoolExecutor(5, 10, 5000,
                TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

......

CompletableFuture<String> future = CompletableFuture.supplyAsync(JSupplier.allowThrowException(() -> TestUtils.randomString(new Object())), executor);
```

***上述例子，将会直接将异常抛出来，但又会有疑问了，若我们开启多个线程，比如：10个线程，其中有5个线程都有异常，那么我们始终只会抛出一个异常，当解决完一个异常之后运行，又会报错，又继续解决异常，如此反复，在Web环境中是非常致命的，因为这会让你一直重启，或者运行5遍单元测试都有异常的情况。那么有没有一个好的解决方案呢？接下来我们使用另一个类来解决这个问题。***



## 二、FunctionalUtils

#### 1、先看其中关于JSupplier的两个方法

```java
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
    
	......
    
}
```

***与allThrowException大致相同，只不过FunctionalUtils中的方法借助了Lombok，使用@Log4j2之后，使用log对象将异常信息打印出来***