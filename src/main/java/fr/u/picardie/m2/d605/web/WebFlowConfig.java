package fr.u.picardie.m2.d605.web;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.convert.service.DefaultConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.webflow.config.AbstractFlowConfiguration;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.ViewFactoryCreator;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.builder.MvcViewFactoryCreator;
import org.springframework.webflow.mvc.servlet.FlowController;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;
import org.springframework.webflow.security.SecurityFlowExecutionListener;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.webflow.view.AjaxThymeleafViewResolver;
import org.thymeleaf.spring5.webflow.view.FlowAjaxThymeleafView;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class WebFlowConfig extends AbstractFlowConfiguration {
	@Autowired
	private LocalValidatorFactoryBean localValidatorFacotryBean;

	@Bean
	public FlowDefinitionRegistry flowRegistry() {
		return getFlowDefinitionRegistryBuilder() //
				.setBasePath("classpath:flows") //
				.addFlowLocationPattern("/**/*.xml") //
				.setFlowBuilderServices(this.flowBuilderServices()) //
				.build();
	}

	@Bean
	public FlowExecutor flowExecutor() {
		return getFlowExecutorBuilder(this.flowRegistry()).addFlowExecutionListener(new SecurityFlowExecutionListener()) //
				.build();
	}

	@Bean
	public FlowBuilderServices flowBuilderServices() {
		return getFlowBuilderServicesBuilder() //
				.setViewFactoryCreator(this.mvcViewFactoryCreator()) // Important!
				.setValidator(this.localValidatorFacotryBean)
				.build();
	}
	// ----------------------------------------------------------

//	@Bean
//	public DefaultFlowUrlHandler  flowHandlerMapping() {
//		Map<String, Controller> mapMapping = new HashMap<String, Controller>();
//		mapMapping.put("/*", flowController());
//		DefaultFlowUrlHandler  handlerMapping = new DefaultFlowUrlHandler();
//		return handlerMapping;
//	}
//	@Bean
//	public SimpleUrlHandlerMapping flowHandlerMapping() {
//		Map<String, Controller> mapMapping = new HashMap<String, Controller>();
//		mapMapping.put("/*", flowController());
//		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping(mapMapping);
//		handlerMapping.setDefaultHandler(new DefaultFlowUrlHandler());
//		handlerMapping.setOrder(-1);
//		return handlerMapping;
//	}

	@Bean
	public FlowHandlerMapping flowHandlerMapping() {
		FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
		handlerMapping.setOrder(-1);
		handlerMapping.setFlowRegistry(this.flowRegistry());
		return handlerMapping;
	}

	@Bean
	public FlowController flowController() {
		FlowController flowController = new FlowController();
		flowController.setFlowExecutor(this.flowExecutor());
		return flowController;
	}

	@Bean
	public FlowHandlerAdapter flowHandlerAdapter() {
		FlowHandlerAdapter handlerAdapter = new FlowHandlerAdapter();
		handlerAdapter.setFlowExecutor(this.flowExecutor());
		handlerAdapter.setSaveOutputToFlashScopeOnRedirect(true);
		return handlerAdapter;
	}

	@Bean
	public ViewFactoryCreator mvcViewFactoryCreator() {
		MvcViewFactoryCreator factoryCreator = new MvcViewFactoryCreator();
		factoryCreator.setViewResolvers(Collections.singletonList(this.thymeleafViewResolver()));
		factoryCreator.setUseSpringBeanBinding(true);
		return factoryCreator;
	}

	@Bean
	@Description("Thymeleaf AJAX view resolver for Spring WebFlow")
	public AjaxThymeleafViewResolver thymeleafViewResolver() {
		AjaxThymeleafViewResolver viewResolver = new AjaxThymeleafViewResolver();
		viewResolver.setViewClass(FlowAjaxThymeleafView.class);
		viewResolver.setTemplateEngine(this.templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");
		return viewResolver;
	}

	@Bean
	@Description("Thymeleaf template resolver serving HTML 5")
	public ClassLoaderTemplateResolver templateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix("templates/");
		templateResolver.setCacheable(false);
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCharacterEncoding("UTF-8");
		return templateResolver;
	}

	@Bean
	@Description("Thymeleaf template engine with Spring integration")
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(this.templateResolver());
		templateEngine.addDialect(new LayoutDialect());
		return templateEngine;
	}
}
