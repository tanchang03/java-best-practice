package com.sds.practice.domain.service;

import com.sds.practice.domain.entity.UserEntity;
import com.sds.practice.domain.entity.WebUserEntity;
import com.sds.practice.domain.repository.mysql.WebUserDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Project : best-practice-project
 * @Package Name : com.sds.practice.domain.service
 * @Description : TODO
 * @Author : tanchang
 * @Create Date : 2018年08月10日 下午1:27
 * @ModificationHistory Who   When     What
 * ------------    --------------    ---------------------------------
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class JPAInheritTest {

    @Autowired
    private UserService userService;

    @Autowired
    private WebUserDao webUserDao;

    @Test
    public void test001(){
        WebUserEntity webUserEntity = new WebUserEntity();
        webUserEntity.setOpenid("openid");
        webUserEntity.setUserName("tanchang");
        webUserEntity.setName("tc");
        webUserEntity = userService.saveWebUser(webUserEntity);

        String id = webUserEntity.getId();

        UserEntity userEntity = userService.findOne(id);
        Assert.assertNotNull(userEntity);

        WebUserEntity entity = webUserDao.findById(id).get();
        Assert.assertNotNull(entity);
    };

}
