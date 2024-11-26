package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryStringsTest {
    @Test
    void create() {
        QueryStrings qs = new QueryStrings("a=11&operator=*&b=33"); // 리스트의 쿼리 스트링으로 만들기.

        assertThat(qs).isNotNull();

    }
}
