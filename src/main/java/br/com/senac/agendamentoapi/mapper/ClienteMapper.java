package br.com.senac.agendamentoapi.mapper;

import br.com.senac.agendamentoapi.dto.ClienteResponse;
import br.com.senac.agendamentoapi.model.Cliente;

public final class ClienteMapper {

    private ClienteMapper() {
    }

    public static ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getDataCadastro(),
                cliente.getStatus()
        );
    }
}
