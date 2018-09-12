package com.sds.demo.services.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.demo.services.feign
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年09月06日 下午6:07
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@FeignClient(value = "service-hi",fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

}
