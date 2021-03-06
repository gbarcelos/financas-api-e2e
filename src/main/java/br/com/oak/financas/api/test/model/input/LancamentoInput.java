package br.com.oak.financas.api.test.model.input;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LancamentoInput {
  private String descricao;
  private BigDecimal valor;
  private String data;
}
