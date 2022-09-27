package br.com.discover.api.service;

import br.com.discover.api.model.Cartao;
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

//    private void addCartaoToCliente(final Cliente cliente, final Long id){
//        var cartoes = cartaoRepository.findById(id)
//                .orElseThrow(()->new RuntimeException(""));
//        cliente.setCartao(cartoes);
//        clienteRepository.save(cliente);
//        cartoes.setCliente(cliente);
//        cartaoRepository.save(cartoes);
//
//    }

    private void criarCliente(final Cliente cliente){
       if(clienteRepository.existsByCpf(cliente.getCpf())){
           throw new RuntimeException("CPF já possui cadastro!");
       }
        clienteRepository.save(cliente);
    }

    public void verificarCliente(final Cliente cliente, final Long idCliente, final Long idCartao){
        clienteTest(cliente, idCliente, idCartao);
    }

    private void clienteTest(final Cliente cliente, final Long id, final Long idCartao){

        var x = clienteRepository.findById(id).orElseThrow();
        var y = cartaoRepository.findById(idCartao).orElseThrow();

        if(cliente.getCpf() != null) {
            x.setCpf(cliente.getCpf());
        }
        if (cliente.getNome() != null) {
            x.setNome(cliente.getNome());
        }
        if (cliente.getSalario() >= 1) {
            x.setSalario(cliente.getSalario());
        }
        x.setCartao(y);
        clienteRepository.save(x);
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
        cliente.setCartao(cartao);
        clienteRepository.save(cliente);
    }

}
