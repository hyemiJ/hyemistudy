package org.example;

import org.example.calculate.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * - 간단한 사칙연산을 할 수 있다.
 * - 양수로만 계산을 할 수 있다.
 * - 나눗셈에서 0을 나누는 경우 IllegalArgument 예외를 발생.
 * - MVC(Model-View-Contrller) 기반으로 구현한다.
 */
public class CalculatorTest {

    //1 + 2 ----> CalCulator
    //   3  <----
    @DisplayName("덧셈 연산을 정상적으로 수행한다.")
    @Test
    void additonTest() {
        //연산 작업을 CalCulator에게 위임.
        int result = CalCulator.calculate(1, "+", 2);
        assertThat(result).isEqualTo(3);
    }

    @DisplayName("뺄셈 연산을 정상적으로 수행한다.")
    @Test
    void subtraction() {
        int result = CalCulator.calculate(2, "-", 1);
        assertThat(result).isEqualTo(1);
    }

    @DisplayName("사칙 연산을 정상적으로 수행한다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest(int a,String operator,int b , int result) {
        int calculatedresult = CalCulator.calculate(a, operator, b );
        assertThat(calculatedresult).isEqualTo(result);
    }

    private static Stream<Arguments> formulaAndResult() {
        return Stream.of(
                arguments(1,"+",2,3),
                arguments(1,"-",2,-1),
                arguments(4,"*",2,8),
                arguments(4,"/",2,2)
        );
    }

    @DisplayName("enum 파일을 활용하여 사칙 연산을 정상적으로 수행한다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest2(int a,String operator,int b , int result) {
        int calculatedresult = CalCulator.calculateEnum(a, operator, b );
        assertThat(calculatedresult).isEqualTo(result);
    }

    @DisplayName("interface를 활용하여 사칙 연산을 정상적으로 수행한다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest3(int a,String operator,int b , int result) {
        int calculatedresult = CalCulator.calculateInterface(a, operator, b );
        assertThat(calculatedresult).isEqualTo(result);
    }

    @DisplayName("나눗셈에서 0을 나누는 경우 IllegalArgument 예외를 발생")
    @Test
    void calculateExceptionTest() {
        assertThatCode(()->CalCulator.calculateInterface(10,"/",0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나눗셈에서 0을 나눌 수 없습니다.");

    }

    @DisplayName("interface를 활용+ 유효성을 위한 클래스 활용하여 사칙 연산을 정상적으로 수행한다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest4(int a,String operator,int b , int result) {
        int calculatedresult = CalCulator.calculateInterface2(new PositiveNumber(a), operator, new PositiveNumber(b) );
        assertThat(calculatedresult).isEqualTo(result);
    }
}

