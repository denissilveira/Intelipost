package br.com.intelipost.repository.impl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import br.com.intelipost.model.resource.UserResource;
import br.com.intelipost.repository.UserRedisRepository;

@Repository("userResourceRepository")
@SuppressWarnings({"rawtypes", "unchecked"})
public class UserRedisRepositoryImpl implements UserRedisRepository {
	
	private static final String KEY = "User";
	@Autowired
	private RedisTemplate redisTemplate;
    private HashOperations<String, String, UserResource> hashOps;
    
	@PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }

	@Override
	public void save(final UserResource user) {
		hashOps.put(KEY, user.getUsername()+":"+user.getPassword(), user);
	}

	@Override
	public void update(final UserResource user) {
		hashOps.put(KEY, user.getUsername()+":"+user.getPassword(), user);
	}

	@Override
	public UserResource find(final UserResource user) {
		return (UserResource) hashOps.get(KEY, user.getUsername()+":"+user.getPassword());
	}

	@Override
	public void delete(final UserResource user) {
		hashOps.delete(KEY, user.getUsername()+":"+user.getPassword(), user);
	}

}