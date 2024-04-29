package pruebatecnica_4.pruebatecnica_4.service;

import pruebatecnica_4.pruebatecnica_4.model.Vuelo;

import java.util.List;

public interface IVueloService {

    List<Vuelo> obtenerVuelos();

    void guardarVuelo(Vuelo vuelo);

    void eliminarVuelo(Long id);

    Vuelo buscarVuelo(Long id);
}
