Feature: Criar uma despesa

  Scenario: Criar uma despesa valida
    Given uma despesa valida
    When criar uma despesa
    Then verificar a resposta do metodo criar uma despesa

  Scenario: Criar uma despesa com a mesma descricao e no mesmo mes
    Given uma despesa valida e criada
    When tentar criar uma despesa com a mesma descricao e no mesmo mes
    Then verificar a resposta de despesa ja existe

  Scenario: Criar uma despesa com uma descricao nulla
    Given uma despesa valida
    When tentar criar uma despesa com uma descricao nulla
    Then verificar a resposta de descricao obrigatoria

  Scenario: Criar uma despesa com uma descricao em branco
    Given uma despesa valida
    When tentar criar uma despesa com uma descricao em branco
    Then verificar a resposta de descricao obrigatoria

  Scenario: Criar uma despesa com uma data nulla
    Given uma despesa valida
    When tentar criar uma despesa com uma data nulla
    Then verificar a resposta de data obrigatoria

  Scenario: Criar uma despesa com uma data invalida
    Given uma despesa valida
    When tentar criar uma despesa com uma data invalida
    Then verificar a resposta de data com formato invalido

  Scenario: Criar uma despesa com um valor nullo
    Given uma despesa valida
    When tentar criar uma despesa com um valor nullo
    Then verificar a resposta de valor obrigatorio