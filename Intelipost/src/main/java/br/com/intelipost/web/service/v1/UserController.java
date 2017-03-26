package br.com.intelipost.web.service.v1;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.intelipost.model.resource.ReturnApiResource;
import br.com.intelipost.model.resource.UserResource;
import br.com.intelipost.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	private static final Logger logger = LogManager.getLogger(UserController.class.getName());
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "login", nickname = "login")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = UserResource.class),
            @ApiResponse(code = 401, message = "Unauthorized")}) 
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserResource> login(@RequestBody UserResource userResource) {
    	
		logger.info("invocada a API de login com o usuário: " + userResource.getUsername());
    	final UserResource user = userService.login(userResource);
    	
    	if (user != null) {
    		logger.info("Login efetuado com sucesso. Usuário: " + userResource.getUsername());
    		return new ResponseEntity<UserResource>(user, HttpStatus.OK);
		}
    	logger.info("Erro ao efetuar o login. Usuário: " + userResource.getUsername());
    	return new ResponseEntity<UserResource>(HttpStatus.UNAUTHORIZED);
	}
    
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Success", response = UserResource.class),
            @ApiResponse(code = 500, message = "Internal Server Error")}) 
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ReturnApiResource> create(@RequestBody UserResource userResource) {
    	
    	logger.info("Api de criacao de usuario invocada.");
    	final ReturnApiResource returnApi  = userService.save(userResource);
    	
    	if (returnApi.isSuccess()) {
    		logger.info("Usuario cadastrado com sucesso. Usuario: " + userResource.getUsername());
			return new ResponseEntity<ReturnApiResource>(returnApi , HttpStatus.CREATED);
		}
    	logger.info("Erro ao cadastrar o usuario. Usuario: " + userResource.getUsername());
    	return new ResponseEntity<ReturnApiResource>(returnApi, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}