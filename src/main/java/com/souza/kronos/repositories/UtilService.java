package com.souza.kronos.repositories;

import org.springframework.stereotype.Service;

@Service
public class UtilService {


    public boolean validarDocumento(String documento)
    {
        switch (documento.length()) {
            case 11:
                return this.validarCPF(documento);
            case 14:
                return this.validarCNPJ(documento);
            default:
                return false;
        }
    }


    private boolean validarCNPJ(String cnpj) {
        if (cnpj == null || !cnpj.matches("\\d{14}")) {
            return false;
        }

        int[] digitos = new int[14];
        for (int i = 0; i < 14; i++) {
            digitos[i] = Character.getNumericValue(cnpj.charAt(i));
        }

        // Calculando o primeiro dígito verificador
        int soma = 0;
        int peso = 2;
        for (int i = 11; i >= 0; i--) {
            soma += digitos[i] * peso;
            peso++;
            if (peso == 10) {
                peso = 2;
            }
        }
        int resto = soma % 11;
        int digito1 = resto < 2 ? 0 : 11 - resto;

        if (digito1 != digitos[12]) {
            return false;
        }

        // Calculando o segundo dígito verificador
        soma = 0;
        peso = 2;
        for (int i = 12; i >= 0; i--) {
            soma += digitos[i] * peso;
            peso++;
            if (peso == 10) {
                peso = 2;
            }
        }
        resto = soma % 11;
        int digito2 = resto < 2 ? 0 : 11 - resto;

        return digito2 == digitos[13];
    }

    private boolean validarCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d{11}")) {
            return false;
        }

        int[] digitos = new int[11];
        for (int i = 0; i < 11; i++) {
            digitos[i] = Character.getNumericValue(cpf.charAt(i));
        }

        int soma1 = 0;
        for (int i = 0; i < 9; i++) {
            soma1 += digitos[i] * (10 - i);
        }
        int digito1 = 11 - (soma1 % 11);
        if (digito1 >= 10) {
            digito1 = 0;
        }

        int soma2 = 0;
        for (int i = 0; i < 10; i++) {
            soma2 += digitos[i] * (11 - i);
        }
        int digito2 = 11 - (soma2 % 11);
        if (digito2 >= 10) {
            digito2 = 0;
        }

        return digito1 == digitos[9] && digito2 == digitos[10];
    }
}
