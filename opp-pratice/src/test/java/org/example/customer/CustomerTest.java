package org.example.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

/*
주제 : 음식점에서 음식을 주문하는 과정
요구 사항
- 도메인을 구성하는 객체에는 어떤것들이 있는지 고민
    - 손님 , 메뉴판(0) , 메뉴(0) , 요리사(0) , 요리(0)
- 객체 관계를 고민
    - 손님 - 메뉴판 (손님이 메뉴판을 보고 요리사에게 요리를 요청)
    - 손님 ---- 요리사
    - 손님 <- 요리
- 동적인 객체를 정적인 타입으로 추상화하여 도메인 모델링하기
    - 손님은 손님타입으로 추상화
    - 메뉴는 다양한 요리 타입으로 선정
    - 메뉴은 메뉴 타입으로 추상화
- 협력을 설계
- 객체들을 포괄하는 타입에 적절한 책임을 할당
- 구현하기
 */
public class CustomerTest {
    @DisplayName("메뉴 이름에 해당하는 요리를 주문한다.")
    @Test
    void orderTest() {
        Customer customer = new Customer();
        Menu menu = new Menu(List.of(new MenuItem("돈까스",5000), new MenuItem("냉면",7000)));
        Cooking cooking = new Cooking();

        //고객이 주문을 한다. 메뉴 이름과 , 메뉴판 , 요리사를 전달.
        // 메뉴이름만 전달해도 된다. 하지만 이정보가 있어야 cook까지 전달이 가능.
        assertThatCode(()-> customer.order("돈까스",menu,cooking))
                .doesNotThrowAnyException();
    }


}
