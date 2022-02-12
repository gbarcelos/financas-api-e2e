@Receita
Feature: Alterar uma receita

  Scenario: Criar uma receita e depois atualiza-la
    Given uma receita valida e criada
    When atualizar a receita
    Then verificar se a receita foi atualizda