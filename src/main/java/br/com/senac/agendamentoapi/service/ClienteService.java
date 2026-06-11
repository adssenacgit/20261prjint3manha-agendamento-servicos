package br.com.senac.agendamentoapi.service;

import br.com.senac.agendamentoapi.dto.ClienteRequest;
import br.com.senac.agendamentoapi.dto.ClienteResponse;
import br.com.senac.agendamentoapi.exception.BusinessException;
import br.com.senac.agendamentoapi.exception.ResourceNotFoundException;
import br.com.senac.agendamentoapi.mapper.ClienteMapper;
import br.com.senac.agendamentoapi.model.Cliente;
import br.com.senac.agendamentoapi.repository.ClienteRepository;
import br.com.senac.agendamentoapi.util.RegistroStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> listar() {
        return repository.findByStatusNot(RegistroStatus.EXCLUIDO).stream()
                .map(ClienteMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteResponse buscarPorId(Integer id) {
        return ClienteMapper.toResponse(buscarEntidade(id));
    }

    @Transactional
    public ClienteResponse criar(ClienteRequest request) {
        validarEmailUnico(request.email(), null);
        Cliente cliente = new Cliente();
        preencher(cliente, request);
        return ClienteMapper.toResponse(repository.save(cliente));
    }

    @Transactional
    public ClienteResponse atualizar(Integer id, ClienteRequest request) {
        Cliente cliente = buscarEntidade(id);
        validarEmailUnico(request.email(), id);
        preencher(cliente, request);
        return ClienteMapper.toResponse(repository.save(cliente));
    }

    @Transactional
    public void excluir(Integer id) {
        Cliente cliente = buscarEntidade(id);
        cliente.setStatus(RegistroStatus.EXCLUIDO);
        repository.save(cliente);
    }

    private Cliente buscarEntidade(Integer id) {
        return repository.findByIdAndStatusNot(id, RegistroStatus.EXCLUIDO)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
    }

    private void preencher(Cliente cliente, ClienteRequest request) {
        cliente.setNome(request.nome());
        cliente.setTelefone(request.telefone());
        cliente.setEmail(request.email());
        cliente.setDataCadastro(request.dataCadastro());
        if (request.status() != null || cliente.getStatus() == null) {
            cliente.setStatus(RegistroStatus.normalizar(request.status()));
        }
    }

    private void validarEmailUnico(String email, Integer idAtual) {
        boolean existe = idAtual == null
                ? repository.existsByEmailAndStatusNot(email, RegistroStatus.EXCLUIDO)
                : repository.existsByEmailAndIdNotAndStatusNot(email, idAtual, RegistroStatus.EXCLUIDO);
        if (existe) {
            throw new BusinessException("Já existe cliente ativo ou inativo cadastrado com este e-mail.");
        }
    }
}
