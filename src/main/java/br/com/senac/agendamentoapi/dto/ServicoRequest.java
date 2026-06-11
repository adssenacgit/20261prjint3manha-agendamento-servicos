package br.com.senac.agendamentoapi.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ServicoRequest(
        @NotBlank @Size(max = 50) String nome,
        String descricao,
        @NotNull @DecimalMin(value = "0.01") BigDecimal valor,
        @NotNull @Min(1) Integer duracaoMinutos,
        @NotNull Integer funcionarioId,
        Integer status
) {
}
