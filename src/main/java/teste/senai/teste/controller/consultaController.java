package teste.senai.teste.controller;

import org.springframework.web.bind.annotation.*;
import teste.senai.teste.service.consultaService;
import teste.senai.teste.service.consultaService.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class consultaController {

    private final consultaService service;

    public consultaController(consultaService service) {
        this.service = service;
    }

    @GetMapping
    public List<RespostaConsulta> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public RespostaConsulta buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public RespostaConsulta cadastrar(
            @RequestBody DadosConsulta dados) {
        return service.cadastrar(dados);
    }

    @PutMapping("/{id}")
    public RespostaConsulta atualizar(
            @PathVariable Long id,
            @RequestBody DadosConsulta dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}