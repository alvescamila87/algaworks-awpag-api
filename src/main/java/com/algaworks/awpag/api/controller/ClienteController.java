package com.algaworks.awpag.api.controller;

import com.algaworks.awpag.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> getClientes() {
        var cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Princesa Isabel");
        cliente1.setTelefone("47 99999-1111");
        cliente1.setEmail("princesa_isable@algaworks.com");

        var cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Dom Manoel");
        cliente2.setTelefone("47 88888-2222");
        cliente2.setEmail("dom_manoel@algaworks.com");

        return Arrays.asList(cliente1, cliente2);
    }


}
