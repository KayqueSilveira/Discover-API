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

    public void execute(final Cliente cliente, final Long id){
        addCartaoToCliente(cliente, id);
    }

    private void addCartaoToCliente(final Cliente cliente, final Long id){
        var cartoes = cartaoRepository.findById(id)
                .orElseThrow(()->new RuntimeException(""));
        cliente.setCartao(cartoes);
        clienteRepository.save(cliente);
        cartoes.setCliente(cliente);
        cartaoRepository.save(cartoes);

    }

    public void verificarCliente(final Cliente cliente, final Long id){
        clienteTest(cliente, id);
    }

    private void clienteTest(final Cliente cliente, final Long id){
        var x = clienteRepository.findById(id).orElseThrow();

        if(cliente.getCpf() != null) {
            x.setCpf(cliente.getCpf());
        }
        if (cliente.getNome() != null) {
            x.setNome(cliente.getNome());
        }
        if(cliente.getCartao() != null) {
            x.setCartao(cliente.getCartao());
        }
        if (cliente.getSalario() >= 1) {
            x.setSalario(cliente.getSalario());
        }

        clienteRepository.save(x);
    }
}
