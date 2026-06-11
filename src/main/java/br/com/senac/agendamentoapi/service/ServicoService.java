package br.com.senac.agendamentoapi.service;

import br.com.senac.agendamentoapi.dto.ServicoRequest;
import br.com.senac.agendamentoapi.dto.ServicoResponse;
import br.com.senac.agendamentoapi.exception.ResourceNotFoundException;
import br.com.senac.agendamentoapi.mapper.ServicoMapper;
import br.com.senac.agendamentoapi.model.Funcionario;
import br.com.senac.agendamentoapi.model.Servico;
import br.com.senac.agendamentoapi.repository.ServicoRepository;
import br.com.senac.agendamentoapi.util.RegistroStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository repository;
    private final FuncionarioService funcionarioService;

    public ServicoService(ServicoRepository repository, FuncionarioService funcionarioService) {
        this.repository = repository;
        this.funcionarioService = funcionarioService;
    }

    @Transactional(readOnly = true)
    public List<ServicoResponse> listar() {
        return repository.findByStatusNot(RegistroStatus.EXCLUIDO).stream()
                .map(ServicoMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ServicoResponse buscarPorId(Integer id) {
        return ServicoMapper.toResponse(buscarEntidade(id));
    }

    @Transactional
    public ServicoResponse criar(ServicoRequest request) {
        Servico servico = new Servico();
        preencher(servico, request);
        return ServicoMapper.toResponse(repository.save(servico));
    }

    @Transactional
    public ServicoResponse atualizar(Integer id, ServicoRequest request) {
        Servico servico = buscarEntidade(id);
        preencher(servico, request);
        return ServicoMapper.toResponse(repository.save(servico));
    }

    @Transactional
    public void excluir(Integer id) {
        Servico servico = buscarEntidade(id);
        servico.setStatus(RegistroStatus.EXCLUIDO);
        repository.save(servico);
    }

    public Servico buscarEntidade(Integer id) {
        return repository.findByIdAndStatusNot(id, RegistroStatus.EXCLUIDO)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço", id));
    }

    private void preencher(Servico servico, ServicoRequest request) {
        Funcionario funcionario = funcionarioService.buscarEntidade(request.funcionarioId());
        servico.setNome(request.nome());
        servico.setDescricao(request.descricao());
        servico.setValor(request.valor());
        servico.setDuracaoMinutos(request.duracaoMinutos());
        servico.setFuncionario(funcionario);
        if (request.status() != null || servico.getStatus() == null) {
            servico.setStatus(RegistroStatus.normalizar(request.status()));
        }
    }
}
