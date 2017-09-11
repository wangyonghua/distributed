package com.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wangyonghua on 2017/9/11.
 */
@RestController
public class TestController {

    @Autowired
    JedisPool jedisPool;

    @GetMapping("/")
    public void index(HttpServletResponse response) throws IOException {
        Jedis jedis = jedisPool.getResource();
        String token = RedisRateLimiter.acquireTokenFromBucket(jedis, 20, 5000);
        if (token == null) {
            response.sendError(500);
        } else {
            //TODO 你的业务逻辑
        }
        jedisPool.returnResource(jedis);
    }
}
