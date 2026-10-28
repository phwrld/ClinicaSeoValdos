package teste.senai.teste.controller;

import org.springframework.web.bind.annotation.*;
import teste.senai.teste.service.usuarioService;
import teste.senai.teste.service.usuarioService.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class usuarioController {

    private final usuarioService service;

    public usuarioController(usuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<RespostaUsuario> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public RespostaUsuario buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public RespostaUsuario cadastrar(
            @RequestBody DadosUsuario dados) {
        return service.cadastrar(dados);
    }

    @PutMapping("/{id}")
    public RespostaUsuario atualizar(
            @PathVariable Long id,
            @RequestBody DadosUsuario dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}