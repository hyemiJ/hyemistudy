package org.example.oopAndCaulate;

public class WrongFixedPasswordGenerator implements PasswordGenerator {

    @Override
    public String generatePassword() {
        return "ab";//8글자 생성하는 메서드.
    }
}