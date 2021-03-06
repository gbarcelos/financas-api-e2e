package br.com.oak.financas.api.test;

import io.cucumber.java.Before;
import io.cucumber.junit.platform.engine.Cucumber;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Cucumber
public class CucumberIT {

  /** Method annotated with @Before executes before every scenario */
  @Before
  public void before() {
    log.info(">>> Before scenario! -> CucumberIT");

    // It configures the Rest Assured to show logs
    RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
  }
}
