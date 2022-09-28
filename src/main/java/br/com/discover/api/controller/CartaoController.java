package br.com.discover.api.controller;

import br.com.discover.api.model.Cartao;
import br.com.discover.api.repository.CartaoRepository;
import br.com.discover.api.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("api/v1/cartao")
public class CartaoController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoService cartaoService;

    @PostMapping("criar")
    public Cartao save(@RequestBody Cartao cartao, @RequestParam Long idCliente){
        cartaoService.execute(cartao, idCliente);
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

    @DeleteMapping("deletar/{idCartao}/{idTransacao}")
    @Transactional
    public void deletarCartao(@PathVariable Long idCartao, @PathVariable Long idTransacao){
        cartaoRepository.deleteById(idCartao);
    }

    @DeleteMapping("deletar/{idCartao}")
    @Transactional
    public void deletarCartaoPorId(@PathVariable Long idCartao){
        cartaoRepository.deleteById(idCartao);
    }
}
