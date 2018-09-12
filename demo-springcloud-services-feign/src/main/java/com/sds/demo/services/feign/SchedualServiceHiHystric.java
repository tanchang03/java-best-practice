package com.sds.demo.services.feign;

import org.springframework.stereotype.Component;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.demo.services.feign
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年09月07日 下午1:43
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
