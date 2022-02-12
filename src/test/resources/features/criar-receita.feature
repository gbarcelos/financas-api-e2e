@Receita
Feature: Criar uma receita

  Scenario: Criar uma receita valida
    Given uma receita valida
    When criar uma receita
    Then verificar a resposta do metodo criar uma receita

  Scenario: Criar uma receita com a mesma descricao e no mesmo mes
    Given uma receita valida e criada
    When tentar criar uma receita com a mesma descricao e no mesmo mes
    Then verificar a resposta de receita ja existe

  Scenario: Criar uma receita com o conteudo como xml content type
    Given uma receita valida
    When tentar criar uma receita com o conteudo como xml content type
    Then verificar a resposta unsupported media type

  Scenario: Criar uma receita com uma descricao nulla
    Given uma receita valida
    When tentar criar uma receita com uma descricao nulla
    Then verificar a resposta de descricao obrigatoria

  Scenario: Criar uma receita com uma descricao em branco
    Given uma receita valida
    When tentar criar uma receita com uma descricao em branco
    Then verificar a resposta de descricao obrigatoria

  Scenario: Criar uma receita com uma data nulla
    Given uma receita valida
    When tentar criar uma receita com uma data nulla
    Then verificar a resposta de data obrigatoria

  Scenario: Criar uma receita com uma data invalida
    Given uma receita valida
    When tentar criar uma receita com uma data invalida
    Then verificar a resposta de data com formato invalido

  Scenario: Criar uma receita com um valor nullo
    Given uma receita valida
    When tentar criar uma receita com um valor nullo
    Then verificar a resposta de valor obrigatorio