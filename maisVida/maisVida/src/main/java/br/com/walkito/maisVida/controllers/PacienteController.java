package br.com.walkito.maisVida.controllers;

import br.com.walkito.maisVida.model.entities.Paciente;
import br.com.walkito.maisVida.model.repository.PacienteRepository;
import br.com.walkito.maisVida.services.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/pacientes")
public class PacienteController {
    @Autowired
    PacienteService pacienteService;

    @Autowired
    PacienteRepository repository;

    @PostMapping(path = "/cadastrar")
    public Paciente cadastrarPaciente(@Valid Paciente paciente){
        return pacienteService.cadastrarPaciente(paciente);
    }

    @GetMapping(path = "/paciente")
    public Iterable<Paciente> obterPacientes(@RequestParam(name = "id") int id,
                                         @RequestParam(name = "nome") String nome,
                                         @RequestParam(name = "sobrenome") String sobrenome,
                                         @RequestParam(name = "cpf") String cpf){
        return pacienteService.obterPaciente(id,nome,sobrenome,cpf);
    }

    @GetMapping
    public Iterable<Paciente> obterPacientes(@RequestParam(name = "pagina") int pagina){
        return pacienteService.obterPacientes(pagina);
    }

    @PutMapping("/paciente/editar")
    public Paciente editarPaciente(@RequestParam(name = "id") int id, Paciente paciente){
        return pacienteService.editarPaciente(id, paciente);
    }

    @DeleteMapping("/paciente/editar")
    public boolean excluirPaciente(@RequestParam(name = "id") int id){
        return pacienteService.excluirPaciente(id);
    }
}

