package br.com.discover.api.controller;

import br.com.discover.api.model.Cliente;
import br.com.discover.api.repository.CartaoRepository;
import br.com.discover.api.repository.ClienteRepository;
import br.com.discover.api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/criar")
    public Cliente save(@RequestBody Cliente cliente){
        clienteService.execute(cliente);
        return cliente;
    }

    @GetMapping("buscar")
    public List<Cliente> getAllCliente(){
        return clienteRepository.findAll();
    }

    @GetMapping("buscar/{id}")
    public Cliente getCliente(@PathVariable Long id){
        return clienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cliente nao existe"));
    }

    @DeleteMapping("delete")
    public void deleteCliente(@RequestParam Long id){
        clienteRepository.deleteByCartaoId(id);
    }

    @PutMapping("update")
    public void updateCliente(@RequestBody Cliente cliente, @RequestParam Long idCliente, @RequestParam(required = false) Long idCartao){
        clienteService.verificarCliente(cliente, idCliente, idCartao);
    }
}
