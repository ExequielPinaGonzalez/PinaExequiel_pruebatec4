package pruebatecnica_4.pruebatecnica_4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebatecnica_4.pruebatecnica_4.model.Habitacion;
import pruebatecnica_4.pruebatecnica_4.repository.HabitacionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HabitacionService implements IHabitacionService {

    @Autowired
    private HabitacionRepository habiRepo;

    @Override
    public List<Habitacion> obtenerHabitaciones() {
        return habiRepo.findAll();
    }

    @Override
    public void guardarHabitacion(Habitacion habitacion, Long idHotel) {
        habiRepo.save(habitacion);
    }

    @Override
    public void eliminarHabitacion(Long id) {
        habiRepo.deleteById(id);
    }

    @Override
    public Habitacion buscarHabitacion(Long id) {
        return habiRepo.findById(id).orElse(null);
    }
}

//    @Override
//    public List<Habitacion> findAvailableRooms(LocalDate dateFrom, LocalDate dateTo, String destination) {
//        // Obtener todas las habitaciones disponibles
//        List<Habitacion> allRooms = habiRepo.findAll();
//
//        // Filtrar las habitaciones disponibles según el destino
//        @Override
//        List<Habitacion> roomsInDestination = allRooms.stream()
//                .filter(room -> room.obtenerDestino().equalsIgnoreCase(destination))
//                .collect(Collectors.toList());
//
//        // Filtrar las habitaciones disponibles según la disponibilidad en el rango de fechas
//        List<Habitacion> disponibles = roomsInDestination.stream()
//                .filter(room -> room.esDisponible(dateFrom, dateTo))
//                .collect(Collectors.toList());
//
//        return disponibles;
//    }





