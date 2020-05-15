package com.asif.flightreservation.entities;

import java.util.Set;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role extends AbstractEntity implements GrantedAuthority {
	
	private String name;
	private Set<User> user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUser() {
		return user;
	}

	public void setUser(Set<User> user) {
		this.user = user;
	}

	@Override
	public String getAuthority() {
		
		return name;
	}
	
	
}
