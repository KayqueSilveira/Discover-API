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
    private ClienteService clienteService;

    @PostMapping("/criar")
    public Cliente save(@RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @GetMapping("buscar")
    public List<Cliente> getAllCliente(){
        return clienteService.findAll();
    }

    @GetMapping("buscar/{id}")
    public Cliente getCliente(@PathVariable Long id){
        return clienteService.findById(id);
    }

    @PutMapping("update")
    public Cliente updateCliente(@RequestBody Cliente cliente, @RequestParam Long idCliente){
        return clienteService.updateCliente(cliente, idCliente);
    }
}
