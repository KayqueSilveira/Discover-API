package br.com.discover.api.controller;

import br.com.discover.api.model.Cliente;
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
    private ClienteService clienteService;

    @PostMapping("/criar")
    public Cliente save(@RequestBody Cliente cliente, @RequestParam(required = false) Long id){
        if(clienteRepository.existsByCpf(cliente.getCpf())){
            throw new RuntimeException("CPF já está cadastrado");
        } else if (id == null) {
            return clienteRepository.save(cliente);
        }else {
            clienteService.execute(cliente, id);
            return cliente;
        }
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
        clienteRepository.delete(clienteRepository.findById(id).orElseThrow());
    }

    @PutMapping("update")
    public void updateCliente(@RequestBody Cliente cliente, @RequestParam Long id){
        clienteService.verificarCliente(cliente, id);
    }
}
