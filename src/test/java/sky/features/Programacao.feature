#language: pt
#Author: sandro.rodrigues

Funcionalidade: Programação Sky
	Como um usuário
	Eu quero acessar o menu de programação
	Para realizar as devidas validações

  @done
  Cenario: Validar programação atual
  	Dado que usuario esteja na pagina inicial da Sky
  	Quando acessar o menu de Programacao
		E selecionar a programacao atual
		Entao deve validar o titulo da programacao e horarios na modal