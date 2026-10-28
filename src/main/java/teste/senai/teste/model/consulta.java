package teste.senai.teste.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "consulta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "IdConsulta")
    private Long IdConsulta;

    @Column (name = "dataConsulta")
    private LocalDate Data;

    @Column (name = "hora")
    private LocalTime hora;

    @ManyToOne
    @JoinColumn(name = "IdPaciente")
    private paciente paciente;

}
