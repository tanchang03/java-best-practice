# 声明式编程 Declarative Programing
>声明式编程是相对于命令式编程而言的，传统JAVA开发都是基于命令式编程(imperactive programing)，命令式编程语言主要是告诉计算机做一件事情应该怎么做，
而声明式编程主要是告诉机器想要什么，让机器想出怎么做

声明式编程是命令式编程的更高级封装

最有代表新的声明式语言就是SQL语法

Java 1.8+支持的几个特性用来体现声明式编程的想法
* lambda函数式编程
* stream语法  map flatmap 等

# 响应式编程ReactiveX

[http://reactivex.io/languages.html](http://reactivex.io/languages.html)

支持多语言：
Java版本:RxJava  [https://github.com/ReactiveX/RxJava](https://github.com/ReactiveX/RxJava)
Js版本：RxJs

ReactiveStream : [https://github.com/reactive-streams/reactive-streams-jvm](https://github.com/reactive-streams/reactive-streams-jvm)

一些Reactive Streams实现的库包如下：
1. RxJava 2.x或RxJavaReactiveStreams with RxJava 1.x 
2. Reactor 
3. Vert.x
4. Akka Streams
5. Slick

Reactive Streams是一个基于JVM的面向流的库包的标准和规范：

 处理潜在的无边界限制的元素
顺序
在组件之间异步传递元素
使用强制性的非堵塞的抗压。


* [深入浅出RxJava（一：基础篇）](https://blog.csdn.net/lzyzsd/article/details/41833541)
* [深入浅出RxJava(二：操作符)](https://blog.csdn.net/lzyzsd/article/details/44094895)
* [深入浅出RxJava三--响应式的好处](https://blog.csdn.net/lzyzsd/article/details/44891933)
* [Java 8 之 Stream](https://blog.csdn.net/zwlove5280/article/details/77576216)