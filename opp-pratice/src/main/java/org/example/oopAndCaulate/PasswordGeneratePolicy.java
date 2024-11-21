package org.example.oopAndCaulate;

//테스트 하기 쉬운 코드를 작성하다 보면 더 낮은 결합도를 가진 설계를 얻을 수 있다.
public interface PasswordGeneratePolicy {
    String generatePassword();
}
