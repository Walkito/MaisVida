package br.com.walkito.maisVida.services;

import br.com.walkito.maisVida.model.entities.Login;
import br.com.walkito.maisVida.model.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public boolean validarLogin(String username, String password){
        try{
            Login login = loginRepository.findByUsername(username);
            if(login.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean criarLogin(Login login){
        try{
            loginRepository.save(login);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
