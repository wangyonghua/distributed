package com.guvau;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by wangyonghua on 2017/9/29.
 */
public class Cache {
    //限流某个接口的时间窗请求数
    public static void main(String[] args) throws ExecutionException {
        LoadingCache<Long, AtomicLong> counter =
                CacheBuilder.newBuilder()
                        .expireAfterWrite(2, TimeUnit.SECONDS)
                        .build(new CacheLoader<Long, AtomicLong>() {
                            @Override
                            public AtomicLong load(Long seconds) throws Exception {
                                return new AtomicLong(0);
                            }
                        });
        long limit = 1000;
        while (true) {
            //得到当前秒
            long currentSeconds = System.currentTimeMillis() / 1000;
            if (counter.get(currentSeconds).incrementAndGet() > limit) {
                System.out.println("限流了:" + currentSeconds);
                continue;
            }
            //业务处理
        }
    }
}
