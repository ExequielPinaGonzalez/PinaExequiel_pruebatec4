package pruebatecnica_4.pruebatecnica_4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebatecnica_4.pruebatecnica_4.model.Hotel;
import pruebatecnica_4.pruebatecnica_4.repository.HotelRepository;

import java.util.List;

@Service
public class HotelService implements IHotelService {

    @Autowired
    private HotelRepository hotelRepo;

    @Override
    public List<Hotel> obtenerHoteles() {
        return hotelRepo.findAll();
    }

    @Override
    public void guardarHotel(Hotel hotel) {
        hotelRepo.save(hotel);
    }

    @Override
    public void eliminarHotel(Long id) {
        hotelRepo.deleteById(id);
    }


    @Override
    public Hotel buscarHotel(Long id) {
        return hotelRepo.findById(id).orElse(null);
    }


}
