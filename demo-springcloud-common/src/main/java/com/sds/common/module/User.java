package com.sds.common.module;

import lombok.Data;

import java.util.Date;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.common.module
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年09月26日 1:50 PM
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@Data
public class User {

    private String name;
    private String email;
    private Date birthday;
    private Integer age;

}

