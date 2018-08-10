package com.sds.practice.application;

import com.sds.practice.application.util.RestResponse;
import com.sds.practice.domain.entity.UserEntity;
import com.sds.practice.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.practice.application.util
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年08月10日 下午3:23
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/api/users")
    public RestResponse<List<UserEntity>> list(){
        return RestResponse.success(userService.list("from UserEntity obj"));
    }
}
