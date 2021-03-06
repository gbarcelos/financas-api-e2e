package br.com.oak.financas.api.test.steps;

import br.com.oak.financas.api.test.FinancasApiE2eApplicationTests;
import br.com.oak.financas.api.test.ScenarioContext;
import br.com.oak.financas.api.test.model.ResponseErrorType;
import br.com.oak.financas.api.test.model.contract.response.ErrorResponse;
import br.com.oak.financas.api.test.model.dto.LancamentoDto;
import br.com.oak.financas.api.test.model.input.LancamentoInput;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import static br.com.oak.financas.api.test.fixtures.FinancasTestFixture.buildDespesaInput;
import static br.com.oak.financas.api.test.utils.Constants.OBJECT_ERROR;
import static br.com.oak.financas.api.test.utils.DateUtils.convertLocalDateToString;
import static br.com.oak.financas.api.test.utils.FinancasApiPathConstants.DESPESA_V1_PATH;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class DespesaSteps extends FinancasApiE2eApplicationTests {

  @Autowired private ScenarioContext scenarioContext;

  @Before(value = "@Despesa")
  public void before() {
    scenarioContext.setLoginResponse(givenRequestSpecification().login(getUsername(), getPass()));
  }

  @After(value = "@Despesa")
  public void after() {

    LancamentoDto lancamentoDto = scenarioContext.getLancamentoDto();

    if (Objects.nonNull(lancamentoDto)) {
      givenRequestSpecification()
          .withToken(scenarioContext.getLoginResponse().getAccess_token())
          .excluirDespesa(lancamentoDto.getId());
      scenarioContext.setLancamentoDto(null);
    }
  }

  @Given("uma despesa valida")
  public void uma_despesa_valida() {
    scenarioContext.setDespesaInput(buildDespesaInput());
  }

  @Given("uma despesa valida e criada")
  public void uma_despesa_valida_e_criada() {
    scenarioContext.setDespesaInput(buildDespesaInput());
    scenarioContext.setLancamentoDto(
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .criarDespesa(scenarioContext.getDespesaInput()));
  }

  @When("criar uma despesa")
  public void criar_uma_despesa() {
    scenarioContext.setLancamentoDto(
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .criarDespesa(scenarioContext.getDespesaInput()));
  }

  @When("tentar criar uma despesa com a mesma descricao e no mesmo mes")
  public void tentar_criar_uma_despesa_com_a_mesma_descricao_e_no_mesmo_mes() {

    JsonPath jsonPath =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .returnRequestSpecification()
            .body(scenarioContext.getDespesaInput())
            .expect()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .when()
            .post(DESPESA_V1_PATH)
            .andReturn()
            .jsonPath();

    scenarioContext.setJsonPath(jsonPath);
  }

  @When("tentar criar uma despesa com uma descricao nulla")
  public void tentar_criar_uma_despesa_com_uma_descricao_nulla() {

    scenarioContext.getDespesaInput().setDescricao(null);

    JsonPath jsonPath =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .returnRequestSpecification()
            .body(scenarioContext.getDespesaInput())
            .expect()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .when()
            .post(DESPESA_V1_PATH)
            .andReturn()
            .jsonPath();

    scenarioContext.setJsonPath(jsonPath);
  }

  @When("tentar criar uma despesa com uma descricao em branco")
  public void tentar_criar_uma_despesa_com_uma_descricao_em_branco() {

    scenarioContext.getDespesaInput().setDescricao("");

    JsonPath jsonPath =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .returnRequestSpecification()
            .body(scenarioContext.getDespesaInput())
            .expect()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .when()
            .post(DESPESA_V1_PATH)
            .andReturn()
            .jsonPath();

    scenarioContext.setJsonPath(jsonPath);
  }

  @When("tentar criar uma despesa com uma data nulla")
  public void tentar_criar_uma_despesa_com_uma_data_nulla() {

    scenarioContext.getDespesaInput().setData(null);

    JsonPath jsonPath =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .returnRequestSpecification()
            .body(scenarioContext.getDespesaInput())
            .expect()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .when()
            .post(DESPESA_V1_PATH)
            .andReturn()
            .jsonPath();

    scenarioContext.setJsonPath(jsonPath);
  }

  @When("tentar criar uma despesa com uma data invalida")
  public void tentar_criar_uma_despesa_com_uma_data_invalida() {

    scenarioContext.getDespesaInput().setData("01/20/2022");

    JsonPath jsonPath =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .returnRequestSpecification()
            .body(scenarioContext.getDespesaInput())
            .expect()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .when()
            .post(DESPESA_V1_PATH)
            .andReturn()
            .jsonPath();

    scenarioContext.setJsonPath(jsonPath);
  }

  @When("tentar criar uma despesa com um valor nullo")
  public void tentar_criar_uma_despesa_com_um_valor_nullo() {

    scenarioContext.getDespesaInput().setValor(null);

    JsonPath jsonPath =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .returnRequestSpecification()
            .body(scenarioContext.getDespesaInput())
            .expect()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .when()
            .post(DESPESA_V1_PATH)
            .andReturn()
            .jsonPath();

    scenarioContext.setJsonPath(jsonPath);
  }

  @When("atualizar a despesa")
  public void atualizar_a_despesa() {

    LancamentoInput lancamentoInput =
        LancamentoInput.builder()
            .descricao(scenarioContext.getLancamentoDto().getDescricao().concat(", atualizada"))
            .data(
                convertLocalDateToString(
                    scenarioContext.getLancamentoDto().getData(), "dd/MM/yyyy"))
            .valor(scenarioContext.getLancamentoDto().getValor())
            .build();

    givenRequestSpecification()
        .withToken(scenarioContext.getLoginResponse().getAccess_token())
        .atualizarDespesa(scenarioContext.getLancamentoDto().getId(), lancamentoInput);
  }

  @When("criar uma despesa com uma categoria nulla")
  public void criar_uma_despesa_com_uma_categoria_nulla() {

    scenarioContext.getDespesaInput().setCategoriaId(null);

    scenarioContext.setLancamentoDto(
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .criarDespesa(scenarioContext.getDespesaInput()));
  }

  @When("criar uma despesa com uma categoria invalida")
  public void criar_uma_despesa_com_uma_categoria_invalida() {

    scenarioContext.getDespesaInput().setCategoriaId(9999L);

    scenarioContext.setLancamentoDto(
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .criarDespesa(scenarioContext.getDespesaInput()));
  }

  @Then("verificar a resposta do metodo criar uma despesa")
  public void verificar_a_resposta_do_metodo_criar_uma_despesa() {
    assertNotNull(scenarioContext.getLancamentoDto().getId());
  }

  @Then("verificar a resposta de despesa ja existe")
  public void verificar_a_resposta_de_despesa_ja_existe() {

    assertFinancasApiResponse(scenarioContext.getJsonPath());

    ErrorResponse errorResponse =
        scenarioContext.getJsonPath().getObject(OBJECT_ERROR, ErrorResponse.class);

    assertEquals(400, errorResponse.getStatus());
    assertEquals(ResponseErrorType.BAD_REQUEST.name(), errorResponse.getType());
    assertEquals("DESPESA_JA_EXISTE", errorResponse.getErrorCode());
    assertEquals("A despesa j?? existe", errorResponse.getDetail());
  }

  @Then("verificar se a despesa foi atualizda")
  public void verificar_se_a_despesa_foi_atualizda() {

    LancamentoDto lancamentoDto =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .obterDespesa(scenarioContext.getLancamentoDto().getId());

    assertEquals(scenarioContext.getLancamentoDto().getData(), lancamentoDto.getData());
    assertTrue(lancamentoDto.getDescricao().contains(", atualizada"));
  }

  @Then("verificar a resposta do metodo criar uma despesa com uma categoria nulla ou invalida")
  public void
      verificar_a_resposta_do_metodo_criar_uma_despesa_com_uma_categoria_nulla_ou_invalida() {

    LancamentoDto lancamentoDto =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .obterDespesa(scenarioContext.getLancamentoDto().getId());

    assertNotNull(lancamentoDto.getCategoria());
  }
}
