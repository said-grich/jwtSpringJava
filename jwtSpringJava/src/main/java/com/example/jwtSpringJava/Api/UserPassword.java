package com.example.jwtSpringJava.Api;

import lombok.Data;
@Data
public class UserPassword {
        String username;
        String passwordOld;
        String passwordNew;
}
