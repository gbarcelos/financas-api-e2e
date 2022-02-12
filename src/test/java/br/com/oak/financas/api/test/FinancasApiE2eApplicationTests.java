package br.com.oak.financas.api.test;

import br.com.oak.financas.api.test.model.TestConfig;
import br.com.oak.financas.api.test.networking.FinancasApiRequestSpecification;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.path.json.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static br.com.oak.financas.api.test.utils.Constants.OBJECT_RESPONSE;
import static br.com.oak.financas.api.test.utils.FinancasApiPathConstants.BASE_FINANCAS_API_WEB_PATH;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Slf4j
@CucumberContextConfiguration
@SpringBootTest
public class FinancasApiE2eApplicationTests {

  @Value("${test.api.uri}")
  private String apiUri;

  @Value("${test.api.port}")
  private int apiPort;

  @Value("${test.api.client-id}")
  private String apiClientId;

  @Value("${test.api.client-secret}")
  private String apiClientSecret;

  @Value("${test.api.username}")
  private String username;

  @Value("${test.api.pass}")
  private String pass;

  protected FinancasApiRequestSpecification givenRequestSpecification() {
    return new FinancasApiRequestSpecification(getFinancasApiConfig());
  }

  protected void assertFinancasApiResponse(JsonPath jsonPath) {

    Object response = jsonPath.getObject(OBJECT_RESPONSE, Object.class);
    String path = jsonPath.getObject("path", String.class);
    String timestamp = jsonPath.getObject("timestamp", String.class);

    assertNull(response);
    assertNotNull(path);
  }

  protected String getUsername() {
    return username;
  }

  protected String getPass() {
    return pass;
  }

  private TestConfig getFinancasApiConfig() {
    return TestConfig.builder()
        .basePath(BASE_FINANCAS_API_WEB_PATH)
        .baseURI(apiUri)
        .port(apiPort)
        .clientId(apiClientId)
        .clientSecret(apiClientSecret)
        .build();
  }
}
