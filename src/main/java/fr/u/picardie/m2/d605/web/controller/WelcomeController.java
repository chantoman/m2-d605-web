package fr.u.picardie.m2.d605.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.webflow.execution.FlowExecutionOutcome;
import org.springframework.webflow.mvc.servlet.AbstractFlowHandler;

@Controller
@Component
public class WelcomeController{

	public String login() {
		return "login";
	}

	@RequestMapping("/")
	public String index(Principal principal) {
		return principal != null ? welcome() : login();
	}

	public String welcome() {
		return "welcome";
	}

	public boolean init() {
		return true;
	}


//	@Override
//	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ModelAndView model=new ModelAndView( request.getUserPrincipal() != null ? welcome() : login());
//		
//		return model;
//	}
}
