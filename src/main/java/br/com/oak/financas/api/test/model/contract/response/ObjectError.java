package br.com.oak.financas.api.test.model.contract.response;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectError {
  private String name;
  private String userMessage;
}
