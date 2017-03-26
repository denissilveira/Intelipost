@tag
Feature: User Tests
	Cenario para testes de usuario

@tag1
Scenario Outline: login
Given Chamarmos o servico de login
When Eu passar o username <username> e o password <password>
Then Eu verifico se retornou o usuario <return>

Examples:
    | username  |password | return |
    | teste@gmail.com |  teste   | teste@gmail.com |
    | erro@email.com |  error | null   |
