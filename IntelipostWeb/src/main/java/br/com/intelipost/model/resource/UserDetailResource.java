package br.com.intelipost.model.resource;

import org.springframework.hateoas.core.Relation;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Relation(value="userDatail", collectionRelation="userDatails")
public class UserDetailResource {
	
	private String name;
	private String description;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}