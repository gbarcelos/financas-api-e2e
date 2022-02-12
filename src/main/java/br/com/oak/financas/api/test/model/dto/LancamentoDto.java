package br.com.oak.financas.api.test.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LancamentoDto {
  private Long id;
  private String descricao;
  private BigDecimal valor;
  private LocalDate data;
  private String categoria;
}
