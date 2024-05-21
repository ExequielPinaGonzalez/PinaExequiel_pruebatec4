import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pruebatecnica_4.pruebatecnica_4.controller.ClienteController;

@WebMvcTest(ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteServi;

    @Test
    void obtenerClientes_debeRetornarListaDeClientes() throws Exception {
        // Datos de prueba
        Cliente cliente1 = new Cliente(1L, "Juan", "Perez");
        Cliente cliente2 = new Cliente(2L, "Ana", "Gomez");
        List<Cliente> listaClientes = Arrays.asList(cliente1, cliente2);

        // Mockear el servicio
        when(clienteServi.obtenerClientes()).thenReturn(listaClientes);

        // Realizar la solicitud y verificar la respuesta
        mockMvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"nombre\":\"Juan\",\"apellido\":\"Perez\"},{\"id\":2,\"nombre\":\"Ana\",\"apellido\":\"Gomez\"}]"));
    }
}


class Cliente {
    private Long id;
    private String nombre;
    private String apellido;

    // Constructor, getters y setters
    public Cliente(Long id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    // Getters y setters (omitir por brevedad)
}


interface ClienteService {
    List<Cliente> obtenerClientes();
}
