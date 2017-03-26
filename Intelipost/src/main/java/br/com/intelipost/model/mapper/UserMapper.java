package br.com.intelipost.model.mapper;

import br.com.intelipost.model.entity.User;
import br.com.intelipost.model.resource.UserResource;

public class UserMapper {
	
	public static final User parse(final UserResource userResource) {
		
		User user = null;
		
		if(userResource != null) {
			user = new User();
			user.setPassword(userResource.getPassword());
			user.setUsername(userResource.getUsername());
		}
		return user;
	}
	
	public static final UserResource parse(final User user) {
		
		UserResource userResource = null;
		
		if(user != null) {
			userResource = new UserResource();
			userResource.setPassword(user.getPassword());
			userResource.setUsername(user.getUsername());
		}
		return userResource;
	}

}
