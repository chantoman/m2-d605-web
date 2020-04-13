package fr.u.picardie.m2.d605.web.api.client.services;

import org.springframework.beans.factory.annotation.Qualifier;
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

import fr.u.picardie.m2.d605.web.api.client.bean.User;

//@FeignClient("netflix-zuul-api-gateway-server")
@FeignClient(contextId = "userApiService", name = "securite-service")
@RibbonClient("securite-service")
@Qualifier("userApiService")
@Component
public interface UserApiService {

	@GetMapping("/users/search/findByEmail")
	public User findByEmail(@RequestParam("email") String email);

	@GetMapping("/users/search/findByLogin")
	public User findByLogin(@RequestParam("login") String login);

	@GetMapping("/users")
	public CollectionModel<User> findAll();

	@PostMapping("/users")
	public User saveUser(@RequestBody User user);

	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") String id);

	@GetMapping("/users/{id}")
	public User findByIdUser(@PathVariable("id") String id);

	@GetMapping(path = "{resource}")
	User getUserByResource(@PathVariable("resource") String resource);

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") String id);
}
