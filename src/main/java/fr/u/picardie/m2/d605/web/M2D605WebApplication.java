package fr.u.picardie.m2.d605.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.config.EnableHypermediaSupport;

import fr.u.picardie.m2.d605.web.api.client.services.RoleApiService;
import fr.u.picardie.m2.d605.web.api.client.services.UserApiService;

@SpringBootApplication
@EnableFeignClients("fr.u.picardie.m2.d605.web")
@EnableEurekaClient
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
//@ImportResource({"classpath*:applicationContext.xml"})
public class M2D605WebApplication {

//	@Autowired
//	private UserApiService userApiService;
//	@Autowired
//	private RoleApiService roleApiService;
//	
	public static void main(String[] args) {
		SpringApplication.run(M2D605WebApplication.class, args);
	}

//	@Bean
//	public UserApiService userApiService() {
//		return userApiService;
//	}
//	@Bean
//	public RoleApiService roleApiService() {
//		return roleApiService;
//	}
//	@Bean
//	public SpringTemplateEngine templateEngine() {
//		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//		templateEngine.addDialect(new LayoutDialect());
//		return templateEngine;
//	}
}
