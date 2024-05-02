package pruebatecnica_4.pruebatecnica_4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebatecnica_4.pruebatecnica_4.model.Habitacion;
import pruebatecnica_4.pruebatecnica_4.repository.HabitacionRepository;

import java.util.List;

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
    public void  eliminarHabitacion(Long id) {
        habiRepo.deleteById(id);
    }

    @Override
    public Habitacion buscarHabitacion(Long id) {return  habiRepo.findById(id).orElse(null);}


}
