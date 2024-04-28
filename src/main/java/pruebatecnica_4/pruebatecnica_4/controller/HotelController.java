package pruebatecnica_4.pruebatecnica_4.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pruebatecnica_4.pruebatecnica_4.model.Hotel;
import pruebatecnica_4.pruebatecnica_4.repository.HotelRepository;
import pruebatecnica_4.pruebatecnica_4.service.IHotelService;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private IHotelService hotelService;

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "La operación se ejecutó correctamente"), @ApiResponse(responseCode = "204", description = "No se encontró ningún paciente con el ID especificado"), @ApiResponse(responseCode = "400", description = "Algún parámetro no cumple con el formato o es requerido y no está presente."), @ApiResponse(responseCode = "500", description = "Error interno del servidor")})


    @GetMapping("/hotels")
    public ResponseEntity<List<Hotel>> obtenerHoteles() {

        return new ResponseEntity<>(hotelService.obtenerHoteles(), HttpStatus.OK);
    }

    @PostMapping("/hotels/new")
    public String newHotel(@RequestBody Hotel hotel) {
        hotelService.guardarHotel(hotel);

        return "New Hotel";
    }

    @DeleteMapping("/hotels/delete/{id}")
    public ResponseEntity<?> borrarHotel(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>("Id no encontrado", HttpStatus.NO_CONTENT);
        }
        hotelService.eliminarHotel(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/hotels/{id}")
    public ResponseEntity<List<Hotel>> buscarHotel(@PathVariable Long id) {
        List<Hotel> hoteles = hotelService.findAllById(id);

        return null;
    }

    @PutMapping("/hotels/edit/{id}")
    public ResponseEntity<?> editarHotel(@PathVariable Long id,
                                         @RequestParam("codigoHotel") String codigoEdit,
                                         @RequestParam("nombre") String nombreEdit,
                                         @RequestParam("lugarCiudad") String lugarCiudadEdit) {

        Hotel hotel = hotelService.buscarHotel(id);

        if (hotel == null) {
            return new ResponseEntity<>("Hotel no encontrado", HttpStatus.NO_CONTENT);
        }
        hotel.setCodigoHotel(codigoEdit);
        hotel.setNombre(nombreEdit);
        hotel.setLugarCiudad(lugarCiudadEdit);
        hotelService.guardarHotel(hotel);

        return new ResponseEntity<>(hotel, HttpStatus.OK);

    }


}