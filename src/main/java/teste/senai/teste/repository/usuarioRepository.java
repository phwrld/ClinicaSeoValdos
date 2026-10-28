package teste.senai.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teste.senai.teste.model.usuario;

public interface usuarioRepository extends JpaRepository<usuario, Long> {

}