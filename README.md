# JFunctional
JFunctional，可以抛异常的函数式接口，是对Java内置的函数式接口的补充！

# Get Start
## 说明：
1、我们经常会使用CompletableFuture来开启线程，CompletableFuture的supplyAsync()方法的参数是一个Supplier(函数式接口)。
2、当我们在线程中遇到了异常时，由于Supplier中的get()方法是无法抛出异常的，通常我们都会在Lambda表达式中写try-catch来捕获异常，但是当我们开启许多线程的时候，就需要在每个线程中都使用try-catch来捕获异常，非常的不方便，也不美观！

例：
    
