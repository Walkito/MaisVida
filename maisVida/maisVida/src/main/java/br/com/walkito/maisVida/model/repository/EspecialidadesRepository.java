package br.com.walkito.maisVida.model.repository;

import br.com.walkito.maisVida.model.entities.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EspecialidadesRepository extends JpaRepository<Especialidade, Integer> {

    @Query("SELECT e FROM Especialidade e WHERE e.id = :pId")
    public Iterable<Especialidade> searchEspecialidadesById(@Param("pId") int id);
    @Query("SELECT e FROM Especialidade e WHERE e.id = :pId")
    public Especialidade searchEspecialidadeById(@Param("pId") int id);
    public Iterable<Especialidade> findByNome(String nome);

    public Iterable<Especialidade> findByTipo(String tipo);
}
