package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

///a=11&operator=*&b=33
public class QueryStringTest {
    @Test
    void create() {
        QueryString  queryString = new QueryString("a","11");
        assertThat(queryString).isNotNull();
    }
}
