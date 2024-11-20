package org.example;

public class CorrenctFixedPasswordGenerator implements PasswordGenerator {

    @Override
    public String generatePassword() {
        return "abcdefgh";//8글자 생성하는 메서드.
    }
}
