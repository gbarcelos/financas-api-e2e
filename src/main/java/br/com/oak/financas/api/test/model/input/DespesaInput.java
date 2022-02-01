package br.com.oak.financas.api.test.model.input;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DespesaInput extends LancamentoInput {
  private Long categoriaId;
}
