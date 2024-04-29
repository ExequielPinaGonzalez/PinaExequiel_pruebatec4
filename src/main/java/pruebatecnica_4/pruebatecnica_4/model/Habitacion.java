package pruebatecnica_4.pruebatecnica_4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String tipoDehabitacion;
    private Double precioPorNoche;
    private LocalDate disponibleDesde;
    private LocalDate disponibleHasta;
    private boolean reservado;
    @ManyToOne
    private Hotel hotel;


}
