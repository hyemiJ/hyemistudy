package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestLineTest {
    @Test
    void create() {
        RequestLine requestLine = new RequestLine("GET /calculate?a=11&operator=*&b=33 HTTP/1.1");

        assertThat(requestLine).isNotNull();

        assertThat(requestLine).isEqualTo(new RequestLine("GET","/calculate","a=11&operator=*&b=33"));

    }
}
