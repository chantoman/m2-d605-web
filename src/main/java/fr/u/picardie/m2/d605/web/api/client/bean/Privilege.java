package fr.u.picardie.m2.d605.web.api.client.bean;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

public class Privilege implements GrantedAuthority, Serializable {
	private String id;
	private String name;
	private String group;

	public Privilege(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Privilege() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return this.getName();
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

}
