package teste.senai.teste.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import teste.senai.teste.model.consulta;
import teste.senai.teste.model.paciente;
import teste.senai.teste.repository.consultaRepository;
import teste.senai.teste.repository.pacienteRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class consultaService {

    private final consultaRepository repository;
    private final pacienteRepository pacienteRepo;

    public record DadosConsulta(
            LocalDate data,
            LocalTime hora,
            Long idPaciente
    ) {}

    public record RespostaConsulta(
            Long idConsulta,
            LocalDate data,
            LocalTime hora,
            Long idPaciente
    ) {}

    public consultaService(
            consultaRepository repository,
            pacienteRepository pacienteRepo) {
        this.repository = repository;
        this.pacienteRepo = pacienteRepo;
    }

    private consulta encontrar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Consulta nao encontrada"));
    }

    private paciente encontrarPaciente(Long id) {
        if (id == null) return null;

        return pacienteRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Paciente nao encontrado"));
    }

    private RespostaConsulta resposta(consulta c) {
        return new RespostaConsulta(
                c.getIdConsulta(),
                c.getData(),
                c.getHora(),
                c.getPaciente() == null
                        ? null
                        : c.getPaciente().getIdPaciente()
        );
    }

    public List<RespostaConsulta> listar() {
        return repository.findAll()
                .stream()
                .map(this::resposta)
                .toList();
    }

    public RespostaConsulta buscar(Long id) {
        return resposta(encontrar(id));
    }

    private void preencher(consulta c, DadosConsulta dados) {
        if (dados.data() == null || dados.hora() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Data e hora obrigatorias");
        }

        c.setData(dados.data());
        c.setHora(dados.hora());
        c.setPaciente(encontrarPaciente(dados.idPaciente()));
    }

    public RespostaConsulta cadastrar(DadosConsulta dados) {
        consulta c = new consulta();
        preencher(c, dados);
        return resposta(repository.save(c));
    }

    public RespostaConsulta atualizar(
            Long id, DadosConsulta dados) {

        consulta c = encontrar(id);
        preencher(c, dados);
        return resposta(repository.save(c));
    }

    public void excluir(Long id) {
        repository.delete(encontrar(id));
    }
}