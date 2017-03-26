package br.com.intelipost.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;

import br.com.intelipost.model.resource.UserResource;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UserSteps {
	
	UserResource userResource;
	private String URL;
	HttpEntity<String> request;
	UserResource response;
	final TestRestTemplate testeRest = new TestRestTemplate("Inteli", "Post");
	
	@Given("^Chamarmos o servico de login$")
	public void chamarmos_o_servico_de_login() throws Throwable {
		URL = "http://localhost:8090/Intelipost/api/v1/users/login";
	}

	@When("^Eu passar o username teste@gmail\\.com e o password teste$")
	public void eu_passar_o_username_teste_gmail_com_e_o_password_teste() throws Throwable {
		userResource = new UserResource();
		userResource.setUsername("teste@gmail.com");
		userResource.setPassword("teste");
	}

	@Then("^Eu verifico se retornou o usuario teste@gmail\\.com$")
	public void eu_verifico_se_retornou_o_usuario_teste_gmail_com() throws Throwable {
		response  =  testeRest.postForObject(URL, userResource, UserResource.class);
		System.out.println(response);
		assertNotNull(response);
		assertNotNull(response.getUsername());
		assertEquals("teste@gmail.com", response.getUsername());
	}

	@When("^Eu passar o username erro@email\\.com e o password error$")
	public void eu_passar_o_username_erro_email_com_e_o_password_error() throws Throwable {
		userResource = new UserResource();
		userResource.setUsername("erro@gmail.com");
		userResource.setPassword("error");
	}
	
	@Then("^Eu verifico se retornou o usuario null$")
	public void eu_verifico_se_retornou_o_usuario_null() throws Throwable {
		response  =  testeRest.postForObject(URL, userResource, UserResource.class);
		assertNull(response);
	}

}