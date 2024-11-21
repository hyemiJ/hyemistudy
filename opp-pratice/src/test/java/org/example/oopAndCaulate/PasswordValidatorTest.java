package org.example.oopAndCaulate;

// 비밀번호는 최소 8자리 이상 12자리 이하
//- 이외의 경우 IllegalArgumentException 예외 발생
//- 경계조건에 대해 테스트 코드 작성

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class PasswordValidatorTest {

    //테스트 의도를 나타내면 좋음
    //해당 문자열이 exception을 통과하지 않았는지 확인.
    @DisplayName("비밀번호는 최소 8자리 이상 12자리 이하면 예외가 발생하지 않는다.")
    @Test
    void validatePassword() {
        assertThatCode(()-> PasswordValidator.validate("serverwizad"))
                .doesNotThrowAnyException();
        //assertThatCode는 assertj에 있는 메서드로 , 테스트 코드의 가독성이 높아질 수있다.
        //doesNotThrowAnyException : 코드 실행 중 어떠한 예외도 발생하지 않아야 테스트가 성공.
    }

    //예외를 발생시켰을때 일치하는지 확인
    @DisplayName("비밀번호가 8자 미만 12자리 초과하는 경우 IllegalArgumentException 예외가 발생한다.")
    @Test
    void validatePassword2() {
        assertThatCode(()->PasswordValidator.validate("a"))
                .isInstanceOf(IllegalArgumentException.class)
                //isInstanceOf(IllegalArgumentException.class): 예외의 타입이 IllegalArgumentException인지 확인
                .hasMessage("비밀번호는 8자 이상 12자 이하여야 한다.");
                //hasMessage(): 예외 메시지가 특정 메시지와 일치하는지 검증.
    }

    //경계조건에 대해서 테스트 코드를 반드시 작성하기를 권장함.
    //항상 문제는 경계에서 발생할 가능성이 높음.
    @DisplayName("비밀번호가 8자 미만 12자리 초과하는 경우 IllegalArgumentException 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1234567","abcdefghijklm"})//7자리와 13자리에 해당하는 값을 넣어줌.
    void validatePassword3(String password) {
        assertThatCode(()->PasswordValidator.validate(password))
                .isInstanceOf(IllegalArgumentException.class)
                //isInstanceOf(IllegalArgumentException.class): 예외의 타입이 IllegalArgumentException인지 확인
                .hasMessage("비밀번호는 8자 이상 12자 이하여야 한다.");
        //hasMessage(): 예외 메시지가 특정 메시지와 일치하는지 검증.
    }
    //테스트를 통해 더 낮은 결합도를 위한 설계

}


