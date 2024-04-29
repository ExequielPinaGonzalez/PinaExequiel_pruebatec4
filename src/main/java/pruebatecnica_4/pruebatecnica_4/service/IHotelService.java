package pruebatecnica_4.pruebatecnica_4.service;

import pruebatecnica_4.pruebatecnica_4.model.Hotel;

import java.util.List;

public interface IHotelService {

    List<Hotel> obtenerHoteles();

    Hotel buscarHotel(Long id);

    void guardarHotel(Hotel hotel);

    void eliminarHotel(Long id);


}
