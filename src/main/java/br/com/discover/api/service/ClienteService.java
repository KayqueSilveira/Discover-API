package br.com.discover.api.service;

import br.com.discover.api.model.Cliente;

import java.util.List;

public interface ClienteService {

    public Cliente save(Cliente cliente);

    public Cliente findById(Long id);

    public List<Cliente> findAll();

    public Cliente updateCliente(Cliente cliente, Long idCliente);


}
