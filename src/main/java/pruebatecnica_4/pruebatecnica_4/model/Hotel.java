package pruebatecnica_4.pruebatecnica_4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String codigoHotel;
    private String nombre;
    private String lugarCiudad;
    @OneToMany
    private List<Habitacion>habitaciones;

}
