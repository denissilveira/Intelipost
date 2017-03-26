package br.com.intelipost.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.intelipost.model.resource.UserResource;
import br.com.intelipost.service.UserService;
import br.com.intelipost.web.client.UserClient;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserClient userClient;

	@Override
	public UserResource login(UserResource user) {
		return userClient.login(user);
	}

	@Override
	public UserResource save(final UserResource user) {
		return userClient.save(user);
	}

}