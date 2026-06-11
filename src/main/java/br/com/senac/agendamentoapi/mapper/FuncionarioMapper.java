package br.com.senac.agendamentoapi.mapper;

import br.com.senac.agendamentoapi.dto.FuncionarioResponse;
import br.com.senac.agendamentoapi.model.Funcionario;

public final class FuncionarioMapper {

    private FuncionarioMapper() {
    }

    public static FuncionarioResponse toResponse(Funcionario funcionario) {
        return new FuncionarioResponse(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getEmail(),
                funcionario.getTelefone(),
                funcionario.getStatus()
        );
    }
}
