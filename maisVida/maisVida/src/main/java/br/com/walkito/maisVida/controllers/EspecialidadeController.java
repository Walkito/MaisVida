package br.com.walkito.maisVida.controllers;

import br.com.walkito.maisVida.model.entities.Especialidade;
import br.com.walkito.maisVida.model.repository.EspecialidadesRepository;
import br.com.walkito.maisVida.services.EspecialidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/especialidades")
public class EspecialidadeController {
    @Autowired
    EspecialidadeService especialidadeService;

    @PostMapping(path = "/cadastrar")
    public Especialidade cadastrarEspecialidade(@Valid Especialidade especialidade){
        return especialidadeService.cadastrarEspecialidade(especialidade);
    }

    @GetMapping
    public Iterable<Especialidade> obterEspecialidades(int pagina){
        return especialidadeService.obterEspecialidades(pagina);
    }

    @GetMapping(path = "/especialidade")
    public Iterable<Especialidade> obterEspecialidades(@RequestParam(name = "id") int id,
                                            @RequestParam(name = "nome") String nome,
                                            @RequestParam(name = "tipo") String tipo){
        return especialidadeService.obterEspecialidade(id, nome, tipo);
    }

    @PutMapping(path = "/especialidade/editar")
    public Especialidade editarEspecialidade(@RequestParam(name = "id") int id, Especialidade especialidade){
        return especialidadeService.editarEspecialidade(id, especialidade);
    }

    @DeleteMapping(path = "/especialidade/editar")
    public boolean excluirEspecialidade(@RequestParam(name = "id") int id){
        return especialidadeService.excluirEspecialidade(id);
    }
}
