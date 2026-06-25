package br.com.senac.agendamentoapi.mapper;

import br.com.senac.agendamentoapi.dto.HorarioDisponivelResponse;
import br.com.senac.agendamentoapi.model.HorarioDisponivel;

public final class HorarioDisponivelMapper {

    private HorarioDisponivelMapper() {
    }

    public static HorarioDisponivelResponse toResponse(HorarioDisponivel horario) {
        return new HorarioDisponivelResponse(
                horario.getId(),
                horario.getData(),
                horario.getInicio(),
                horario.getFim(),
                horario.getStatus(),
                horario.getFuncionario().getId(),
                horario.getFuncionario().getNome()
        );
    }
}
