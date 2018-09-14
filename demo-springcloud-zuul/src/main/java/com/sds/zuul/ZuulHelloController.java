package com.sds.zuul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.zuul
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年09月13日 下午7:52
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@RestController
public class ZuulHelloController {

    private static final Logger logger = LoggerFactory.getLogger(ZuulHelloController.class);

    @RequestMapping("/zuul/hello")
    public String hello(){
        logger.info("这是个日志：{}","zuul hello");
        return "zuul hello";
    }
}
