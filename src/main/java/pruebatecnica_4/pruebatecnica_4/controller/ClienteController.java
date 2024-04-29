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
    public ResponseEntity<List<Cliente>> borrarCliente(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>("Id no encontrado", HttpStatus.NO_CONTENT);
        }
        clienteServi.eliminarCliente(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("clientes/{id}")
    public ResponseEntity<List<Cliente>> buscarCliente(@PathVariable Long id) {
        List<Cliente> clientes = clienteServi.buscarClientePorId(id);

        return null;
    }

    @PutMapping("/clientes/edit/{id}")
    public ResponseEntity<?> editarCliente(@PathVariable Long id,
                                           @RequestParam("nombre") String nombreEdit,
                                           @RequestParam("apellido") String apellidoEdit,
                                           @RequestParam("dni") String dniEdit,
                                           @RequestParam("telefono") int telefonoEdit) {

        Cliente cliente = clienteServi.buscarCliente(id);

        if (cliente == null) {
            return new ResponseEntity<>("Cliente no encontrado", HttpStatus.NO_CONTENT);
        }
        cliente.setNombre(nombreEdit);
        cliente.setApellido(apellidoEdit);
        cliente.setDni(dniEdit);
        cliente.setTelefono(telefonoEdit);
        clienteServi.guardarCliente(cliente);

        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
}