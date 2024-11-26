package org.example.calculate;

public interface NewAritmeticOperator {
    boolean supports(String operater);
    //1차 테스트
    int caculate(int a , int b);
    //2차 테스트 : 유효성검사를 위한 다형성 적용
    int caculatePosive(PositiveNumber a,PositiveNumber b);
}
