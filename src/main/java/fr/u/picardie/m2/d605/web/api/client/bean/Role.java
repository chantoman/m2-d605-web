package fr.u.picardie.m2.d605.web.api.client.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

public class Role implements Serializable {
	private String id;
	private String extref;
	@NotBlank
	private String name;
	private List<Privilege> privileges = new ArrayList<Privilege>();

	public Role(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Role() {
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

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public String getExtref() {
		return extref;
	}

	public void setExtref(String extref) {
		this.extref = extref;
	}

}
