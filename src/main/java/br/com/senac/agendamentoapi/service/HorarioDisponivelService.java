package br.com.senac.agendamentoapi.service;

import br.com.senac.agendamentoapi.dto.HorarioDisponivelRequest;
import br.com.senac.agendamentoapi.dto.HorarioDisponivelResponse;
import br.com.senac.agendamentoapi.exception.BusinessException;
import br.com.senac.agendamentoapi.exception.ResourceNotFoundException;
import br.com.senac.agendamentoapi.mapper.HorarioDisponivelMapper;
import br.com.senac.agendamentoapi.model.Funcionario;
import br.com.senac.agendamentoapi.model.HorarioDisponivel;
import br.com.senac.agendamentoapi.model.HorarioDisponivelSituacao;
import br.com.senac.agendamentoapi.repository.HorarioDisponivelRepository;
import br.com.senac.agendamentoapi.util.RegistroStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HorarioDisponivelService {

    private final HorarioDisponivelRepository repository;
    private final FuncionarioService funcionarioService;

    public HorarioDisponivelService(HorarioDisponivelRepository repository, FuncionarioService funcionarioService) {
        this.repository = repository;
        this.funcionarioService = funcionarioService;
    }

    @Transactional(readOnly = true)
    public List<HorarioDisponivelResponse> listar() {
        return repository.findByStatusNot(RegistroStatus.EXCLUIDO).stream()
                .map(HorarioDisponivelMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public HorarioDisponivelResponse buscarPorId(Integer id) {
        return HorarioDisponivelMapper.toResponse(buscarEntidade(id));
    }

    @Transactional
    public HorarioDisponivelResponse criar(HorarioDisponivelRequest request) {
        validarPeriodo(request);
        HorarioDisponivel horario = new HorarioDisponivel();
        preencher(horario, request);
        return HorarioDisponivelMapper.toResponse(repository.save(horario));
    }

    @Transactional
    public HorarioDisponivelResponse atualizar(Integer id, HorarioDisponivelRequest request) {
        validarPeriodo(request);
        HorarioDisponivel horario = buscarEntidade(id);
        preencher(horario, request);
        return HorarioDisponivelMapper.toResponse(repository.save(horario));
    }

    @Transactional
    public void excluir(Integer id) {
        HorarioDisponivel horario = buscarEntidade(id);
        horario.setStatus(RegistroStatus.EXCLUIDO);
        repository.save(horario);
    }

    public HorarioDisponivel buscarEntidade(Integer id) {
        return repository.findByIdAndStatusNot(id, RegistroStatus.EXCLUIDO)
                .orElseThrow(() -> new ResourceNotFoundException("Horário disponível", id));
    }

    private void preencher(HorarioDisponivel horario, HorarioDisponivelRequest request) {
        Funcionario funcionario = funcionarioService.buscarEntidade(request.funcionarioId());
        horario.setData(request.data());
        horario.setInicio(request.inicio());
        horario.setFim(request.fim());
        if (request.situacao() != null || horario.getSituacao() == null) {
            horario.setSituacao(request.situacao() == null ? HorarioDisponivelSituacao.DISPONIVEL : request.situacao());
        }
        horario.setFuncionario(funcionario);
        if (request.status() != null || horario.getStatus() == null) {
            horario.setStatus(RegistroStatus.normalizar(request.status()));
        }
    }

    private void validarPeriodo(HorarioDisponivelRequest request) {
        if (!request.fim().isAfter(request.inicio())) {
            throw new BusinessException("O horário final deve ser maior que o horário inicial.");
        }
    }
}
