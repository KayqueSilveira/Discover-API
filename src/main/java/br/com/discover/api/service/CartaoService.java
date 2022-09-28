package br.com.discover.api.service;

import br.com.discover.api.model.Cartao;

import java.util.List;

public interface CartaoService {


    public Cartao save(Cartao cartao, Long id);

    public Cartao delete(Long id);

    public Cartao findById(Long id);

    public List<Cartao> findAll();

}
