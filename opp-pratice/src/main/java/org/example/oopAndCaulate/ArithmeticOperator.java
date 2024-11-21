package org.example.oopAndCaulate;

import java.util.Arrays;

public enum ArithmeticOperator {
    ADDITION("+") {
        @Override
        public int arithmeticCalculate(int a, int b) {
            return a+b;
        }
    },SUBTRACTION("-") {
        @Override
        public int arithmeticCalculate(int a, int b) {
            return a-b;
        }
    },MULTIPLICATION("*") {
        @Override
        public int arithmeticCalculate(int a, int b) {
            return a*b;
        }
    },DIVISION("/") {
        @Override
        public int arithmeticCalculate(int a, int b) {
            return a/b;
        }
    };

    private final String operator;

    // 생성자: Enum 값을 초기화할 때 연산자를 설정
    ArithmeticOperator(String operator) {
        this.operator = operator;
    }

    // 추상 메서드: 각 연산자가 구현해야 할 메서드
    public abstract int arithmeticCalculate(final int a, final int b);

    // 정적 메서드: 문자열로 연산자를 받아 알맞은 Enum 값을 찾아 계산 실행
    public static int calculate(int a, String operator, int b) {
        ArithmeticOperator arithmeticOperator = Arrays.stream(values()) // Enum 값 순회
                .filter(v -> v.operator.equals(operator)) // 전달된 연산자와 일치하는 값 찾기
                .findFirst() // 첫 번째 일치하는 값 반환
                .orElseThrow(() -> new IllegalArgumentException(operator + " : 올바른 사칙 연산이 아닙니다.")); // 없으면 예외 발생
        return arithmeticOperator.arithmeticCalculate(a, b); // 연산 수행
    }


}
