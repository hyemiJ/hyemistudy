package org.example.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class MenuItemTest {

    @DisplayName("메뉴 항목을 생성한다.")
    @Test
    void crateMenuItem() {
        assertThatCode(()->new MenuItem("만두",5000))
                .doesNotThrowAnyException();

    }
}
