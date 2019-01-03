package ru.gromov.auth.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Document(collection = "accounts")
@TypeAlias("account")
public class Account {
    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    private String roles;

    private String verificationCode;

    private Long verificationTime;
}
