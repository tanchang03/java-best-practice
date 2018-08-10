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

- 统一请求日志输出

  * WebLogFilter的使用

  * 所有配置过滤器可管控的请求都会输出一下日志：
  
```text

16:41:38.443 [http-nio-8080-exec-1] INFO  c.s.p.application.util.WebLogFilter - Http请求 [0:0:0:0:0:0:0:1][7104E2E314D5F8A2C611E9B724D6D0D8]http://localhost:8080/api/users 
16:41:38.443 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter - ContentType：null
16:41:38.443 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter - Method:GET
16:41:38.443 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter - Encoding:UTF-8
16:41:38.443 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter - Headers:
16:41:38.444 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter -    host:localhost:8080
16:41:38.444 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter -    connection:keep-alive
16:41:38.444 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter -    cache-control:max-age=0
16:41:38.444 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter -    upgrade-insecure-requests:1
16:41:38.444 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter -    user-agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.84 Safari/537.36
16:41:38.444 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter -    accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
16:41:38.444 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter -    accept-encoding:gzip, deflate, br
16:41:38.444 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter -    accept-language:zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7
16:41:38.444 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter -    cookie:Idea-d7895867=8c942b66-0f9b-4122-9f2b-db30da01056f; jenkins-timestamper-offset=-28800000; JSESSIONID=8834E4C74E28C85745C1041F19292972
16:41:38.445 [http-nio-8080-exec-1] INFO  c.s.p.application.util.WebLogFilter - Params：
16:41:38.480 [http-nio-8080-exec-1] INFO  o.h.h.i.QueryTranslatorFactoryInitiator - HHH000397: Using ASTQueryTranslatorFactory
Hibernate: select userentity0_.id as id1_1_, userentity0_.create_time as create_t2_1_, userentity0_.deleted as deleted3_1_, userentity0_.modify_time as modify_t4_1_, userentity0_.sortno as sortno5_1_, userentity0_.version as version6_1_, userentity0_.birthday as birthday7_1_, userentity0_.name as name8_1_, userentity0_.org_id as org_id11_1_, userentity0_.sex as sex9_1_, userentity0_.user_name as user_na10_1_, userentity0_1_.account263 as account1_2_, userentity0_2_.openid as openid1_3_, case when userentity0_1_.id is not null then 1 when userentity0_2_.id is not null then 2 when userentity0_.id is not null then 0 end as clazz_ from tb_user userentity0_ left outer join tb_user_manager userentity0_1_ on userentity0_.id=userentity0_1_.id left outer join tb_user_web userentity0_2_ on userentity0_.id=userentity0_2_.id where ( userentity0_.deleted=0)
16:41:38.650 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.RestResponse - 输出响应：{"success":true,"errorCode":null,"errorMsg":null,"data":[{"id":"40288286652275b201652275c3b10000","createTime":1533881467000,"modifyTime":null,"deleted":false,"version":0,"sortNo":0,"userName":"tanchang","name":"tc","birthday":null,"sex":null,"org":null,"openid":"openid"},{"id":"40288286652278320165227843260000","createTime":1533881631000,"modifyTime":null,"deleted":false,"version":0,"sortNo":0,"userName":"tanchang","name":"tc","birthday":null,"sex":null,"org":null,"openid":"openid"},{"id":"4028828665227f860165227f98170000","createTime":1533882112000,"modifyTime":null,"deleted":false,"version":0,"sortNo":0,"userName":"tanchang","name":"tc","birthday":null,"sex":null,"org":null,"openid":"openid"},{"id":"402882866522807d016522808b650000","createTime":1533882174000,"modifyTime":null,"deleted":false,"version":0,"sortNo":0,"userName":"tanchang","name":"tc","birthday":null,"sex":null,"org":null,"openid":"openid"}]}
16:41:38.684 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter - 响应类型：application/json;charset=UTF-8
16:41:38.685 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter - 请求返回：{"success":true,"errorCode":null,"errorMsg":null,"data":[{"id":"40288286652275b201652275c3b10000","createTime":"2018-08-10T06:11:07.000+0000","modifyTime":null,"deleted":false,"version":0,"sortNo":0,"userName":"tanchang","name":"tc","birthday":null,"sex":null,"org":null,"openid":"openid"},{"id":"40288286652278320165227843260000","createTime":"2018-08-10T06:13:51.000+0000","modifyTime":null,"deleted":false,"version":0,"sortNo":0,"userName":"tanchang","name":"tc","birthday":null,"sex":null,"org":null,"openid":"openid"},{"id":"4028828665227f860165227f98170000","createTime":"2018-08-10T06:21:52.000+0000","modifyTime":null,"deleted":false,"version":0,"sortNo":0,"userName":"tanchang","name":"tc","birthday":null,"sex":null,"org":null,"openid":"openid"},{"id":"402882866522807d016522808b650000","createTime":"2018-08-10T06:22:54.000+0000","modifyTime":null,"deleted":false,"version":0,"sortNo":0,"userName":"tanchang","name":"tc","birthday":null,"sex":null,"org":null,"openid":"openid"}]}
16:41:38.685 [http-nio-8080-exec-1] DEBUG c.s.p.application.util.WebLogFilter - 请求耗时[271ms]=>/api/users

```
- [统一请求返回对象RestResponse](https://github.com/tanchang03/java-best-practice/wiki/%E7%BB%9F%E4%B8%80%E5%93%8D%E5%BA%94)
```java
     @RequestMapping("/api/users")
     public RestResponse<List<UserEntity>> list(){
         return RestResponse.success(userService.list("from UserEntity obj"));
     }
```
响应体结构如下：
```text
{
    "success":true,
    "errorCode":null,
    "errorMsg":null,
    "data":"返回对象"
}
```

- [如何部署SPRINGBOOT应用到DOCKER](https://github.com/tanchang03/java-best-practice/wiki/SPRINGBOOT-%E5%BA%94%E7%94%A8%E9%83%A8%E7%BD%B2-DOCKER-%E5%AE%9E%E8%B7%B5)
