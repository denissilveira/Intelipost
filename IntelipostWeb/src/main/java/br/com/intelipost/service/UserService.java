package br.com.intelipost.service;

import br.com.intelipost.model.resource.UserResource;

public interface UserService {

	UserResource login(final UserResource user);
	
	UserResource save(final UserResource user);
}