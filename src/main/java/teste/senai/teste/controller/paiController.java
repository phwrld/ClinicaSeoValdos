package teste.senai.teste.controller;

import org.springframework.web.bind.annotation.*;
import teste.senai.teste.model.pai;
import teste.senai.teste.service.paiService;

import java.util.List;

@RestController
@RequestMapping("/pais")
public class paiController {

    private final paiService service;

    public paiController(paiService service) {
        this.service = service;
    }

    @GetMapping
    public List<pai> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public pai buscar(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public pai cadastrar(@RequestBody pai novo) {
        return service.cadastrar(novo);
    }

    @PutMapping("/{id}")
    public pai atualizar(@PathVariable Long id, @RequestBody pai dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}