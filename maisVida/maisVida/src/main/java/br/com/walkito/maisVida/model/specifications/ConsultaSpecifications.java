package br.com.walkito.maisVida.model.specifications;

import br.com.walkito.maisVida.model.entities.Consulta;
import br.com.walkito.maisVida.model.entities.Especialidade;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;
import java.util.Date;

public class ConsultaSpecifications {

    public static Specification<Consulta> comId(Integer id){
        return (root, query, criteriaBuilder) -> {
            if (id <= 0){
                return null;
            }
            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    public static Specification<Consulta> comData(String data){
        if (data.isEmpty() || data.equals(null)){
            return null;
        } else {
            return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("dataConsulta"), data);
        }
    }

    public static Specification<Consulta> comEspecialidade(String nomeEspecialidade){
        if (nomeEspecialidade.isEmpty() || nomeEspecialidade.equals(null)){
            return null;
        } else {
            return (root, query, criteriaBuilder) -> {
                root.fetch("especialidade");
                return criteriaBuilder.equal(root.get("especialidade").get("nome"), nomeEspecialidade);
            };
        }

    }

    public static Specification<Consulta> comTipo(String tipo){
        if (tipo.isEmpty() || tipo.equals(null)){
            return null;
        } else {
            return (root, query, criteriaBuilder) -> {
                root.fetch("especialidade");
                return criteriaBuilder.equal(root.get("especialidade").get("tipo"), tipo);
            };
        }
    }

    public static Specification<Consulta> comNomePaciente(String nome){
        if (nome.isEmpty() || nome.equals(null)){
            return null;
        } else {
            return (root, query, criteriaBuilder) -> {
                root.fetch("paciente");
                return criteriaBuilder.equal(root.get("paciente").get("nome"), nome);
            };
        }
    }

    public static Specification<Consulta> comSobrenomePaciente(String sobrenome){
        if (sobrenome.isEmpty() || sobrenome.equals(null)){
            return null;
        } else {
            return (root, query, criteriaBuilder) -> {
                root.fetch("paciente");
                return criteriaBuilder.equal(root.get("paciente").get("sobrenome"), sobrenome);
            };
        }
    }

    public static Specification<Consulta> consultaVazia(){
        return null;
    }
}
