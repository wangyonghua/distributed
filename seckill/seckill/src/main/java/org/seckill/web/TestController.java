package org.seckill.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangyonghua on 2017/9/12.
 */
@RestController
public class TestController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
