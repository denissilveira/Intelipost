package br.com.intelipost.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.intelipost.model.entity.User;
import br.com.intelipost.model.entity.UserDetail;
import br.com.intelipost.model.mapper.UserDetailMapper;
import br.com.intelipost.model.mapper.UserMapper;
import br.com.intelipost.model.resource.ReturnApiResource;
import br.com.intelipost.model.resource.UserResource;
import br.com.intelipost.repository.UserDetailRepository;
import br.com.intelipost.repository.UserRedisRepository;
import br.com.intelipost.repository.UserRepository;
import br.com.intelipost.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class.getName());
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDetailRepository userDetailRepository;
	@Autowired
	private UserRedisRepository userRedisRepository;

	@Override
	public UserResource login(final UserResource userResource) {
		
		logger.info("Iniciando a busca de usuário no Redis. User: " + userResource.getUsername());
		UserResource ur = userRedisRepository.find(userResource);
		
		
		if(ur == null) {
			logger.info("A consulta via Redis não trouxe resultado.... Iniciando a busca de usuário no Banco de dados.");
			final User user = userRepository.findByUsernameAndPassword(userResource.getUsername(), 
					userResource.getPassword());
			
			if(user != null) {
				final UserDetail userDetail = userDetailRepository.findOne(user.getId());
				ur = UserMapper.parse(user);
				ur.setDetailUserResource(UserDetailMapper.parse(userDetail));
				logger.info("Usuário encontrado no banco de dados. User: " + userResource.getUsername());
				
				saveUserRedis(ur);
			}
		} 
		
		return ur;
	}

	@Override
	public ReturnApiResource save(final UserResource userResource) {
		
		final ReturnApiResource returnApi = new ReturnApiResource();
		
		try {
			logger.info("Iniciando o casdastro de usuário. User: " + userResource.getUsername());
			
			User user = userRepository.findByUsername(userResource.getUsername());
			if(user == null) {
				user = 	userRepository.save(UserMapper.parse(userResource));
				UserDetail userDetail= UserDetailMapper.parse(userResource.getDetailUserResource());
				userDetail.setUserId(user.getId());
				userDetailRepository.save(userDetail);
				
				final UserResource ur = UserMapper.parse(user);
				ur.setDetailUserResource(UserDetailMapper.parse(userDetail));	
				
				returnApi.setSuccess(true);
				returnApi.setMessage("Cadastro Realizado com Sucesso.");
				returnApi.setObject(ur);
				
				logger.info("Usuário cadastrado com Sucesso... User: " + ur.getUsername());
				saveUserRedis(ur);
				
			} else {
				
				returnApi.setSuccess(false);
				returnApi.setMessage("Usuário já cadastrado.");
				logger.info("Usuário já cadastrado no sistema. User: " + userResource.getUsername());
			}
			
		} catch (Exception e) {
			returnApi.setSuccess(false);
			returnApi.setMessage("Erro ao incluir o usuário: " + e.getMessage());
			logger.info("Erro ao persistir o usuário. ERROR: " + e.getMessage());
		}
		
		return returnApi;
	}
	
	private void saveUserRedis(final UserResource ur) {
		try {
			logger.info("Iniciando a persistencia do usuário no Redis. User: " + ur.getUsername());
			userRedisRepository.save(ur);
		} catch (Exception e) {
			logger.info("Erro ao persistir o usuário no Redis. ERROR: " + e.getMessage());
		}
	}

}