package fr.u.picardie.m2.d605.web.api.client.services;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import fr.u.picardie.m2.d605.web.api.client.bean.Role;

//@FeignClient("netflix-zuul-api-gateway-server")
@FeignClient(contextId = "roleApiService", name = "securite-service")
@RibbonClient("securite-service")
@Component
public interface RoleApiService {

	@GetMapping("/roles/search/findByName")
	public Role findByName(@RequestParam("name") String name);

	@GetMapping("/roles")
	public CollectionModel<Role> findAll();

	@PostMapping("/roles")
	public Role saveRole(@RequestBody Role role);

	@PutMapping("/roles/{id}")
	public Role updateRole(@RequestBody Role role, @PathVariable("id") String id);

	@GetMapping("/roles/{id}")
	public Role findByIdRole(@PathVariable("id") String id);

	@DeleteMapping("/roles/{id}")
	public void deleteRole(@PathVariable("id") String id);
}
