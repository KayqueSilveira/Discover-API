package br.com.discover.api.service;

import br.com.discover.api.model.Cliente;
import br.com.discover.api.repository.CartaoRepository;
import br.com.discover.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void execute(final Cliente cliente){
        criarCliente(cliente);
    }
    private void criarCliente(final Cliente cliente){
       if(clienteRepository.existsByCpf(cliente.getCpf())){
           throw new RuntimeException("CPF já possui cadastro!");
       }
        clienteRepository.save(cliente);
    }

    public void verificarCliente(final Cliente cliente, final Long idCliente){
        updateCliente(cliente, idCliente);
    }

    private void updateCliente(final Cliente cliente, final Long idCliente){

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
        clienteRepository.save(clienteUpdate);
    }

    private void validaLimite(final Cliente cliente, final Long idCartao){
        var cartao = cartaoRepository.findById(idCartao).orElseThrow();
        var result = cliente.getSalario() * 0.3;
        if(result < 300 ){
            cartao.setLimite(300);
        } else if (result >= 300 && result < 2000) {
            cartao.setLimite(result);
        }else if (result > 2000){
            cartao.setLimite(2000);
        }
        clienteRepository.save(cliente);
    }

}
