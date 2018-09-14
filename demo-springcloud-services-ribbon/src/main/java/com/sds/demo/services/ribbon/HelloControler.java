package com.sds.demo.services.ribbon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.demo.services.ribbon
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年09月06日 下午5:11
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@RestController
public class HelloControler {
    @Autowired
    HelloService helloService;
    
    private static final Logger logger = LoggerFactory.getLogger(HelloControler.class);

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        logger.info("ribbon controller is start ");
        return helloService.hiService( name );
    }
}
