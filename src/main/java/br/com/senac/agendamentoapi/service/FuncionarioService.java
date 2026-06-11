package br.com.senac.agendamentoapi.service;

import br.com.senac.agendamentoapi.dto.FuncionarioRequest;
import br.com.senac.agendamentoapi.dto.FuncionarioResponse;
import br.com.senac.agendamentoapi.exception.BusinessException;
import br.com.senac.agendamentoapi.exception.ResourceNotFoundException;
import br.com.senac.agendamentoapi.mapper.FuncionarioMapper;
import br.com.senac.agendamentoapi.model.Funcionario;
import br.com.senac.agendamentoapi.repository.FuncionarioRepository;
import br.com.senac.agendamentoapi.util.PasswordHashUtil;
import br.com.senac.agendamentoapi.util.RegistroStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<FuncionarioResponse> listar() {
        return repository.findByStatusNot(RegistroStatus.EXCLUIDO).stream()
                .map(FuncionarioMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public FuncionarioResponse buscarPorId(Integer id) {
        return FuncionarioMapper.toResponse(buscarEntidade(id));
    }

    @Transactional
    public FuncionarioResponse criar(FuncionarioRequest request) {
        validarEmailUnico(request.email(), null);
        Funcionario funcionario = new Funcionario();
        preencher(funcionario, request);
        return FuncionarioMapper.toResponse(repository.save(funcionario));
    }

    @Transactional
    public FuncionarioResponse atualizar(Integer id, FuncionarioRequest request) {
        Funcionario funcionario = buscarEntidade(id);
        validarEmailUnico(request.email(), id);
        preencher(funcionario, request);
        return FuncionarioMapper.toResponse(repository.save(funcionario));
    }

    @Transactional
    public void excluir(Integer id) {
        Funcionario funcionario = buscarEntidade(id);
        funcionario.setStatus(RegistroStatus.EXCLUIDO);
        repository.save(funcionario);
    }

    public Funcionario buscarEntidade(Integer id) {
        return repository.findByIdAndStatusNot(id, RegistroStatus.EXCLUIDO)
                .orElseThrow(() -> new ResourceNotFoundException("Funcionário", id));
    }

    private void preencher(Funcionario funcionario, FuncionarioRequest request) {
        funcionario.setNome(request.nome());
        funcionario.setEmail(request.email());
        funcionario.setSenhaHash(PasswordHashUtil.sha256(request.senha()));
        funcionario.setTelefone(request.telefone());
        if (request.status() != null || funcionario.getStatus() == null) {
            funcionario.setStatus(RegistroStatus.normalizar(request.status()));
        }
    }

    private void validarEmailUnico(String email, Integer idAtual) {
        boolean existe = idAtual == null
                ? repository.existsByEmailAndStatusNot(email, RegistroStatus.EXCLUIDO)
                : repository.existsByEmailAndIdNotAndStatusNot(email, idAtual, RegistroStatus.EXCLUIDO);
        if (existe) {
            throw new BusinessException("Já existe funcionário ativo ou inativo cadastrado com este e-mail.");
        }
    }
}
