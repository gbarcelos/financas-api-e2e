package br.com.oak.financas.api.test.steps;

import br.com.oak.financas.api.test.FinancasApiE2eApplicationTests;
import br.com.oak.financas.api.test.ScenarioContext;
import br.com.oak.financas.api.test.model.ResponseErrorType;
import br.com.oak.financas.api.test.model.contract.response.ErrorResponse;
import br.com.oak.financas.api.test.model.contract.response.ObjectError;
import br.com.oak.financas.api.test.model.dto.LancamentoDto;
import br.com.oak.financas.api.test.model.input.LancamentoInput;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

import static br.com.oak.financas.api.test.fixtures.FinancasTestFixture.buildReceitaInput;
import static br.com.oak.financas.api.test.utils.Constants.OBJECT_ERROR;
import static br.com.oak.financas.api.test.utils.DateUtils.convertLocalDateToString;
import static br.com.oak.financas.api.test.utils.FinancasApiPathConstants.RECEITA_V1_PATH;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class ReceitaSteps extends FinancasApiE2eApplicationTests {

  @Autowired private ScenarioContext scenarioContext;

  @Before(value = "@Receita")
  public void before() {
    scenarioContext.setLoginResponse(givenRequestSpecification().login(getUsername(), getPass()));
  }

  @After(value = "@Receita")
  public void after() {

    LancamentoDto lancamentoDto = scenarioContext.getLancamentoDto();

    if (Objects.nonNull(lancamentoDto)) {
      givenRequestSpecification()
          .withToken(scenarioContext.getLoginResponse().getAccess_token())
          .excluirReceita(lancamentoDto.getId());
      scenarioContext.setLancamentoDto(null);
    }
  }

  @Given("uma receita valida")
  public void uma_receita_valida() {
    scenarioContext.setReceitaInput(buildReceitaInput());
  }

  @Given("uma receita valida e criada")
  public void uma_receita_valida_e_criada() {
    scenarioContext.setReceitaInput(buildReceitaInput());
    scenarioContext.setLancamentoDto(
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .criarReceita(scenarioContext.getReceitaInput()));
  }

  @When("criar uma receita")
  public void criar_uma_receita() {
    scenarioContext.setLancamentoDto(
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .criarReceita(scenarioContext.getReceitaInput()));
  }

  @When("tentar criar uma receita com o conteudo como xml content type")
  public void tentar_criar_uma_receita_com_o_conteudo_como_xml_content_type() {

    JsonPath jsonPath =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .returnRequestSpecification()
            .contentType(ContentType.XML)
            .body("body")
            .expect()
            .statusCode(HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE)
            .when()
            .post(RECEITA_V1_PATH)
            .andReturn()
            .jsonPath();

    scenarioContext.setJsonPath(jsonPath);
  }

  @When("tentar criar uma receita com uma descricao nulla")
  public void tentar_criar_uma_receita_com_uma_descricao_nulla() {

    scenarioContext.getReceitaInput().setDescricao(null);

    JsonPath jsonPath =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .returnRequestSpecification()
            .body(scenarioContext.getReceitaInput())
            .expect()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .when()
            .post(RECEITA_V1_PATH)
            .andReturn()
            .jsonPath();

    scenarioContext.setJsonPath(jsonPath);
  }

  @When("tentar criar uma receita com uma descricao em branco")
  public void tentar_criar_uma_receita_com_uma_descricao_em_branco() {

    scenarioContext.getReceitaInput().setDescricao("");

    JsonPath jsonPath =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .returnRequestSpecification()
            .body(scenarioContext.getReceitaInput())
            .expect()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .when()
            .post(RECEITA_V1_PATH)
            .andReturn()
            .jsonPath();

    scenarioContext.setJsonPath(jsonPath);
  }

  @When("tentar criar uma receita com uma data nulla")
  public void tentar_criar_uma_receita_com_uma_data_nulla() {

    scenarioContext.getReceitaInput().setData(null);

    JsonPath jsonPath =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .returnRequestSpecification()
            .body(scenarioContext.getReceitaInput())
            .expect()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .when()
            .post(RECEITA_V1_PATH)
            .andReturn()
            .jsonPath();

    scenarioContext.setJsonPath(jsonPath);
  }

  @When("tentar criar uma receita com uma data invalida")
  public void tentar_criar_uma_receita_com_uma_data_invalida() {

    scenarioContext.getReceitaInput().setData("01/20/2022");

    JsonPath jsonPath =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .returnRequestSpecification()
            .body(scenarioContext.getReceitaInput())
            .expect()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .when()
            .post(RECEITA_V1_PATH)
            .andReturn()
            .jsonPath();

    scenarioContext.setJsonPath(jsonPath);
  }

  @When("tentar criar uma receita com um valor nullo")
  public void tentar_criar_uma_receita_com_um_valor_nullo() {

    scenarioContext.getReceitaInput().setValor(null);

    JsonPath jsonPath =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .returnRequestSpecification()
            .body(scenarioContext.getReceitaInput())
            .expect()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .when()
            .post(RECEITA_V1_PATH)
            .andReturn()
            .jsonPath();

    scenarioContext.setJsonPath(jsonPath);
  }

  @When("tentar criar uma receita com a mesma descricao e no mesmo mes")
  public void tentar_criar_uma_receita_com_a_mesma_descricao_e_no_mesmo_mes() {

    JsonPath jsonPath =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .returnRequestSpecification()
            .body(scenarioContext.getReceitaInput())
            .expect()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .when()
            .post(RECEITA_V1_PATH)
            .andReturn()
            .jsonPath();

    scenarioContext.setJsonPath(jsonPath);
  }

  @When("atualizar a receita")
  public void atualizar_a_receita() {

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
        .atualizarReceita(scenarioContext.getLancamentoDto().getId(), lancamentoInput);
  }

  @Then("verificar a resposta do metodo criar uma receita")
  public void verificar_a_resposta_do_metodo_criar_uma_receita() {
    assertNotNull(scenarioContext.getLancamentoDto().getId());
  }

  @Then("verificar a resposta unsupported media type")
  public void verificar_a_resposta_unsupported_media_type() {

    assertFinancasApiResponse(scenarioContext.getJsonPath());

    ErrorResponse errorResponse =
        scenarioContext.getJsonPath().getObject(OBJECT_ERROR, ErrorResponse.class);

    assertEquals(415, errorResponse.getStatus());
    assertEquals(ResponseErrorType.UNSUPPORTED_MEDIA_TYPE.name(), errorResponse.getType());
    assertEquals(ResponseErrorType.UNSUPPORTED_MEDIA_TYPE.name(), errorResponse.getErrorCode());
    assertEquals(
        "Content type 'application/xml;charset=ISO-8859-1' not supported",
        errorResponse.getDetail());
  }

  @Then("verificar a resposta de descricao obrigatoria")
  public void verificar_a_resposta_de_descricao_obrigatoria() {

    assertFinancasApiResponse(scenarioContext.getJsonPath());

    ErrorResponse errorResponse =
        scenarioContext.getJsonPath().getObject(OBJECT_ERROR, ErrorResponse.class);

    assertEquals(400, errorResponse.getStatus());
    assertEquals(ResponseErrorType.BAD_REQUEST.name(), errorResponse.getType());
    assertEquals("REQUIRED_FIELD", errorResponse.getErrorCode());
    assertEquals("Um ou mais campos estão inválidos.", errorResponse.getDetail());
    assertNotNull(errorResponse.getObjectErrors());

    ObjectError objectError = errorResponse.getObjectErrors().stream().findFirst().get();
    assertEquals("descricao", objectError.getName());
    assertEquals("must not be blank", objectError.getUserMessage());
  }

  @Then("verificar a resposta de data obrigatoria")
  public void verificar_a_resposta_de_data_obrigatoria() {

    assertFinancasApiResponse(scenarioContext.getJsonPath());

    ErrorResponse errorResponse =
        scenarioContext.getJsonPath().getObject(OBJECT_ERROR, ErrorResponse.class);

    assertEquals(400, errorResponse.getStatus());
    assertEquals(ResponseErrorType.BAD_REQUEST.name(), errorResponse.getType());
    assertEquals("REQUIRED_FIELD", errorResponse.getErrorCode());
    assertEquals("Um ou mais campos estão inválidos.", errorResponse.getDetail());
    assertNotNull(errorResponse.getObjectErrors());

    ObjectError objectError = errorResponse.getObjectErrors().stream().findFirst().get();
    assertEquals("data", objectError.getName());
    assertEquals("must not be null", objectError.getUserMessage());
  }

  @Then("verificar a resposta de data com formato invalido")
  public void verificar_a_resposta_de_data_com_formato_invalido() {

    assertFinancasApiResponse(scenarioContext.getJsonPath());

    ErrorResponse errorResponse =
        scenarioContext.getJsonPath().getObject(OBJECT_ERROR, ErrorResponse.class);

    assertEquals(400, errorResponse.getStatus());
    assertEquals(ResponseErrorType.BAD_REQUEST.name(), errorResponse.getType());
    assertEquals("INVALID_FORMAT", errorResponse.getErrorCode());
    assertEquals(
        "Invalid value for MonthOfYear (valid values 1 - 12): 20", errorResponse.getDetail());
  }

  @Then("verificar a resposta de valor obrigatorio")
  public void verificar_a_resposta_de_valor_obrigatorio() {

    assertFinancasApiResponse(scenarioContext.getJsonPath());

    ErrorResponse errorResponse =
        scenarioContext.getJsonPath().getObject(OBJECT_ERROR, ErrorResponse.class);

    assertEquals(400, errorResponse.getStatus());
    assertEquals(ResponseErrorType.BAD_REQUEST.name(), errorResponse.getType());
    assertEquals("REQUIRED_FIELD", errorResponse.getErrorCode());
    assertEquals("Um ou mais campos estão inválidos.", errorResponse.getDetail());
    assertNotNull(errorResponse.getObjectErrors());

    ObjectError objectError = errorResponse.getObjectErrors().stream().findFirst().get();
    assertEquals("valor", objectError.getName());
    assertEquals("must not be null", objectError.getUserMessage());
  }

  @Then("verificar a resposta de receita ja existe")
  public void verificar_a_resposta_de_receita_ja_existe() {

    assertFinancasApiResponse(scenarioContext.getJsonPath());

    ErrorResponse errorResponse =
        scenarioContext.getJsonPath().getObject(OBJECT_ERROR, ErrorResponse.class);

    assertEquals(400, errorResponse.getStatus());
    assertEquals(ResponseErrorType.BAD_REQUEST.name(), errorResponse.getType());
    assertEquals("RECEITA_JA_EXISTE", errorResponse.getErrorCode());
    assertEquals("A receita já existe", errorResponse.getDetail());
  }

  @Then("verificar se a receita foi atualizda")
  public void verificar_se_a_receita_foi_atualizda() {

    LancamentoDto lancamentoDto =
        givenRequestSpecification()
            .withToken(scenarioContext.getLoginResponse().getAccess_token())
            .obterReceita(scenarioContext.getLancamentoDto().getId());

    assertEquals(scenarioContext.getLancamentoDto().getData(), lancamentoDto.getData());
    assertTrue(lancamentoDto.getDescricao().contains(", atualizada"));
  }
}
