package br.com.walkito.maisVida.services;

import br.com.walkito.maisVida.model.entities.Consulta;
import br.com.walkito.maisVida.model.entities.Especialidade;
import br.com.walkito.maisVida.model.entities.Paciente;
import br.com.walkito.maisVida.model.repository.ConsultaRepository;
import br.com.walkito.maisVida.model.repository.EspecialidadesRepository;
import br.com.walkito.maisVida.model.repository.PacienteRepository;
import br.com.walkito.maisVida.model.specifications.ConsultaSpecifications;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.*;

@Service
public class ConsultaService {
    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    EspecialidadesRepository especialidadesRepository;

    public Iterable<Consulta> obterConsulta(int id, String data, String especialidade, String tipo,
                                            String nome, String sobrenome){
        Map<String, Object> customJson = new HashMap<>();
        List<Map<String, Object>> Jsons = new ArrayList<>();
        Iterable<Consulta> consulta;

        boolean estaVazio = verificaParametros(id, data, especialidade, tipo, nome, sobrenome);

        if (estaVazio){
           consulta = obterConsultas(0);
        } else {
            Specification<Consulta> spec = ConsultaSpecifications.comId(id)
                    .and(ConsultaSpecifications.comData(data))
                    .and(ConsultaSpecifications.comEspecialidade(especialidade))
                    .and(ConsultaSpecifications.comTipo(tipo))
                    .and(ConsultaSpecifications.comNomePaciente(nome))
                    .and(ConsultaSpecifications.comSobrenomePaciente(sobrenome));
            consulta = consultaRepository.findAll(spec);
        }

        return consulta;
    }

    public Iterable<Consulta> obterConsultas(int pagina){
        try {
            Pageable page = PageRequest.of(pagina,10);
            return consultaRepository.findAll(page);
        } catch(Exception e) {
            throw e;
        }
    }

    public Consulta agendarConsulta(int idPaciente, int idEspecialidade, String  data){
        Consulta consulta = criarConsulta(idPaciente, idEspecialidade, data);
        try{
            return consultaRepository.save(consulta);
        }catch (Exception e){
            throw e;
        }
    }

    public Consulta editarConsulta(int id, Consulta consulta){
        try{
            Consulta consultaExistente = consultaRepository.searchConsultaById(id);
            BeanUtils.copyProperties(consulta, consultaExistente, getNullPropertyNames(consulta));
            return consultaRepository.save(consultaExistente);
        } catch (Exception e){
            throw e;
        }
    }

    public boolean deletarConsulta(int id){
        try {
            Optional<Consulta> consulta = consultaRepository.findById(id);
            if(!consulta.get().equals(null)){
                consultaRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
    private boolean verificaParametros(int id, String data, String especialidade, String tipo,
                                       String nome, String sobrenome){
        if (id == 0 && (data.isEmpty() || data.equals(null)) && (especialidade.isEmpty() || especialidade.equals(null))
            && (tipo.isEmpty() || tipo.equals(null)) && (nome.isEmpty() || nome.equals(null)) && (sobrenome.isEmpty()
            || sobrenome.equals(null))){
            return true;
        } else {
            return false;
        }
    }

    private Consulta criarConsulta(int idPaciente, int idEspecialidade, String  data){
        Paciente paciente = obterPaciente(idPaciente);
        Especialidade especialidade = obterEspecialidade(idEspecialidade);
        return new Consulta(paciente, especialidade, data);
    }
    private Paciente obterPaciente(int id){
        return pacienteRepository.searchPacienteById(id);
    }

    private Especialidade obterEspecialidade(int id){
        return especialidadesRepository.searchEspecialidadeById(id);
    }
}
