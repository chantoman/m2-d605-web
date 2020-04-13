package fr.u.picardie.m2.d605.web.controller;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.ModelAndView;

import feign.FeignException.Conflict;
import fr.u.picardie.m2.d605.web.api.client.bean.Role;
import fr.u.picardie.m2.d605.web.api.client.bean.User;
import fr.u.picardie.m2.d605.web.api.client.services.UserApiService;
import fr.u.picardie.m2.d605.web.exception.ServiceException;

@Controller
@RequestMapping("users")
public class UserController extends FormContentFilter {
	@Autowired
	private UserApiService userApiService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping
	public ModelAndView allUsers() {
		ModelAndView model = new ModelAndView("users/list");
		CollectionModel<User> users = userApiService.findAll();
		model.addObject("allUsers", users);
		return model;
	}

	@GetMapping("delete/{id}")
	public ModelAndView updateUser(@PathVariable("id") String id) {
		try {
			userApiService.deleteUser(id);
		} catch (Exception e) {
		}
		return allUsers();
	}

	public User createNew() {
		return new User();
	}

	public User findById(String id) {
		try {
			return userApiService.findByIdUser(id);
		} catch (Exception e) {
			return null;
		}
	}

	public User save(User user, MessageContext context) throws Exception {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		try {
			User usr = userApiService.saveUser(user);
			return usr;
		} catch (Conflict e) {
			throw e;
		} catch (Throwable e) {
			throw new ServiceException(e);
		}
	}

	public User update(@Valid User user, MessageContext context) throws Exception {
		try {
			User usr = userApiService.updateUser(user, user.getExtref());
			return usr;
		} catch (Throwable e) {
			throw new ServiceException(e);
		}
	}
}
