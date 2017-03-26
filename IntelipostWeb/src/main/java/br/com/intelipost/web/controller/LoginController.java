package br.com.intelipost.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.intelipost.model.mapper.UserMapper;
import br.com.intelipost.model.resource.UserModel;
import br.com.intelipost.model.resource.UserResource;
import br.com.intelipost.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.POST)
	public ModelAndView login(@ModelAttribute UserModel user, BindingResult bindingResult, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", new UserModel());
		if (bindingResult.hasErrors()) {
			user.setErrorMessage("Erro ao efetuar o login");
			modelAndView.addObject("errorMessage", user.getErrorMessage());
			bindingResult.rejectValue("errorMessage", "Erro ao efetuar o login");
			modelAndView.setViewName("login");
		} else {
			UserResource userExists = userService.login(UserMapper.parse(user));
			if (userExists != null) {
				user.setSuccessMessage("Seja bem vindo " + user.getUsername());
				modelAndView.addObject("successMessage", user.getSuccessMessage());
				modelAndView.addObject("user", UserMapper.parse(userExists));
				modelAndView.setViewName("admin/home");
			} else {
				user.setErrorMessage("Usuário e/ou senha inválidos");
				modelAndView.addObject("errorMessage", user.getErrorMessage());
				bindingResult.rejectValue("errorMessage", "Usuário e/ou senha inválidos");
				modelAndView.setViewName("login");
			}
		}
		return modelAndView;
	}
	

	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView login(final Model model){
		model.addAttribute("user", new UserModel());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", new UserModel());
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid UserModel user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		UserResource userExists = userService.login(UserMapper.parse(user));
		if (userExists != null) {
			user.setErrorMessage("Este usuário já existe");
			modelAndView.addObject("errorMessage", user.getErrorMessage());
			bindingResult.rejectValue("errorMessage", "Este usuário já existe");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("user", new UserModel());
			modelAndView.setViewName("registration");
		} else {
			userService.save(UserMapper.parse(user));
			user.setSuccessMessage("Usuário cadastrado com Sucesso");
			modelAndView.addObject("successMessage", user.getSuccessMessage());
			modelAndView.addObject("user", new UserModel());
			modelAndView.setViewName("login");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("successMessage", "CONTEUDO PRIVADO");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}


}