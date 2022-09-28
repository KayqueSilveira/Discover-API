package br.com.discover.api.service;

import br.com.discover.api.model.Cartao;
import br.com.discover.api.repository.CartaoRepository;
import br.com.discover.api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void execute(final Cartao cartao, final Long idCliente){
        validaLimite(cartao, idCliente);
    }

    private void validCartao(final Cartao cartao){
        if(cartaoRepository.existsByNumeroCartao(cartao.getNumeroCartao())){
            throw new RuntimeException("Cartao já está cadastrado");
        }

    }

    private void validaLimite(final Cartao cartao, final Long idCliente){
        validCartao(cartao);
        var cliente = clienteRepository.findById(idCliente).orElseThrow();
        var result = cliente.getSalario() * 0.3;
        if(result < 300 ){
            cartao.setLimite(300);
        } else if (result >= 300 && result < 2000) {
            cartao.setLimite(result);
        }else if (result > 2000){
            cartao.setLimite(2000);
        }
        cartao.setCliente(cliente);
        cartaoRepository.save(cartao);
    }
}
