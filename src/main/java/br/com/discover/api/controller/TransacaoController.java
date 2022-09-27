package br.com.discover.api.controller;

import br.com.discover.api.model.Transacao;
import br.com.discover.api.repository.TransacaoRepository;
import br.com.discover.api.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private TransacaoService transacaoService;

    @RequestMapping(value = "/criar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Transacao save(@RequestBody Transacao transacao, @RequestParam Long idCartao, @RequestParam int senha){
        transacaoService.execute(transacao, idCartao, senha);
        return null;
    }
}
