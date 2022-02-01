package br.com.oak.financas.api.test.model.contract.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
  private Integer status;
  private String type;
  private String errorCode;
  private String detail;
  private List<ObjectError> objectErrors;
}
