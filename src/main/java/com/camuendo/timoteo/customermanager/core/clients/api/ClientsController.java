package com.camuendo.timoteo.customermanager.core.clients.api;

import com.camuendo.timoteo.customermanager.core.clients.ClientsServiceImpl;
import com.camuendo.timoteo.customermanager.core.clients.dto.CreateClientDTO;
import com.camuendo.timoteo.customermanager.core.clients.dto.UpdateClientDTO;
import com.camuendo.timoteo.customermanager.core.clients.entities.Client;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final ClientsServiceImpl clientsService;

    public ClientsController(ClientsServiceImpl clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping
    public List<Client> getAll() {
        return this.clientsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.clientsService.findById(id));
    }

    @PostMapping
    public Client create(@RequestBody @Valid CreateClientDTO client) {
        return this.clientsService.save(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(
            @PathVariable Long id,
            @RequestBody @Valid UpdateClientDTO client) {
        return this.clientsService.update(id, client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        this.clientsService.delete(id);
    }
}
