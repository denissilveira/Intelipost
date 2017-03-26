package br.com.intelipost.model.resource;

import java.io.Serializable;

import org.springframework.hateoas.core.Relation;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
@Relation(value="user", collectionRelation="users")
public class UserResource implements Serializable {

	private String username;
	private String password;
	private UserDetailResource detailUserResource;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDetailResource getDetailUserResource() {
		return detailUserResource;
	}
	public void setDetailUserResource(UserDetailResource detailUserResource) {
		this.detailUserResource = detailUserResource;
	}

}