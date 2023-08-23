package br.com.walkito.maisVida.controllers;

import br.com.walkito.maisVida.model.entities.Consulta;
import br.com.walkito.maisVida.model.entities.Especialidade;
import br.com.walkito.maisVida.model.entities.Paciente;
import br.com.walkito.maisVida.model.repository.ConsultaRepository;
import br.com.walkito.maisVida.model.repository.EspecialidadesRepository;
import br.com.walkito.maisVida.model.repository.PacienteRepository;
import br.com.walkito.maisVida.services.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/consultas")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @PostMapping(path = "/agendar")
    public Consulta agendarConsulta(@RequestParam(name = "idPaciente") int idPaciente,
                                    @RequestParam(name = "idEspecialidade") int idEspecialidade,
                                    @RequestParam(name = "data") String data
                                    ){

        return consultaService.agendarConsulta(idPaciente, idEspecialidade, data);
    }

    @GetMapping(path = "/consulta")
    public Iterable<Consulta> obterConsulta(@RequestParam(name = "id") int id,
                                                   @RequestParam(name = "data") String data,
                                                   @RequestParam(name = "especialidade") String especialidade,
                                                   @RequestParam(name = "tipo") String tipo,
                                                   @RequestParam(name = "nome") String nome,
                                                   @RequestParam(name = "sobrenome") String sobrenome
    ){
        return consultaService.obterConsulta(id, data, especialidade, tipo, nome, sobrenome);
    }

    @GetMapping
    public Iterable<Consulta> obterConsultas(@RequestParam(name = "pagina") int pagina){
        return consultaService.obterConsultas(pagina);
    }

    @PutMapping(path = "/consulta/editar")
    public Consulta editarConsulta(@RequestParam(name = "id") int id, Consulta consulta){
        return consultaService.editarConsulta(id, consulta);
    }

    @DeleteMapping(path = "/consulta/editar")
    public boolean deletarConsulta(@RequestParam(name = "id") int id){
        return consultaService.deletarConsulta(id);
    }
}
