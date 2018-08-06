package com.sds.practice.domain.service;

import com.sds.practice.domain.entity.OrganizationEntity;
import com.sds.practice.domain.entity.UserEntity;
import com.sds.practice.helper.HqlUtil;
import com.sds.practice.helper.StringUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

	@Autowired
	private OrganizationService organizationService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSaveUser(){
	};

	/**
	 * 逻辑删除案例
	 */
	@Test
	public void testDeleteUser(){
		UserEntity user = new UserEntity();
		user.setUserName("tanchang");
		user.setName("谭畅");
		user.setBirthday(new Date());
		user = userService.save(user);
		String userid = user.getId();
		user = userService.findOne(userid);
		userService.logicDelete(user);
		user = userService.findOne(userid);
		Assert.assertNull(user);
	}


	@Before
	public void prepareData(){
		UserEntity user = new UserEntity();
		user.setUserName("tanchang");
		user.setName("谭畅");
		user.setBirthday(new Date());

		OrganizationEntity org = OrganizationEntity.builder().name("集团公司").code("00001").build();
		OrganizationEntity org01 = OrganizationEntity.builder().name("研发部").code("00011").parent(org).build();
		OrganizationEntity org02 = OrganizationEntity.builder().name("销售部").code("00012").parent(org).build();
		organizationService.save(org);
		organizationService.save(org01);
		organizationService.save(org02);

		user.setOrg(org01);
		user = userService.save(user);

		Assert.assertNotNull(user.getId());
	}

	@After
	public void destory(){
		userService.truncate();
		organizationService.truncate();
	}

	/**
	 *批量保存案例
	 */
	@Test
	public void testBatchSave(){
		List<UserEntity> users = new ArrayList<>();
		for(int i=0;i<100;i++){
			UserEntity user = new UserEntity();
			user.setUserName("tanchang");
			user.setName("谭畅");
			user.setBirthday(new Date());
			users.add(user);
		}
		long dt = System.currentTimeMillis();
		userService.saveAll(users);
		System.out.println("保存成功，共耗时："+(System.currentTimeMillis() - dt) + "ms");
		Assert.assertTrue(userService.count()>=100);
	}


	/**
	 * 查询部分属性的方法
	 */
	@Test
	public void testSelectCustomAttributes(){
		List<UserEntity> usersList =userService.getPageList("select new com.sds.practice.domain.entity.UserEntity(obj.userName,obj.name) from UserEntity obj",true,0,10);
		System.out.println(usersList);
		Assert.assertTrue(usersList != null);
		Assert.assertTrue(StringUtil.isNotEmpty((usersList.get(0)).getName()));
		Assert.assertTrue(null == usersList.get(0).getId());
	}


	/**
	 * 自定义条件查询
	 * 按日期
	 */
	@Test
	public void testFindCustom(){
		String hql = "from UserEntity obj where obj.userName = ?1 and obj.name= ?2";

		hql = HqlUtil.addCondition(hql,"createTime","2018-08-01",HqlUtil.LOGIC_AND,HqlUtil.TYPE_DATE,HqlUtil.COMPARECHAR_GREAT_EQ);
		hql = HqlUtil.addCondition(hql,"createTime","2018-08-10",HqlUtil.LOGIC_AND,HqlUtil.TYPE_DATE,HqlUtil.COMPARECHAR_LESS);
		List result = userService.findByCustomWithParams(hql,"tanchang","谭畅");
		Assert.assertTrue(result!= null);
		Assert.assertTrue(result.size() >= 1);
	}


	/**
	 * 关联对象查询
	 */
	@Test
	public void testFindByGroup(){

		String hql = "from UserEntity obj where obj.org.name = ?1";
		List<UserEntity> result = userService.list(hql,"研发部");

		Assert.assertTrue(result.size() > 0);

		OrganizationEntity root = OrganizationEntity.builder().code("000033").name("相关部门").build();
		organizationService.save(root);

		hql = "from OrganizationEntity obj where obj.code = ?1";
		OrganizationEntity org = organizationService.findUnique(hql,"000033");
		Assert.assertNotNull(org);
		Assert.assertTrue("相关部门".equals(org.getName()));
	}


	@Test
	public void update(){
		String hql = "from OrganizationEntity obj where obj.code = ?1";
		OrganizationEntity org = organizationService.findUnique(hql,"00011");
		Assert.assertNotNull(org);
		org.setName("研发部修改");
		organizationService.save(org);

		org = organizationService.findUnique(hql,"00011");
		Assert.assertTrue(org.getName().equals("研发部修改"));
	}

}
