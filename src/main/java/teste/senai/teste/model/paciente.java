package teste.senai.teste.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "paciente")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPaciente")
    private Long idPaciente;

    @Column(name = "nome")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "IdPai")
    private pai responsavel;
}