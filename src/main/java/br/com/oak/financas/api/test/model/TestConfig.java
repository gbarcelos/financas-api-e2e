package br.com.oak.financas.api.test.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TestConfig {
  private String baseURI;
  private String basePath;
  private int port;
}
