package br.com.walkito.maisVida.model.repository;

import br.com.walkito.maisVida.model.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PacienteRepository extends JpaRepository<Paciente, Integer>{

    public Iterable<Paciente> findByCpf(String cpf);

    @Query("SELECT p FROM Paciente p WHERE p.id = :pId")
    public Iterable<Paciente> searchPacientesById(@Param("pId") int id);

    @Query("SELECT p FROM Paciente p WHERE p.id = :pId")
    public Paciente searchPacienteById(@Param("pId") int id);
    public Iterable<Paciente> findByNome(String nome);

    public Iterable<Paciente> findBySobrenome(String sobrenome);

    public Iterable<Paciente> findByNomeAndSobrenome(String nome, String sobrenome);
}
