package br.com.intelipost.model.mapper;

import br.com.intelipost.model.entity.UserDetail;
import br.com.intelipost.model.resource.UserDetailResource;

public class UserDetailMapper {
	
	public static final UserDetail parse(final UserDetailResource userDetailResource) {
		
		UserDetail userDetail = null;
		
		if(userDetailResource != null) {
			userDetail = new UserDetail();
			userDetail.setDescription(userDetailResource.getDescription());
			userDetail.setName(userDetailResource.getName());
		}
		
		return userDetail;
	}
	
	public static final UserDetailResource parse(final UserDetail userDetail) {
		
		UserDetailResource userDetailResource = null;
		
		if(userDetail != null) {
			userDetailResource = new UserDetailResource();
			userDetailResource.setDescription(userDetail.getDescription());
			userDetailResource.setName(userDetail.getName());
		}
		
		return userDetailResource;
	}

}
