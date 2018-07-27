package com.sds.practice.domain.service;

import com.sds.practice.domain.entity.UserEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleDomainServiceApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSaveUser(){
		UserEntity user = UserEntity.builder().userName("tanchang")
				.name("谭畅").birthday(new Date()).build();
		user = userService.save(user);
		Assert.assertNotNull(user.getId());
		String userid = user.getId();
		user = userService.findOne(userid);
		userService.logicDelete(user);
		user = userService.findOne(userid);
		Assert.assertNull(user);
	};

	@Test
	public void testBatchSave(){
		List<UserEntity> users = new ArrayList<>();
		for(int i=0;i<10000;i++){
			UserEntity user = UserEntity.builder().userName("tanchang")
					.name("谭畅").birthday(new Date()).build();
			users.add(user);
		}
		long dt = System.currentTimeMillis();
		userService.saveAll(users);
		System.out.println("保存成功，共耗时："+(System.currentTimeMillis() - dt) + "ms");
		Assert.assertTrue(userService.count()>=100);
	}



}
