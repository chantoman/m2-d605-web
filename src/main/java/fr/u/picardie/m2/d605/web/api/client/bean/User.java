package fr.u.picardie.m2.d605.web.api.client.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User extends RepresentationModel<User> implements UserDetails, Serializable {

	private String id;
	private String extref;
	@NotBlank
	@Length(min = 4)
	private String login;
	private Boolean provided;
	private String provider;
	@NotBlank
	private String lastName;
	@NotBlank
	private String firstName;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Length(min = 4)
	private String password;
	private String confirmedPassword;
	private boolean enabled;
	private boolean tokenExpired;
	private List<Role> roles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isTokenExpired() {
		return tokenExpired;
	}

	public void setTokenExpired(boolean tokenExpired) {
		this.tokenExpired = tokenExpired;
	}

	public Boolean getProvided() {
		return provided;
	}

	public void setProvided(Boolean provided) {
		this.provided = provided;
	}

	public String getExtref() {
		return extref;
	}

	public void setExtref(String extref) {
		this.extref = extref;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<Privilege> privilege = new ArrayList<Privilege>();
		if (getRoles() != null) {
			getRoles().stream().map(r -> r.getPrivileges()).forEach(p -> privilege.addAll(p));
		}
		return privilege;
	}

	@Override
	public String getUsername() {

		return this.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public String getConfirmedPassword() {
		return confirmedPassword;
	}

	public void setConfirmedPassword(String confirmedPassword) {
		this.confirmedPassword = confirmedPassword;
	}

	public User() {
		super();
	}

}
