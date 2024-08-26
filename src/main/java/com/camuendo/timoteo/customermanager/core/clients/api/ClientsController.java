package com.camuendo.timoteo.customermanager.core.clients.api;

import com.camuendo.timoteo.customermanager.core.clients.ClientsService;
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

    private final ClientsService clientsService;

    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok(this.clientsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.clientsService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody @Valid CreateClientDTO client) {
        return ResponseEntity.ok(this.clientsService.save(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(
            @PathVariable Long id,
            @RequestBody @Valid UpdateClientDTO client) {
        return ResponseEntity.ok(this.clientsService.update(id, client));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        this.clientsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
