package teste.senai.teste.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pai")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class pai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPai")
    private Long idPai;

    @Column (name ="Nome")
    private String nome;

    @Column (name ="CPF")
    private String CPF;

    @Column (name = "Telefone")
    private String Telefone;
}
