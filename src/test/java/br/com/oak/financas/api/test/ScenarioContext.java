package br.com.oak.financas.api.test;

import br.com.oak.financas.api.test.model.dto.LancamentoDto;
import br.com.oak.financas.api.test.model.input.LancamentoInput;
import io.restassured.path.json.JsonPath;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/** Class where we put objects that need to be shared between test scenarios */
@Getter
@Setter
@Component
public class ScenarioContext {
  private JsonPath jsonPath;
  private LancamentoInput lancamentoInput;
  private LancamentoDto lancamentoDto;
}
