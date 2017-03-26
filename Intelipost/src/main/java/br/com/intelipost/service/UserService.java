package br.com.intelipost.service;

import br.com.intelipost.model.resource.ReturnApiResource;
import br.com.intelipost.model.resource.UserResource;

public interface UserService {

	UserResource login(final UserResource userResource);
	
	ReturnApiResource save(final UserResource user);
}