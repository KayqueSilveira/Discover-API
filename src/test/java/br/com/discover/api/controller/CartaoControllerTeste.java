package br.com.discover.api.controller;

import br.com.discover.api.model.Cartao;
import br.com.discover.api.model.Cliente;
import br.com.discover.api.model.Transacao;
import br.com.discover.api.repository.CartaoRepository;
import br.com.discover.api.repository.ClienteRepository;
import br.com.discover.api.service.impl.CartaoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CartaoControllerTeste {

    @InjectMocks
    private CartaoServiceImpl cartaoService;

    @Mock
    private CartaoRepository cartaoRepository;

    @Mock
    private ClienteRepository clienteRepository;
    private Cliente cliente;

    private Transacao transacao;

    private Transacao transacao2;

    private List<Transacao> transacaoList;

    private Cartao cartao;
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        builderCliente();
        builderTransacao();
        builder();
    }

    @Test
    @DisplayName("Quando for salvar deve retornar sucesso")
    void save() {
        when(cartaoRepository.save(any())).thenReturn(cartao);
        when(clienteRepository.findById(any())).thenReturn(Optional.ofNullable(cliente));

        Cartao c = cartaoService.save(cartao, cliente.getId());

        Assertions.assertEquals(c.getClass(), Cartao.class);
    }

    @Test
    @DisplayName("Quando buscar todos os cartoes deve retornar sucesso")
    void getAllCartao() {
        when(cartaoRepository.findAll()).thenReturn(List.of(cartao));

        List<Cartao> cartaoList = cartaoService.findAll();

        Assertions.assertEquals(1, cartaoList.size());
    }

    @Test
    @DisplayName("Quando buscar cartao por id deve retornar sucesso")
    void getCartao() {
        when(cartaoRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(cartao));
        Cartao response = cartaoService.findById(cartao.getId());

        Assertions.assertEquals(Cartao.class, response.getClass());
    }

    @Test
    void deletarCartaoPorId() {
        when(cartaoRepository.findById(anyLong())).thenReturn(Optional.ofNullable(cartao));

        Cartao response = cartaoService.delete(1L);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(Cartao.class, response.getClass());
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
