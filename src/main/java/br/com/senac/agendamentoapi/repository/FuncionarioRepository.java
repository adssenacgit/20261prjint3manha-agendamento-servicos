package br.com.senac.agendamentoapi.repository;

import br.com.senac.agendamentoapi.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    List<Funcionario> findByStatusNot(Integer status);

    Optional<Funcionario> findByIdAndStatusNot(Integer id, Integer status);

    boolean existsByEmailAndStatusNot(String email, Integer status);

    boolean existsByEmailAndIdNotAndStatusNot(String email, Integer id, Integer status);
}
