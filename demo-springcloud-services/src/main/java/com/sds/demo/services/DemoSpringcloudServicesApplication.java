package com.sds.demo.services;

import com.sds.common.module.RestResponse;
import com.sds.common.module.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class DemoSpringcloudServicesApplication {
	private static final Logger logger = LoggerFactory.getLogger(DemoSpringcloudServicesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringcloudServicesApplication.class, args);
	}

	@Value("${server.port}")
	String port;

	@RequestMapping("/hi")
	public String home(@RequestParam(value = "name", defaultValue = "forezp") String name) {
		System.out.println("服务调用：" + name);
		return "hi " + name + " ,i am from port:" + port;
	}


	@RequestMapping(value="/save",method = RequestMethod.POST)
	public RestResponse saveUser(@RequestBody User user){
		logger.info("保存用户：{}",user);
		return RestResponse.success("OK");
	}
}
