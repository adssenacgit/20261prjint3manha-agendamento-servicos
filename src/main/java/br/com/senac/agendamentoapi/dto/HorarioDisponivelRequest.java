package br.com.senac.agendamentoapi.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public record HorarioDisponivelRequest(
        @NotNull LocalDate data,
        @NotNull LocalTime inicio,
        @NotNull LocalTime fim,
        @NotNull Integer funcionarioId,
        Integer status
) {
}
