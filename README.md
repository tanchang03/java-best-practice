# Java应用基于springboot最佳实践

- Springboot基本框架搭建
- JPA基于H2的面向对象的模型应用案例
- 常用抽象基类BaseDao BaseService

- BaseService很多有用的方法
  * save 保存
  * list 列表
  * saveAll 高效的批量保存
  * findByCustomWithParams 支持动态参数的分页hql查询
  * truncate 清空数据
  * findByCustom常用的hql查询方法
  
- HqlUtil工具类 非常好用，代码可读性更高
```java

	@Test
	public void testFindCustom(){
		String hql = "from UserEntity obj where obj.userName = ?1 and obj.name= ?2";

		hql = HqlUtil.addCondition(hql,"createTime","2018-08-01",HqlUtil.LOGIC_AND,HqlUtil.TYPE_DATE,HqlUtil.COMPARECHAR_GREAT_EQ);
		hql = HqlUtil.addCondition(hql,"createTime","2018-08-10",HqlUtil.LOGIC_AND,HqlUtil.TYPE_DATE,HqlUtil.COMPARECHAR_LESS);
		List result = userService.findByCustomWithParams(hql,"tanchang","谭畅");
		Assert.assertTrue(result!= null);
		Assert.assertTrue(result.size() >= 1);
	}
```

  
