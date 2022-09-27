package br.com.discover.api.controller;

import br.com.discover.api.model.Cartao;
import br.com.discover.api.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cartao")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("criar")
    public Cartao save(@RequestBody Cartao cartao){
        if(cartaoRepository.existsByNumeroCartao(cartao.getNumeroCartao())){
            throw new RuntimeException("Cartao já está cadastrado");
        }
        cartaoRepository.save(cartao);
        return cartao;
    }

    @GetMapping("buscar")
    public List<Cartao> getAllCartao(){
        return cartaoRepository.findAll();
    }

    @GetMapping("buscar/{id}")
    public Cartao getCartao(@PathVariable Long id){
        return cartaoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cliente nao existe"));
    }

    @DeleteMapping("deletar/{id}")
    public void deletarCartao(@PathVariable Long id){
        var cartao = cartaoRepository.findById(id).orElseThrow();
        cartaoRepository.delete(cartao);
    }
}
