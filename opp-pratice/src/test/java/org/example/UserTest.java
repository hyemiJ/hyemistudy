package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @DisplayName("패스워드를 초기화한다.")
    @Test
    void passwordTest1() {
        //given
        User user = new User();
        //when
        user.initPassword1();
        //then
        assertThat(user.getPassword()).isNotNull();
    }//테스트 코드에서 컨트롤 할수 없는 경우.


    @DisplayName("패스워드를 초기화한다.")
    @Test
    void passwordTest() {
        //given
        User user = new User();
        //when
        //@FunctionalInterface를 사용함으로 인해 람다식을 활용
        user.initPassword(()->"abcdefgh");
        //user.initPassword(new CorrenctFixedPasswordGenerator());
        //then
        assertThat(user.getPassword()).isNotNull();
    }

    @DisplayName("패스워드가 요구사항에 부합하지 않아 초기화되지 않는다.")
    @Test
    void passwordTest2() {
        //given
        User user = new User();
        //when
        //@FunctionalInterface를 사용함으로 인해 람다식을 활용
        user.initPassword(()->"ab");
        //user.initPassword(new WrongFixedPasswordGenerator());
        //then
        assertThat(user.getPassword()).isNull();
    }

    // 이를 통해 알 수 있는점
    // Random을 컨트롤 할 수 없는 부분이 있기 때문에 , 테스트를 돌릴때마다 실패와 성공을 알수없는 상태이다.
    //상위에 인터페이스를 선언하여 실제로 , 운영에서는 랜덤한 패스워드가 들어오겠지만 ,
    //테스트를 위해 항상 통과할 수 있는 경우를만들어 시나리오를 제작할 수 있었다.
    //테스트 코드에서는 컨트롤 하기위해 , 쉬운 코드를 제작하다보니 낮은 결합도로 만들게 된다.
}