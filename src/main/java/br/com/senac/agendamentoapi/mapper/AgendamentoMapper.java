package br.com.senac.agendamentoapi.mapper;

import br.com.senac.agendamentoapi.dto.AgendamentoResponse;
import br.com.senac.agendamentoapi.model.Agendamento;

public final class AgendamentoMapper {

    private AgendamentoMapper() {
    }

    public static AgendamentoResponse toResponse(Agendamento agendamento) {
        return new AgendamentoResponse(
                agendamento.getId(),
                agendamento.getData(),
                agendamento.getHorarioInicio(),
                agendamento.getHorarioFim(),
                agendamento.getObservacoes(),
                agendamento.getStatus(),
                agendamento.getServico().getId(),
                agendamento.getServico().getNome(),
                agendamento.getHorarioDisponivel().getId(),
                agendamento.getCliente().getId(),
                agendamento.getCliente().getNome()
        );
    }
}
