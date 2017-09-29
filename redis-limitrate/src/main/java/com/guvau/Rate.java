package com.guvau;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangyonghua on 2017/9/29.
 */
public class Rate {
    //http://www.cnblogs.com/xulele/p/5918881.html
    //之前的限流方式都不能很好地应对突发请求，即瞬间请求可能都被允许从而导致一些问题；因此在一些场景中需要对突发请求进行整形，整形为平均速率请求处理（比如5r/s，则每隔200毫秒处理一个请求，平滑了速率）。这个时候有两种算法满足我们的场景：令牌桶和漏桶算法。Guava框架提供了令牌桶算法实现，可直接拿来使用。
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(5, 1000, TimeUnit.MILLISECONDS);
        for (int i = 1; i < 5; i++) {
            System.out.println(limiter.acquire());
        }
        Thread.sleep(1000);
        for (int i = 1; i < 5; i++) {
            System.out.println(limiter.acquire());
        }
    }
}
