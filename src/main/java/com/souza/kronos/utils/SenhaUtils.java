package com.souza.kronos.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtils {

    public static String encode(String senha)
    {
        return new BCryptPasswordEncoder().encode(senha);
    }
}
