package com.sds.practice.application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.practice.application
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年07月30日 上午10:23
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@RestController
public class controller {

    @RequestMapping("/open/hello")
    public String hello(){
        return "hello world";
    }


}
