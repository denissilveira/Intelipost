package br.com.intelipost.model.mapper;

import br.com.intelipost.model.resource.UserDetailResource;
import br.com.intelipost.model.resource.UserModel;
import br.com.intelipost.model.resource.UserResource;

public class UserMapper {
	
	public static final UserModel parse(final UserResource userResource) {
		
		UserModel user = null;
		
		if(userResource != null) {
			user = new UserModel();
			user.setPassword(userResource.getPassword());
			user.setUsername(userResource.getUsername());
			if(userResource.getDetailUserResource() != null) {
				user.setName(userResource.getDetailUserResource().getName());
				user.setDescription(userResource.getDetailUserResource().getDescription());
			}
		}
		return user;
	}
	
	public static final UserResource parse(final UserModel user) {
		
		UserResource userResource = null;
		
		if(user != null) {
			userResource = new UserResource();
			userResource.setPassword(user.getPassword());
			userResource.setUsername(user.getUsername());
			userResource.setDetailUserResource(new UserDetailResource());
			userResource.getDetailUserResource().setDescription(user.getDescription());
			userResource.getDetailUserResource().setName(user.getName());
		}
		return userResource;
	}

}