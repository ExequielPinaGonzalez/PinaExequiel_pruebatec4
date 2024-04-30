package pruebatecnica_4.pruebatecnica_4.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pruebatecnica_4.pruebatecnica_4.model.Habitacion;
import pruebatecnica_4.pruebatecnica_4.model.Vuelo;
import pruebatecnica_4.pruebatecnica_4.repository.HabitacionRepository;
import pruebatecnica_4.pruebatecnica_4.service.IHabitacionService;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class HabitacionController {


    @Autowired
    private HabitacionRepository habiRepo;

    @Autowired
    private IHabitacionService habiServi;


    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "La operación se ejecutó correctamente"),
            @ApiResponse(responseCode = "204", description = "No se encontró ningún vuelo con el ID especificado"),
            @ApiResponse(responseCode = "400", description = "Algún parámetro no cumple con el formato o es requerido y no está presente."),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")})

    @GetMapping("/habitaciones")
    public ResponseEntity<List<Habitacion>> obtenerHabitaciones() {
        return new ResponseEntity<>(habiServi.obtenerHabitaciones(), HttpStatus.OK);
    }

    @PostMapping("/habitaciones/new")
    public String newHabitacion(@RequestBody Habitacion habitacion) {
        habiServi.guardarHabitacion(habitacion);

        return "Habitacion creada con éxito";
    }

    @DeleteMapping("/habitaciones/delete/{id}")
    public ResponseEntity<?> borrarHabitacion(@PathVariable Long id) {
        Habitacion habitacion = habiServi.buscarHabitacion(id);

        if(habitacion == null) {
            return  new ResponseEntity<>("Habitación no encontrada", HttpStatus.NOT_FOUND);
        }
        habiServi.eliminarHabitacion(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/habitaciones/{id}")
    public ResponseEntity<?> buscarHabitacion(@PathVariable Long id) {
        Habitacion habitacion = habiServi.buscarHabitacion(id);

        if (habitacion == null) {
            return new ResponseEntity<>("Habitación no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(habitacion, HttpStatus.OK);
    }

    @PutMapping("/habitaciones/edit/{id}")
    public ResponseEntity<?> editarHabitacion(@PathVariable Long id, @RequestBody Habitacion habitacion) {

        Habitacion habitacionEdit = habiServi.buscarHabitacion(id);

        if (habitacionEdit == null) {
            return new ResponseEntity<>("Vuelo no encontrado", HttpStatus.NO_CONTENT);
        }
        habitacionEdit.setTipoDehabitacion(habitacion.getTipoDehabitacion());
        habitacionEdit.setPrecioPorNoche(habitacion.getPrecioPorNoche());
        habitacionEdit.setDisponibleDesde(habitacion.getDisponibleDesde());
        habitacionEdit.setDisponibleHasta(habitacion.getDisponibleHasta());
        habitacionEdit.setHotel(habitacion.getHotel());

        habiServi.guardarHabitacion(habitacionEdit);

        return new ResponseEntity<>(habitacionEdit, HttpStatus.OK);
    }

}
