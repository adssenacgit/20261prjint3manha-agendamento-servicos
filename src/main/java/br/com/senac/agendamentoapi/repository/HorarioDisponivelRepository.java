package br.com.senac.agendamentoapi.repository;

import br.com.senac.agendamentoapi.model.HorarioDisponivel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HorarioDisponivelRepository extends JpaRepository<HorarioDisponivel, Integer> {

    List<HorarioDisponivel> findByStatusNot(Integer status);

    Optional<HorarioDisponivel> findByIdAndStatusNot(Integer id, Integer status);
}
