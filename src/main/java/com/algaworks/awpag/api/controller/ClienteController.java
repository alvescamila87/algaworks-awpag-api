package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.domain.model.Cliente;
import com.algaworks.awpag.domain.repository.ClienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    //@PersistenceContext
    //private EntityManager manager;

    //@Autowired
    private final ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() {
        //return manager.createQuery("from Cliente", Cliente.class).getResultList();
        //return clienteRepository.findByNome("Princesa Isabel");
        //return clienteRepository.findByNomeContaining("Is");
        return  clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        //codigo 200
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }

        // codigo 404
        return ResponseEntity.notFound().build();

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cliente adicionar(@Valid @RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @Valid @RequestBody Cliente cliente){
        // se id NAO existir, 404 not found
        if (!clienteRepository.existsById(clienteId)) {
            ResponseEntity.notFound().build();
        }

        // se existir: atualiza e nao permite incluir novos registros
        cliente.setId(clienteId);
        cliente = clienteRepository.save(cliente);

        // codigo 200 ok
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
        // se id NAO existir, 404 not found
        if(!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }

        // se existir, exclui
        clienteRepository.deleteById(clienteId);

        // 204 no content
        return ResponseEntity.noContent().build();
    }


}
