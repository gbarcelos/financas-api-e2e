package br.com.oak.financas.api.test.model.input;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LancamentoInput {
  private String descricao;
  private BigDecimal valor;
  private String data;
}
