package br.com.walkito.maisVida.services;

import br.com.walkito.maisVida.model.entities.Paciente;
import br.com.walkito.maisVida.model.repository.PacienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;

    public Paciente cadastrarPaciente(Paciente paciente){
        try {
            return pacienteRepository.save(paciente);
        } catch (Exception e){
            throw e;
        }
    }

    public Iterable<Paciente> obterPaciente(int id, String nome, String sobrenome, String cpf){
        Iterable<Paciente> pacientes;
        try{
            if(id != 0){
                pacientes = pacienteRepository.searchPacientesById(id);
            } else if(!cpf.isEmpty() && !cpf.equals(null)){
                pacientes = pacienteRepository.findByCpf(cpf);
            } else if((!nome.isEmpty() && !nome.equals(null)) && (sobrenome.isEmpty() || sobrenome.equals(null))){
                pacientes = pacienteRepository.findByNome(nome);
            } else if((nome.isEmpty() || nome.equals(null)) && (!sobrenome.isEmpty() && !sobrenome.equals(null))){
                pacientes = pacienteRepository.findBySobrenome(sobrenome);
            } else if((!nome.isEmpty() && !nome.equals(null)) && (!sobrenome.isEmpty() && !sobrenome.equals(null))){
                pacientes = pacienteRepository.findByNomeAndSobrenome(nome, sobrenome);
            } else {
                pacientes = obterPacientes(0);
            }
            return pacientes;
        } catch (Exception e){
            throw e;
        }
    }

    public Iterable<Paciente> obterPacientes(int pagina){
        try {
            Pageable page = PageRequest.of(pagina, 10);
            return pacienteRepository.findAll(page);
        } catch (Exception e){
            throw e;
        }
    }

    public Paciente editarPaciente(int id, Paciente paciente){
        try{
            Paciente pacienteAtual = pacienteRepository.searchPacienteById(id);
            BeanUtils.copyProperties(paciente, pacienteAtual, getNullPropertyNames(paciente));
            return pacienteRepository.save(pacienteAtual);
        } catch (Exception e){
            throw e;
        }
    }

    public boolean excluirPaciente(int id){
        try{
            Optional<Paciente> paciente = pacienteRepository.findById(id);
            if(!paciente.get().equals(null)){
                pacienteRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        }catch (NoSuchElementException e){
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
}
