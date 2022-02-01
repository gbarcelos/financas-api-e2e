package br.com.oak.financas.api.test.fixtures;

import br.com.oak.financas.api.test.model.input.DespesaInput;
import br.com.oak.financas.api.test.model.input.LancamentoInput;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;

public class FinancasTestFixture {

  public static LancamentoInput buildReceitaInput() {
    return LancamentoInput.builder()
        .descricao("Descricao de uma receita ".concat(RandomStringUtils.random(10, true, false)))
        .data("01/01/2022")
        .valor(new BigDecimal("1500.45"))
        .build();
  }

  public static DespesaInput buildDespesaInput() {
    return DespesaInput.builder()
        .descricao("Descricao de uma despesa ".concat(RandomStringUtils.random(10, true, false)))
        .data("01/01/2022")
        .valor(new BigDecimal("1500.45"))
        .build();
  }
}
