package br.com.intelipost.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.intelipost.model.entity.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, BigInteger>{
}