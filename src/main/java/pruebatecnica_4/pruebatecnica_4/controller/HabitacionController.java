package pruebatecnica_4.pruebatecnica_4.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pruebatecnica_4.pruebatecnica_4.model.Habitacion;
import pruebatecnica_4.pruebatecnica_4.repository.HabitacionRepository;
import pruebatecnica_4.pruebatecnica_4.service.IHabitacionService;

import java.time.LocalDate;
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

    @GetMapping("/room-booking/")
    public ResponseEntity<List<Habitacion>> obtenerHabitaciones() {
        return new ResponseEntity<>(habiServi.obtenerHabitaciones(), HttpStatus.OK);
    }

    @PostMapping("/room-booking/{idHotel}/new")
    public String newHabitacion(@RequestBody Habitacion habitacion, @PathVariable Long idHotel) {
        habiServi.guardarHabitacion(habitacion, idHotel);

        return "Habitacion creada con éxito";
    }

//    @GetMapping("/agency/rooms?")
//    public ResponseEntity<String> getAvailableRooms(
//            @RequestParam("dateFrom") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateFrom,
//            @RequestParam("dateTo") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dateTo,
//            @RequestParam("destination") String destination) {
//
//        List<Habitacion> disponibles = habiServi.habitacionesDisponibles(dateFrom, dateTo, destination);
//
//        if (disponibles.isEmpty()) {
//            return new ResponseEntity<>("No se encontraron habitaciones disponibles para las fechas y destino especificados",
//                    HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<Habitacion>(disponibles, HttpStatus.OK);
//    }


    @DeleteMapping("/room-booking/delete/{id}")
    public ResponseEntity<?> borrarHabitacion(@PathVariable Long id) {
        Habitacion habitacion = habiServi.buscarHabitacion(id);

        if(habitacion == null) {
            return  new ResponseEntity<>("Habitación no encontrada", HttpStatus.NOT_FOUND);
        }
        habiServi.eliminarHabitacion(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/room-booking//{id}")
    public ResponseEntity<?> buscarHabitacion(@PathVariable Long id) {
        Habitacion habitacion = habiServi.buscarHabitacion(id);

        if (habitacion == null) {
            return new ResponseEntity<>("Habitación no encontrada", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(habitacion, HttpStatus.OK);
    }

    @PutMapping("room-booking//{idHotel}/edit/{idHabitacion}")
    public ResponseEntity<?> editarHabitacion(@PathVariable Long idHotel,@PathVariable Long idHabitacion, @RequestBody Habitacion habitacion ) {

        Habitacion habitacionEdit = habiServi.buscarHabitacion(idHabitacion);

        if (habitacionEdit == null) {
            return new ResponseEntity<>("Vuelo no encontrado", HttpStatus.NO_CONTENT);
        }
        habitacionEdit.setTipoDehabitacion(habitacion.getTipoDehabitacion());
        habitacionEdit.setPrecioPorNoche(habitacion.getPrecioPorNoche());
        habitacionEdit.setDisponibleDesde(habitacion.getDisponibleDesde());
        habitacionEdit.setDisponibleHasta(habitacion.getDisponibleHasta());
        habitacionEdit.setHotel(habitacion.getHotel());

        habiServi.guardarHabitacion(habitacionEdit, idHotel);

        return new ResponseEntity<>(habitacionEdit, HttpStatus.OK);
    }

}
