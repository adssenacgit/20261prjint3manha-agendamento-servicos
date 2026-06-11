package br.com.senac.agendamentoapi.repository;

import br.com.senac.agendamentoapi.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    List<Servico> findByStatusNot(Integer status);

    Optional<Servico> findByIdAndStatusNot(Integer id, Integer status);
}
