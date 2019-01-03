package ru.gromov.auth.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.gromov.auth.domain.LoginInfo;
import ru.gromov.auth.dto.AccountDto;
import ru.gromov.auth.service.AccountService;
import ru.gromov.auth.service.LibraryUserDetailsService;

@RestController
@RequestMapping("/account")
public class AdminController {

    private AccountService accountService;

    @Autowired
    public AdminController(AccountService accountService,
            LibraryUserDetailsService userDetailService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    public List<AccountDto> findAll() {
        return accountService.findAll().stream().map(AccountDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/current")
    public AccountDto accountInfo(Authentication authentication) {
        return accountService
                .findByNameOrEmail(authentication.getPrincipal().toString())
                .map(account -> new AccountDto("", "", "",
                        AccountDto.toDto(account).getRoles()))
                .orElse(new AccountDto("", "", "", new ArrayList<>()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> findOne(@PathVariable("id") String id) {
        return accountService.findById(id).map(AccountDto::toDto)
                .map(acc -> ResponseEntity.ok(acc))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody LoginInfo loginInfo) {
        
        if (accountService
                .findByNameOrEmail(loginInfo.getUsername()).isPresent()) {
            return new ResponseEntity<>(
                    new ApiResponse(false, "User already exists"),
                    HttpStatus.BAD_REQUEST);
        }

        accountService.createUser(loginInfo.getUsername(),
                loginInfo.getPassword());

        return ResponseEntity.ok(new ApiResponse(true, "User was created"));
    }
}
