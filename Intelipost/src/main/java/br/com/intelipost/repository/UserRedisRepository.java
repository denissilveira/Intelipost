package br.com.intelipost.repository;

import br.com.intelipost.model.resource.UserResource;

public interface UserRedisRepository {
	
	void save(final UserResource user);
	
	void update(final UserResource user);
	
	UserResource find(final UserResource user);
	
	void delete(final UserResource user);
	
}