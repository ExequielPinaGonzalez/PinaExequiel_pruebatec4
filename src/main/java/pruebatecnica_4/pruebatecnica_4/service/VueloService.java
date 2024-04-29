package pruebatecnica_4.pruebatecnica_4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebatecnica_4.pruebatecnica_4.model.Vuelo;
import pruebatecnica_4.pruebatecnica_4.repository.VueloRepository;

import java.util.List;
@Service
public class VueloService implements IVueloService {

    @Autowired
    private VueloRepository vueloRepo;

    @Override
    public List<Vuelo> obtenerVuelos() {
        return vueloRepo.findAll();
    }

    @Override
    public void guardarVuelo(Vuelo vuelo) {
        vueloRepo.save(vuelo);
    }

    @Override
    public void eliminarVuelo(Long id) {
        vueloRepo.deleteById(id);
    }

    @Override
    public Vuelo buscarVuelo(Long id) {
        return vueloRepo.findById(id).orElse(null);
    }

}
