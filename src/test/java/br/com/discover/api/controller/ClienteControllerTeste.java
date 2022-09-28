package br.com.discover.api.controller;

import br.com.discover.api.model.Cliente;
import br.com.discover.api.repository.ClienteRepository;
import br.com.discover.api.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClienteControllerTeste {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
        builderCliente();
    }

    @Test
    @DisplayName("Deve retornar sucesso ao salvar um cliente")
    void save() {
        when(clienteRepository.save(any())).thenReturn(cliente);

        Cliente c = clienteService.save(cliente);

        Assertions.assertEquals(c.getClass(), Cliente.class);
    }

    @Test
    @DisplayName("Quando for buscar todos os clientes deve retorar sucesso")
    void getAllCliente() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente));

        List<Cliente> clienteList = clienteService.findAll();

        Assertions.assertEquals(1, clienteList.size());
    }

    @Test
    @DisplayName("Quando for buscar um cliente por id deve retornar sucesso")
    void getCliente() {
        when(clienteRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(cliente));
        Cliente response = clienteService.findById(cliente.getId());

        Assertions.assertEquals(Cliente.class, response.getClass());
    }

    @Test
    @DisplayName("Quando for alterar um cliente deve retornar sucesso")
    void updateCliente() {
        when(clienteRepository.save(any())).thenReturn(cliente);
        Cliente response = cliente;
        response.setNome("camargos");
        clienteService.updateCliente(response, 1L);

        Assertions.assertEquals("garcia", cliente.getNome());

    }

    @Test
    @DisplayName("Quando criar um cliente que ja possui cadastro deve retornar erro")
    void criarClienteJaExistente(){
        when(clienteRepository.existsByCpf(any())).thenReturn(true);

        Cliente clienteNew = new Cliente(2L, "44611795837","Garcia", 1000 );

        try {
            clienteService.save(clienteNew);
        }catch (Exception ex){
            Assertions.assertEquals(RuntimeException.class, ex.getClass());
        }

    }
    private void builderCliente(){
        cliente = new Cliente(1L, "44611795837","Kayque Garcia", 5000);
    }
}
