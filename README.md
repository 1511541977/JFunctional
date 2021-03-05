# JFunctional

JFunctional，可以抛异常的函数式接口，是对Java内置的函数式接口的补充！

# Get Start

## 说明：

1、我们经常会使用CompletableFuture来开启线程，CompletableFuture的supplyAsync()方法的参数是一个Supplier(函数式接口)。

2、当我们在线程中遇到了异常时，由于Supplier中的get()方法是无法抛出异常的，通常我们都会在Lambda表达式中写try-catch来捕获异常，但是当我们开启许多线程的时候，就需要在每个线程中都使用try-catch来捕获异常，非常的不方便，也不美观！



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

**当开启许多线程的时候就会造成出现许多的try-catch代码块，究其原因，是Java内置的函数式接口的抽象方法并没有抛出异常，所以我定义了如下结构(以Supplier举例，其它函数式接口用法相同)**



## 一、JSupplier