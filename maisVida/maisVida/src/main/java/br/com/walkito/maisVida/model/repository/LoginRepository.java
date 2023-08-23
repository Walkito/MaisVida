package br.com.walkito.maisVida.model.repository;

import br.com.walkito.maisVida.model.entities.Login;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Login, Integer> {
    public Login findByUsername(String username);
}
