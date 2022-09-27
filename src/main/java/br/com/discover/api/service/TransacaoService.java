package br.com.discover.api.service;

import br.com.discover.api.model.Cartao;
import br.com.discover.api.model.Transacao;
import br.com.discover.api.repository.CartaoRepository;
import br.com.discover.api.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    public void execute(final Transacao transacao, final Long id, final int senha){
        verificaTransacao(transacao, id, senha);
    }

    private void verificaTransacao(final Transacao transacao, final Long id, final int senha){
        var cartao = cartaoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cartao nao existe"));

        transacao.setCartao(cartao);
        transacao.setHora(LocalDateTime.now());
        transacao.setNomeCliente(cartao.getCliente().getNome());
        if(verificaLimiteCartao(transacao, id) && verificaSenhaCartao(cartao, senha)){
            List<Transacao> t = new ArrayList<>();
            t.add(transacao);
            cartao.setTransacao(t);
            transacaoRepository.save(transacao);
            cartaoRepository.save(cartao);

        }else{
            throw new RuntimeException("Error ao efetuar transacao!");
        }
    }

    public boolean verificaLimiteCartao(final Transacao transacao, final Long id){
        var cartao = cartaoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cartao nao existe"));

        if(cartao.getLimite() >= transacao.getValor()){
            var result = (cartao.getLimite() - transacao.getValor());
            cartao.setLimite(result);

            return true;
        }else{
            return false;
        }
    }

    public boolean verificaSenhaCartao(final Cartao cartao, final int senha){
        if(cartao.getSenha() == senha){
            return true;
        }
        return false;
    }
}
