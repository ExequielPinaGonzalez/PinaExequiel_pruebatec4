package pruebatecnica_4.pruebatecnica_4.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pruebatecnica_4.pruebatecnica_4.model.Vuelo;
import pruebatecnica_4.pruebatecnica_4.repository.VueloRepository;
import pruebatecnica_4.pruebatecnica_4.service.IVueloService;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class VueloController {

    @Autowired
    private VueloRepository vueloRepo;

    @Autowired
    private IVueloService vueloServi;

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "La operación se ejecutó correctamente"),
            @ApiResponse(responseCode = "204", description = "No se encontró ningún vuelo con el ID especificado"),
            @ApiResponse(responseCode = "400", description = "Algún parámetro no cumple con el formato o es requerido y no está presente."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")})

    @GetMapping("/flights")
    public ResponseEntity<List<Vuelo>> obtenerVuelos() {
        return new ResponseEntity<>(vueloServi.obtenerVuelos(), HttpStatus.OK);
    }

    @PostMapping("/flights/new")
    public String newVuelo(@RequestBody Vuelo vuelo) {
        vueloServi.guardarVuelo(vuelo);

        return "Vuelo Creado con éxito";
    }

    @DeleteMapping("/flights/delete/{id}")
    public ResponseEntity<?> borrarVuelo(@PathVariable Long id) {
        Vuelo vuelo = vueloServi.buscarVuelo(id);
        if (vuelo == null) {
            return new ResponseEntity<>("Vuelo no encontrado", HttpStatus.NOT_FOUND);
        }
        vueloServi.eliminarVuelo(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<?> buscarVuelo(@PathVariable Long id) {
        Vuelo vuelo = vueloServi.buscarVuelo(id);
        if (vuelo == null) {
            return new ResponseEntity<>("Vuelo no encontrado", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vuelo, HttpStatus.OK);
    }

    @PutMapping("/flights/edit/{id}")
    public ResponseEntity<?> editarVuelo(@PathVariable Long id, @RequestBody Vuelo vuelo) {

        Vuelo vueloEdit = vueloServi.buscarVuelo(id);

        if (vueloEdit == null) {
            return new ResponseEntity<>("Vuelo no encontrado", HttpStatus.NO_CONTENT);
        }
        vueloEdit.setCodigo(vuelo.getCodigo());
        vueloEdit.setOrigen(vuelo.getOrigen());
        vueloEdit.setDestino(vuelo.getDestino());
        vueloEdit.setFechaDeSalida(vuelo.getFechaDeSalida());
        vueloServi.guardarVuelo(vueloEdit);

        return new ResponseEntity<>(vueloEdit, HttpStatus.OK);
    }

}
