package pruebatecnica_4.pruebatecnica_4.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pruebatecnica_4.pruebatecnica_4.model.Cliente;
import pruebatecnica_4.pruebatecnica_4.model.Hotel;
import pruebatecnica_4.pruebatecnica_4.repository.ClienteRepository;
import pruebatecnica_4.pruebatecnica_4.service.IClienteService;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private IClienteService clienteServi;

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "La operación se ejecutó correctamente"),
            @ApiResponse(responseCode = "204", description = "No se encontró ningún paciente con el ID especificado"),
            @ApiResponse(responseCode = "400", description = "Algún parámetro no cumple con el formato o es requerido y no está presente."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")})

    @GetMapping("clientes")
    public ResponseEntity<List<Cliente>> obtenerClientes() {
        return new ResponseEntity<>(clienteServi.obtenerClientes(), HttpStatus.OK);
    }

    @PostMapping("clintes/new")
    public String newCliente(@RequestBody Cliente cliente) {
        clienteServi.guardarCliente(cliente);

        return "New Cliente";
    }

    @DeleteMapping("clientes/delete/{id}")
    public ResponseEntity<?> borrarCliente(@PathVariable Long id) {
        Cliente cliente = clienteServi.buscarCliente(id);
        if (cliente == null) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NO_CONTENT);
        }
        clienteServi.eliminarCliente(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("clientes/{id}")
    public ResponseEntity<?> buscarCliente(@PathVariable Long id) {
        Cliente cliente = clienteServi.buscarCliente(id);
        if (cliente == null) {
            return new ResponseEntity<>("Id no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/clientes/edit/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {

        Cliente clienteEdit = clienteServi.buscarCliente(id);

        if (clienteEdit == null) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NO_CONTENT);
        }
        clienteEdit.setNombre(cliente.getNombre());
        clienteEdit.setApellido(cliente.getApellido());
        clienteEdit.setDni(cliente.getDni());
        clienteEdit.setTelefono(cliente.getTelefono());
        clienteServi.guardarCliente(clienteEdit);

        return new ResponseEntity<>(clienteEdit, HttpStatus.OK);
    }
}