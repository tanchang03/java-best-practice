package com.sds.declarative.test;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.declarative.test
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年08月08日 下午1:41
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class RxJavaTest {

    @Test
    public void test001() throws InterruptedException {
        Observable observable1= Observable.empty();
        Observable o = Observable.timer(10000,TimeUnit.MILLISECONDS);

                o.subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        System.out.println("onSubscribe");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        System.out.println(aLong);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("error :"+throwable);

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("ok");
                    }
                });

        Thread.currentThread().join();
    }


    @Test
    public void test002() throws InterruptedException {
        Observable.interval(1000,TimeUnit.MILLISECONDS).subscribe((x)->{
            System.out.println("启动："+x);
        });
        Thread.currentThread().join();
    }

    @Test
    public void test003(){
        Observable.just("d","f","e","c","e","f").map(x->x.toCharArray()[0]).map((y)->{
            int x = y;
            System.out.println("处理："+y);
            Thread.sleep(1000);
            return x;
        }).distinct().sorted().subscribe(x -> {
            System.out.println(x);
        });
    }

    @Test
    public void test004(){
        Observable.just("张三","李四","","","王五","赵六")
                .filter(x -> x.length()>0)
                .flatMap(name -> Observable.just(name))
                .takeLast(1)
                .forEach(result -> {
            System.out.println(result);
        });
    }


    @Test
    public void test005() throws InterruptedException {
        int count = 10;
        CountDownLatch finishedLatch = new CountDownLatch(1);
        long t = System.nanoTime();
        Observable.range(0, count).map(i -> {
            System.out.println("A:" + Thread.currentThread().getName());
            return 200;
        })
                .subscribe(statusCode -> {
            System.out.println("B:" + Thread.currentThread().getName());
        }, error -> {
        }, () -> {
            finishedLatch.countDown();
        });
        finishedLatch.await();
        t = (System.nanoTime() - t) / 1000000; //ms
        System.out.println("RxJavaWithoutBlocking TPS: " + count * 1000 / t);
    }
}
