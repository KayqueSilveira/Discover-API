package br.com.discover.api.repository;

import br.com.discover.api.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

    boolean existsByNumeroCartao(int numeroCartao);
}
