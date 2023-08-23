package br.com.walkito.maisVida.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50, nullable = false)
    @NotBlank
    @Size(max = 50)
    private String nome;

    @Column(length = 100, nullable = false)
    @NotBlank
    @Size(max = 100)
    private String sobrenome;

    @Column(length = 11, nullable = false, unique = true)
    @NotBlank
    @Size(max = 11)
    private String cpf;

    @Column(length = 9, nullable = false)
    @NotBlank
    @Size(max = 9)
    private String rg;

    @Column(length = 50)
    @Size(max = 50)
    private String endereco;

    @Column(length = 35)
    @Size(max = 35)
    private String cidade;

    @Column(length = 25)
    @Size(max = 25)
    private String estado;

    @Column(length = 12)
    @Size(max = 12)
    private String telefone;

    public Paciente(){

    }

    public Paciente(String nome, String sobrenome, String cpf, String endereco, String cidade, String estado, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
    }

    @OneToMany(mappedBy = "paciente")
    @JsonBackReference
    private Set<Consulta> consultas = new HashSet<>();

    public Set<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(Set<Consulta> consultas) {
        this.consultas = consultas;
    }

    public int getId() {
        return id;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNomeCompleto(){
        return nome + " " + sobrenome;
    }
}
