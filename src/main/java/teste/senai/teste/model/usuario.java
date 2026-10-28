package teste.senai.teste.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "IdUsuario")
    private Long IdUsuario;

    @Column(name ="nome")
    private String nome;

    @Column (name ="login")
    private String Login;

    @Column (name = "senha")
    private String Senha;
}
