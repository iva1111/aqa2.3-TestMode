package ru.netology.testmode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private String login;
    private String password;
    private String status;
}
