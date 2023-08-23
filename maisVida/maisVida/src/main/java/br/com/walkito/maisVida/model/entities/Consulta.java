package br.com.walkito.maisVida.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;


@Entity
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @JsonManagedReference
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    @JsonManagedReference
    private  Especialidade especialidade;

    private String dataConsulta;

    public Consulta() {
    }

    public Consulta(Paciente paciente, Especialidade especialidade, String dataConsulta) {
        this.paciente = paciente;
        this.especialidade = especialidade;
        this.dataConsulta = dataConsulta;
    }

    public String  getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String  dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public int getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }
}
