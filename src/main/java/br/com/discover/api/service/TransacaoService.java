package br.com.discover.api.service;

import br.com.discover.api.model.Cartao;
import br.com.discover.api.model.Transacao;

public interface TransacaoService {
    void verificaTransacao(final Transacao transacao, final Long id, final int senha);
}
