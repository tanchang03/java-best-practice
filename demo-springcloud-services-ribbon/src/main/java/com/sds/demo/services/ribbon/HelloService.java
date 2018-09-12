package com.sds.demo.services.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.demo.services.ribbon
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年09月06日 下午5:10
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }

    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }

}
