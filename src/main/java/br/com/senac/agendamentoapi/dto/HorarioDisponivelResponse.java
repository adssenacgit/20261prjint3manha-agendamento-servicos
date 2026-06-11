package br.com.senac.agendamentoapi.dto;

import br.com.senac.agendamentoapi.model.HorarioDisponivelSituacao;
import java.time.LocalDate;
import java.time.LocalTime;

public record HorarioDisponivelResponse(
        Integer id,
        LocalDate data,
        LocalTime inicio,
        LocalTime fim,
        HorarioDisponivelSituacao situacao,
        Integer status,
        Integer funcionarioId,
        String funcionarioNome
) {
}
