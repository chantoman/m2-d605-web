package fr.u.picardie.m2.d605.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageContext;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.ModelAndView;

import feign.FeignException.Conflict;
import fr.u.picardie.m2.d605.web.api.client.bean.Role;
import fr.u.picardie.m2.d605.web.api.client.services.RoleApiService;
import fr.u.picardie.m2.d605.web.exception.ServiceException;

@Controller
@RequestMapping("roles")
public class RoleController extends FormContentFilter {
	@Autowired
	private RoleApiService roleApiService;

	@GetMapping
	public ModelAndView allRoles() {
		ModelAndView model = new ModelAndView("roles/list");
		CollectionModel<Role> roles = roleApiService.findAll();
		model.addObject("allBeans", roles);
		return model;
	}

	@GetMapping("delete/{id}")
	public ModelAndView updateRole(@PathVariable("id") String id) {
		try {
			roleApiService.deleteRole(id);
		} catch (Exception e) {
		}
		return allRoles();
	}

	public Role createNew() {
		return new Role();
	}

	public Role save(Role role, MessageContext context) throws Exception {
		try {
			Role usr = roleApiService.saveRole(role);
			return usr;
		} catch (Conflict e) {
			throw e;
		} catch (Throwable e) {
			throw new ServiceException(e);
		}
	}

	public Role findById(String id) {
		try {
			return roleApiService.findByIdRole(id);
		} catch (Exception e) {
			return null;
		}
	}

	public Role update(@Valid Role role, MessageContext context) throws Exception{
		try {
			Role usr = roleApiService.updateRole(role, role.getExtref());
			return usr;
		} catch (Conflict e) {
			throw e;
		} catch (Throwable e) {
			throw new ServiceException(e);
		}
	}
}
