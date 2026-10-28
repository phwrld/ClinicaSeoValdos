package teste.senai.teste.controller;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClinicaController {
    public record Responsavel(
            String cpf,
            String telefone
    ){}

    public record Paciente(
            int id,
            String nome,
            int responsavelId
    ){}

    public record Consulta(
            int id,
            LocalDate data,
            LocalTime hora,
            int pacienteId,
            String pacienteNome,
            int responsavelId,
            String ResponsavelNome
    ){}
}
