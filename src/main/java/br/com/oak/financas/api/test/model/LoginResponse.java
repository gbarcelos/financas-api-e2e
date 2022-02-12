package br.com.oak.financas.api.test.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
  private String access_token;
  private String token_type;
  private Long expires_in;
  private String scope;
  private String guid;
  private String nome_completo;
  private String jti;
}
