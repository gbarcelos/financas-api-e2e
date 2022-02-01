Feature: Alterar uma despesa

  Scenario: Criar uma despesa e depois atualiza-la
    Given uma despesa valida e criada
    When atualizar a despesa
    Then verificar se a despesa foi atualizda