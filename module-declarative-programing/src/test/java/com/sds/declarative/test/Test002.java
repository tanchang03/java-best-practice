package com.sds.declarative.test;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.declarative.test
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年08月08日 上午10:50
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
public class Test002 {

    @Test
    public void test001(){
        Flowable.just("hello world").subscribe(System.out::print);
    }

    @Test
    public void test002(){
        Observable<Integer> observable = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            System.out.println(1/0);
            emitter.onNext(3);
            emitter.onComplete();
        });
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("subscribe");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("do next:"+integer);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error");
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        };
//        observable.subscribe(observer);
        observable.subscribe(next -> {
            System.out.println("do next:"+next);
        },error->{
            System.out.println("error:"+error);
        },()->{
            System.out.println("complete");
        });
    }
}
