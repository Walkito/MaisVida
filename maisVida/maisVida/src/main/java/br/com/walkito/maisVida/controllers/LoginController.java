package br.com.walkito.maisVida.controllers;

import br.com.walkito.maisVida.model.entities.Login;
import br.com.walkito.maisVida.model.repository.LoginRepository;
import br.com.walkito.maisVida.services.LoginService;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping
    public boolean validarLogin(@RequestParam(name = "username") String username,
                              @RequestParam(name = "password") String password){
        return loginService.validarLogin(username, password);
    }

    @PostMapping(path = "/criar")
    public boolean criarLogin(@Valid Login login){
        return loginService.criarLogin(login);
    }
}
