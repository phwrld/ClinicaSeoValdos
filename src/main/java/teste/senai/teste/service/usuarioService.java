package teste.senai.teste.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import teste.senai.teste.model.usuario;
import teste.senai.teste.repository.usuarioRepository;

import java.util.List;

@Service
public class usuarioService {

    private final usuarioRepository repository;
    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    public record DadosUsuario(
            String nome,
            String login,
            String senha
    ) {}

    public record RespostaUsuario(
            Long idUsuario,
            String nome,
            String login
    ) {}

    public usuarioService(usuarioRepository repository) {
        this.repository = repository;
    }

    private usuario encontrar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuario nao encontrado"));
    }

    private RespostaUsuario resposta(usuario u) {
        return new RespostaUsuario(
                u.getIdUsuario(),
                u.getNome(),
                u.getLogin()
        );
    }

    public List<RespostaUsuario> listar() {
        return repository.findAll()
                .stream()
                .map(this::resposta)
                .toList();
    }

    public RespostaUsuario buscar(Long id) {
        return resposta(encontrar(id));
    }

    private void preencher(usuario u, DadosUsuario dados) {
        if (dados.nome() == null || dados.nome().isBlank()
                || dados.login() == null || dados.login().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nome e login obrigatorios");
        }

        u.setNome(dados.nome());
        u.setLogin(dados.login());

        if (dados.senha() != null && !dados.senha().isBlank()) {
            u.setSenha(encoder.encode(dados.senha()));
        }
    }

    public RespostaUsuario cadastrar(DadosUsuario dados) {
        if (dados.senha() == null || dados.senha().isBlank()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Senha obrigatoria");
        }

        usuario u = new usuario();
        preencher(u, dados);

        return resposta(repository.save(u));
    }

    public RespostaUsuario atualizar(
            Long id, DadosUsuario dados) {

        usuario u = encontrar(id);
        preencher(u, dados);

        return resposta(repository.save(u));
    }

    public void excluir(Long id) {
        repository.delete(encontrar(id));
    }
}