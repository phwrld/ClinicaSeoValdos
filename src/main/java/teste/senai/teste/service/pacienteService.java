package teste.senai.teste.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import teste.senai.teste.model.paciente;
import teste.senai.teste.model.pai;
import teste.senai.teste.repository.pacienteRepository;
import teste.senai.teste.repository.paiRepository;

import java.util.List;

@Service
public class pacienteService {

    private final pacienteRepository repository;
    private final paiRepository paiRepo;

    public record DadosPaciente(
            String nome,
            Long idPai
    ) {}

    public record RespostaPaciente(
            Long idPaciente,
            String nome,
            Long idPai
    ) {}

    public pacienteService(
            pacienteRepository repository,
            paiRepository paiRepo) {
        this.repository = repository;
        this.paiRepo = paiRepo;
    }

    private RespostaPaciente resposta(paciente p) {
        return new RespostaPaciente(
                p.getIdPaciente(),
                p.getNome(),
                p.getResponsavel() == null
                        ? null
                        : p.getResponsavel().getIdPai()
        );
    }

    public List<RespostaPaciente> listar() {
        return repository.findAll()
                .stream()
                .map(this::resposta)
                .toList();
    }

    public RespostaPaciente buscar(Long id) {
        return resposta(encontrar(id));
    }

    private paciente encontrar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Paciente nao encontrado"));
    }

    private pai encontrarPai(Long id) {
        if (id == null) return null;

        return paiRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Responsavel nao encontrado"));
    }

    public RespostaPaciente cadastrar(DadosPaciente dados) {
        if (dados.nome() == null || dados.nome().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Nome obrigatorio");
        }

        paciente p = new paciente();
        p.setNome(dados.nome());
        p.setResponsavel(encontrarPai(dados.idPai()));

        return resposta(repository.save(p));
    }

    public RespostaPaciente atualizar(
            Long id, DadosPaciente dados) {

        paciente p = encontrar(id);

        if (dados.nome() == null || dados.nome().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Nome obrigatorio");
        }

        p.setNome(dados.nome());
        p.setResponsavel(encontrarPai(dados.idPai()));

        return resposta(repository.save(p));
    }

    public void excluir(Long id) {
        repository.delete(encontrar(id));
    }
}