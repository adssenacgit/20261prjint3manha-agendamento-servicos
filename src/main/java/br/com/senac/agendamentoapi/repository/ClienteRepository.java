package br.com.senac.agendamentoapi.repository;

import br.com.senac.agendamentoapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByStatusNot(Integer status);

    Optional<Cliente> findByIdAndStatusNot(Integer id, Integer status);

    boolean existsByEmailAndStatusNot(String email, Integer status);

    boolean existsByEmailAndIdNotAndStatusNot(String email, Integer id, Integer status);
}
