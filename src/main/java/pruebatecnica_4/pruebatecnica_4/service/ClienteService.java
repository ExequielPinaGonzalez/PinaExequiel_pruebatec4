package pruebatecnica_4.pruebatecnica_4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebatecnica_4.pruebatecnica_4.model.Cliente;
import pruebatecnica_4.pruebatecnica_4.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    @Override
    public List<Cliente> obtenerClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public void guardarCliente(Cliente cliente) {
        clienteRepo.save(cliente);
    }

    @Override
    public void eliminarCliente(Long id) {
        clienteRepo.deleteById(id);
    }

    @Override
    public Cliente buscarCliente(Long id) {
        return clienteRepo.findById(id).orElse(null);
    }


}
