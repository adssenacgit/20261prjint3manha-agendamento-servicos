package br.com.senac.agendamentoapi.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record HorarioDisponivelResponse(
        Integer id,
        LocalDate data,
        LocalTime inicio,
        LocalTime fim,
        Integer status,
        Integer funcionarioId,
        String funcionarioNome
) {
}
