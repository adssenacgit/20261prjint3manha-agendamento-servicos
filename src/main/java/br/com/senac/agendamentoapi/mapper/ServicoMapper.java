package br.com.senac.agendamentoapi.mapper;

import br.com.senac.agendamentoapi.dto.ServicoResponse;
import br.com.senac.agendamentoapi.model.Servico;

public final class ServicoMapper {

    private ServicoMapper() {
    }

    public static ServicoResponse toResponse(Servico servico) {
        return new ServicoResponse(
                servico.getId(),
                servico.getNome(),
                servico.getDescricao(),
                servico.getValor(),
                servico.getDuracaoMinutos(),
                servico.getStatus(),
                servico.getFuncionario().getId(),
                servico.getFuncionario().getNome()
        );
    }
}
