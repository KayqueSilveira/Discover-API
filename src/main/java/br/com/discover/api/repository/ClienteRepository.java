package br.com.discover.api.repository;

import br.com.discover.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    void deleteByCartaoId(Long id);
    boolean existsByCpf(String cpf);
}
