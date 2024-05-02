package pruebatecnica_4.pruebatecnica_4.service;

import pruebatecnica_4.pruebatecnica_4.model.Habitacion;

import java.time.LocalDate;
import java.util.List;

public interface IHabitacionService {

    List<Habitacion> obtenerHabitaciones();

    Habitacion buscarHabitacion(Long id);

    void guardarHabitacion(Habitacion habitacion, Long idHotel);

    void eliminarHabitacion(Long id);

}


//    List<Habitacion> habitacionesDisponibles(LocalDate dateFrom, LocalDate dateTo, String destination);
//}
