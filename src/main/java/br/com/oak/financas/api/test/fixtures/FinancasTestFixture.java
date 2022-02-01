package br.com.oak.financas.api.test.fixtures;

import br.com.oak.financas.api.test.model.input.LancamentoInput;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;

public class FinancasTestFixture {

  public static LancamentoInput buildLancamentoInput() {
    return LancamentoInput.builder()
        .descricao("Descricao ".concat(RandomStringUtils.random(10, true, false)))
        .data("01/01/2022")
        .valor(new BigDecimal("1500.45"))
        .build();
  }
}
