package teste.senai.teste.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import teste.senai.teste.model.pai;
import teste.senai.teste.repository.paiRepository;

import java.util.List;

@Service
public class paiService {

    private final paiRepository repository;

    public paiService(paiRepository repository) {
        this.repository = repository;
    }

    public List<pai> listarTodos() {
        return repository.findAll();
    }

    public pai buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Responsavel nao encontrado"));
    }

    public pai cadastrar(pai novo) {
        novo.setIdPai(null);
        return repository.save(novo);
    }

    public pai atualizar(Long id, pai dados) {
        pai existente = buscarPorId(id);

        existente.setNome(dados.getNome());
        existente.setCPF(dados.getCPF());
        existente.setTelefone(dados.getTelefone());

        return repository.save(existente);
    }

    public void excluir(Long id) {
        pai existente = buscarPorId(id);
        repository.delete(existente);
    }
}