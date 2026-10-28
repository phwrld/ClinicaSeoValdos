package teste.senai.teste.controller;

import org.springframework.web.bind.annotation.*;
import teste.senai.teste.service.pacienteService;
import teste.senai.teste.service.pacienteService.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class pacienteController {

    private final pacienteService service;

    public pacienteController(pacienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<RespostaPaciente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public RespostaPaciente buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public RespostaPaciente cadastrar(
            @RequestBody DadosPaciente dados) {
        return service.cadastrar(dados);
    }

    @PutMapping("/{id}")
    public RespostaPaciente atualizar(
            @PathVariable Long id,
            @RequestBody DadosPaciente dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}