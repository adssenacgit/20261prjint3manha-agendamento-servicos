package br.com.senac.agendamentoapi.service;

import br.com.senac.agendamentoapi.dto.AgendamentoRequest;
import br.com.senac.agendamentoapi.dto.AgendamentoResponse;
import br.com.senac.agendamentoapi.exception.BusinessException;
import br.com.senac.agendamentoapi.exception.ResourceNotFoundException;
import br.com.senac.agendamentoapi.mapper.AgendamentoMapper;
import br.com.senac.agendamentoapi.model.*;
import br.com.senac.agendamentoapi.repository.AgendamentoRepository;
import br.com.senac.agendamentoapi.repository.ClienteRepository;
import br.com.senac.agendamentoapi.util.RegistroStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final ServicoService servicoService;
    private final HorarioDisponivelService horarioDisponivelService;
    private final ClienteRepository clienteRepository;

    public AgendamentoService(AgendamentoRepository repository,
                              ServicoService servicoService,
                              HorarioDisponivelService horarioDisponivelService,
                              ClienteRepository clienteRepository) {
        this.repository = repository;
        this.servicoService = servicoService;
        this.horarioDisponivelService = horarioDisponivelService;
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    public List<AgendamentoResponse> listar() {
        return repository.findByStatusNot(RegistroStatus.EXCLUIDO).stream()
                .map(AgendamentoMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public AgendamentoResponse buscarPorId(Integer id) {
        return AgendamentoMapper.toResponse(buscarEntidade(id));
    }

    @Transactional
    public AgendamentoResponse criar(AgendamentoRequest request) {
        validarPeriodo(request);
        Agendamento agendamento = new Agendamento();
        preencher(agendamento, request);
        return AgendamentoMapper.toResponse(repository.save(agendamento));
    }

    @Transactional
    public AgendamentoResponse atualizar(Integer id, AgendamentoRequest request) {
        validarPeriodo(request);
        Agendamento agendamento = buscarEntidade(id);
        preencher(agendamento, request);
        return AgendamentoMapper.toResponse(repository.save(agendamento));
    }

    @Transactional
    public void excluir(Integer id) {
        Agendamento agendamento = buscarEntidade(id);
        agendamento.setStatus(RegistroStatus.EXCLUIDO);
        repository.save(agendamento);
    }

    private Agendamento buscarEntidade(Integer id) {
        return repository.findByIdAndStatusNot(id, RegistroStatus.EXCLUIDO)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento", id));
    }

    private void preencher(Agendamento agendamento, AgendamentoRequest request) {
        Servico servico = servicoService.buscarEntidade(request.servicoId());
        HorarioDisponivel horario = horarioDisponivelService.buscarEntidade(request.horarioDisponivelId());
        Cliente cliente = clienteRepository.findByIdAndStatusNot(request.clienteId(), RegistroStatus.EXCLUIDO)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", request.clienteId()));

        agendamento.setData(request.data());
        agendamento.setHorarioInicio(request.horarioInicio());
        agendamento.setHorarioFim(request.horarioFim());
        agendamento.setObservacoes(request.observacoes());
        if (request.situacao() != null || agendamento.getSituacao() == null) {
            agendamento.setSituacao(request.situacao() == null ? AgendamentoSituacao.PENDENTE : request.situacao());
        }
        agendamento.setServico(servico);
        agendamento.setHorarioDisponivel(horario);
        agendamento.setCliente(cliente);
        if (request.status() != null || agendamento.getStatus() == null) {
            agendamento.setStatus(RegistroStatus.normalizar(request.status()));
        }
    }

    private void validarPeriodo(AgendamentoRequest request) {
        if (!request.horarioFim().isAfter(request.horarioInicio())) {
            throw new BusinessException("O horário final do agendamento deve ser maior que o horário inicial.");
        }
    }
}
