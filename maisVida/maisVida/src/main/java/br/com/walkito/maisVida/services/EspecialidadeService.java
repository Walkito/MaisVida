package br.com.walkito.maisVida.services;

import br.com.walkito.maisVida.model.entities.Especialidade;
import br.com.walkito.maisVida.model.repository.EspecialidadesRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
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
public class EspecialidadeService {
    @Autowired
    EspecialidadesRepository especialidadesRepository;

    public Especialidade cadastrarEspecialidade(Especialidade especialidade){
        try{
            return especialidadesRepository.save(especialidade);
        } catch (Exception e){
            throw e;
        }
    }

    public Iterable<Especialidade> obterEspecialidades(int pagina){
        try{
            Pageable page = PageRequest.of(pagina, 10);
            return especialidadesRepository.findAll(page);
        }catch (Exception e){
            throw e;
        }
    }

    public Iterable<Especialidade> obterEspecialidade(int id, String nome, String tipo){
        Iterable<Especialidade> especialidades = null;
        try{
            if (id != 0){
                especialidades = especialidadesRepository.searchEspecialidadesById(id);
            } else if (!nome.isEmpty() && !nome.equals(null)){
                especialidades = especialidadesRepository.findByNome(nome);
            } else if (!tipo.isEmpty() && !tipo.equals(null)){
                especialidades = especialidadesRepository.findByTipo(tipo);
            } else {
                especialidades = obterEspecialidades(0);
            }
            return especialidades;
        }catch (Exception e){
            throw e;
        }
    }

    public boolean excluirEspecialidade(int id){
        try{
            Optional<Especialidade> especialidade = especialidadesRepository.findById(id);
            if(!especialidade.get().equals(null)){
                especialidadesRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (NoSuchElementException e){
            e.printStackTrace();
            return false;
        }
    }

    public Especialidade editarEspecialidade(int id, Especialidade especialidade){
        try {
            Especialidade especialidadeExistente = especialidadesRepository.searchEspecialidadeById(id);
            BeanUtils.copyProperties(especialidade, especialidadeExistente, getNullPropertyNames(especialidade));
            return especialidadesRepository.save(especialidadeExistente);
        } catch (Exception e){
            throw e;
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
