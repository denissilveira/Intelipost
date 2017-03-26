package br.com.intelipost.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.intelipost.model.entity.User;

public interface UserRepository extends JpaRepository<User, BigInteger>{
	
	User findByUsernameAndPassword(final String username, final String password);

	User findByUsername(final String username);
}