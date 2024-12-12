package Student_Bus.Student_Busm.controller;
import Student_Bus.Student_Busm.entity.Account;

import Student_Bus.Student_Busm.service.Accountservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin("*")
public class Accountcontroller {

    private final Accountservice accountService;

    @PostMapping("/register")
    public Account createAccount(@RequestBody Account account) {
        return accountService.postaccount(account);
    }

    @PostMapping("/login")
    public Account login(@RequestBody Account loginData) {
        Account account = accountService.findByStudentIdAndPassword(loginData.getStudentId(), loginData.getPassword());
        if (account != null) {
            return account;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }



}
