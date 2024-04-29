package pruebatecnica_4.pruebatecnica_4.service;

import pruebatecnica_4.pruebatecnica_4.model.Cliente;

import java.util.List;

public interface IClienteService {
    public List<Cliente> obtenerClientes();


    public void guardarCliente(Cliente cliente);

    public void eliminarCliente(Long id);

    public Cliente buscarCliente(Long id);



}
