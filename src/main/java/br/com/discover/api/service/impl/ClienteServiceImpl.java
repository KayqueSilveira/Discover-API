package br.com.discover.api.service.impl;

import br.com.discover.api.model.Cliente;
import br.com.discover.api.repository.CartaoRepository;
import br.com.discover.api.repository.ClienteRepository;
import br.com.discover.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente save(Cliente cliente) {
        if(clienteRepository.existsByCpf(cliente.getCpf())){
            throw new RuntimeException("CPF já possui cadastro!");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente findById(final Long id){
        return clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cliente nao existe"));
    }
    @Override
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    @Override
    public Cliente updateCliente(final Cliente cliente, final Long idCliente) {
        var clienteUpdate = clienteRepository.findById(idCliente).orElseThrow();

        if(cliente.getCpf() != null) {
            clienteUpdate.setCpf(cliente.getCpf());
        }
        if (cliente.getNome() != null) {
            clienteUpdate.setNome(cliente.getNome());
        }
        if (cliente.getSalario() >= 1) {
            clienteUpdate.setSalario(cliente.getSalario());
        }
        return clienteRepository.save(clienteUpdate);
    }

}
