package com.sds.demo.services.ribbon;

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

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService( name );
    }
}
