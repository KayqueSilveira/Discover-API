package br.com.discover.api.controller;

import br.com.discover.api.model.Cartao;
import br.com.discover.api.model.Cliente;
import br.com.discover.api.model.Transacao;
import br.com.discover.api.repository.CartaoRepository;
import br.com.discover.api.repository.ClienteRepository;
import br.com.discover.api.repository.TransacaoRepository;
import br.com.discover.api.service.impl.TransacaoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TransacaoControllerTeste {

    @InjectMocks
    private TransacaoServiceImpl transacaoService;

    @Mock
    private TransacaoRepository transacaoRepository;

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    private Cartao cartao;

    private Transacao transacao;

    private Transacao transacao2;

    private List<Transacao> transacaoList;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        builderCliente();
        builderTransacao();
        builder();
    }


    @Test
    public void verificaTransacao(){
        when(cartaoRepository.findById(any())).thenReturn(Optional.ofNullable(cartao));

    }

    public void builder(){

        cartao = Cartao.builder()
                .id(1L)
                .cliente(cliente)
                .transacao(List.of(transacao2))
                .numeroCartao(123456)
                .nome("Santander")
                .senha(1233)
                .limite(300)
                .build();

    }
    private void builderCliente(){
        cliente = new Cliente(1L, "44611795837","Kayque Garcia", 5000);
    }

    private void builderTransacao(){
        transacao = Transacao.builder()
                .id(1L)
                .hora(LocalDateTime.now())
                .lugar("Morada do sol")
                .nomeCliente("Garcia")
                .cartao(cartao)
                .build();
        transacao2 = Transacao.builder()
                .id(2L)
                .hora(LocalDateTime.now())
                .lugar("Morada do sol")
                .nomeCliente("Garcia")
                .cartao(cartao)
                .build();
    }
}
