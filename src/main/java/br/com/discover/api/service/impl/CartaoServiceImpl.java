package br.com.discover.api.service.impl;

import br.com.discover.api.model.Cartao;
import br.com.discover.api.repository.CartaoRepository;
import br.com.discover.api.repository.ClienteRepository;
import br.com.discover.api.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoServiceImpl implements CartaoService {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


     private void validaLimite(final Cartao cartao, final Long idCliente){
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
    }

    @Override
    public Cartao save(Cartao cartao, final Long id) {
        if(cartaoRepository.existsByNumeroCartao(cartao.getNumeroCartao())){
            throw new RuntimeException("Cartao já está cadastrado");
        }
        validaLimite(cartao, id);
        return cartaoRepository.save(cartao);
    }

    @Override
    public Cartao delete(Long id) {
         var cartao = cartaoRepository.findById(id)
                 .orElseThrow(()->new RuntimeException("Cartao nao existe!"));
        cartaoRepository.deleteById(id);
        return cartao;
    }

    @Override
    public Cartao findById(Long id) {
        return cartaoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cliente nao existe"));
    }

    @Override
    public List<Cartao> findAll() {
        return cartaoRepository.findAll();
    }
}
