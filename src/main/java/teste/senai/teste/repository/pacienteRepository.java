package teste.senai.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teste.senai.teste.model.paciente;

public interface pacienteRepository extends JpaRepository<paciente, Long> {

}