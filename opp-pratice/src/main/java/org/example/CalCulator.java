package org.example;

import org.example.calculate.*;

import java.util.ArrayList;
import java.util.List;

public class CalCulator {

    private static final List<NewAritmeticOperator> aritmeticOperators
            = List.of(new AdditionOperator(), new SubTractionOperator(), new MuliplicationOprator() , new DivisionOprator());

    //인터 페이스 활용
    public static int calculateInterface(int a, String operator , int b ) {
        return aritmeticOperators.stream()
                .filter(v -> v.supports(operator)) // 해당 연산자를 처리할 수 있는 클래스 선택
                .map(v -> v.caculate(a, b)) // 선택된 클래스에서 연산 수행
                .findFirst() // 첫 번째 결과 반환
                .orElseThrow(()->new IllegalArgumentException("Operator :"+operator+" : 올바른 사칙연산이 아닙니다."));
    }
    //인터페이스와 유효성 테스트를 위한 클래스 활용
    public static int calculateInterface2(PositiveNumber a, String operator , PositiveNumber b ) {
        return aritmeticOperators.stream()
                .filter(v -> v.supports(operator)) // 해당 연산자를 처리할 수 있는 클래스 선택
                .map(v -> v.caculatePosive(a, b)) // 선택된 클래스에서 연산 수행
                .findFirst() // 첫 번째 결과 반환
                .orElseThrow(()->new IllegalArgumentException("Operator :"+operator+" : 올바른 사칙연산이 아닙니다."));
    }

    //1차적 계산 메서드
    public static int calculate(int a, String operator , int b ) {
        if(operator.equals("+")){
            return a+b;
        }else if(operator.equals("-")){
            return a-b;
        }else if(operator.equals("*")){
            return a*b;
        }else if(operator.equals("/")){
            return a/b;
        }
    return 0;
    }

    //enum 파일을 이용하여 계산을 맞김
    public static int calculateEnum(int a, String operator , int b ) {
        return ArithmeticOperator.calculate(a,operator,b);
    }
}
