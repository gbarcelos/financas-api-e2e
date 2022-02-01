package br.com.oak.financas.api.test.networking;

import br.com.oak.financas.api.test.model.TestConfig;
import br.com.oak.financas.api.test.model.dto.LancamentoDto;
import br.com.oak.financas.api.test.model.input.LancamentoInput;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import static br.com.oak.financas.api.test.utils.Constants.OBJECT_ID;
import static br.com.oak.financas.api.test.utils.Constants.OBJECT_RESPONSE;
import static br.com.oak.financas.api.test.utils.FinancasApiPathConstants.*;
import static io.restassured.RestAssured.given;

public class FinancasApiRequestSpecification {

  private RequestSpecification requestSpecification;

  public FinancasApiRequestSpecification(TestConfig testConfig) {
    requestSpecification = given().accept(ContentType.JSON).contentType(ContentType.JSON);
    requestSpecification.baseUri(testConfig.getBaseURI());
    requestSpecification.basePath(testConfig.getBasePath());
    requestSpecification.port(testConfig.getPort());
  }

  public RequestSpecification returnRequestSpecification() {
    return requestSpecification;
  }

  public LancamentoDto criarReceita(LancamentoInput lancamentoInput) {

    JsonPath jsonPath =
        requestSpecification
            .body(lancamentoInput)
            .expect()
            .statusCode(HttpStatus.SC_CREATED)
            .when()
            .post(RECEITA_V1_PATH)
            .andReturn()
            .jsonPath();

    return jsonPath.getObject(OBJECT_RESPONSE, LancamentoDto.class);
  }

  public LancamentoDto criarDespesa(LancamentoInput lancamentoInput) {

    JsonPath jsonPath =
        requestSpecification
            .body(lancamentoInput)
            .expect()
            .statusCode(HttpStatus.SC_CREATED)
            .when()
            .post(DESPESA_V1_PATH)
            .andReturn()
            .jsonPath();

    return jsonPath.getObject(OBJECT_RESPONSE, LancamentoDto.class);
  }

  public void atualizarReceita(Long id, LancamentoInput lancamentoInput) {

    requestSpecification
        .body(lancamentoInput)
        .pathParam(OBJECT_ID, id)
        .expect()
        .statusCode(HttpStatus.SC_NO_CONTENT)
        .when()
        .put(RECEITA_V1_ID_PATH);
  }

  public void atualizarDespesa(Long id, LancamentoInput lancamentoInput) {

    requestSpecification
        .body(lancamentoInput)
        .pathParam(OBJECT_ID, id)
        .expect()
        .statusCode(HttpStatus.SC_NO_CONTENT)
        .when()
        .put(DESPESA_V1_ID_PATH);
  }

  public LancamentoDto obterReceita(Long id) {

    JsonPath jsonPath =
        requestSpecification
            .pathParam(OBJECT_ID, id)
            .expect()
            .statusCode(HttpStatus.SC_OK)
            .when()
            .get(RECEITA_V1_ID_PATH)
            .andReturn()
            .jsonPath();

    return jsonPath.getObject(OBJECT_RESPONSE, LancamentoDto.class);
  }

  public LancamentoDto obterDespesa(Long id) {

    JsonPath jsonPath =
        requestSpecification
            .pathParam(OBJECT_ID, id)
            .expect()
            .statusCode(HttpStatus.SC_OK)
            .when()
            .get(DESPESA_V1_ID_PATH)
            .andReturn()
            .jsonPath();

    return jsonPath.getObject(OBJECT_RESPONSE, LancamentoDto.class);
  }

  public void excluirReceita(Long id) {

    requestSpecification
        .pathParam(OBJECT_ID, id)
        .expect()
        .statusCode(HttpStatus.SC_NO_CONTENT)
        .when()
        .delete(RECEITA_V1_ID_PATH);
  }

  public void excluirDespesa(Long id) {

    requestSpecification
        .pathParam(OBJECT_ID, id)
        .expect()
        .statusCode(HttpStatus.SC_NO_CONTENT)
        .when()
        .delete(DESPESA_V1_ID_PATH);
  }
}
