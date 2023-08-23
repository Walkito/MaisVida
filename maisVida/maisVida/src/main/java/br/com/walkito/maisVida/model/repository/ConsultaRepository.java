package br.com.walkito.maisVida.model.repository;

import br.com.walkito.maisVida.model.entities.Consulta;
import br.com.walkito.maisVida.model.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConsultaRepository extends JpaRepository<Consulta, Integer>, JpaSpecificationExecutor<Consulta> {
    @Query("SELECT c FROM Consulta c WHERE c.id = :pId")
    public Consulta searchConsultaById(@Param("pId") int id);


}
