package br.com.senac.agendamentoapi.repository;

import br.com.senac.agendamentoapi.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {

    List<Agendamento> findByStatusNot(Integer status);

    Optional<Agendamento> findByIdAndStatusNot(Integer id, Integer status);
}
