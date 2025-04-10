package com.safetynet.alerts.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringJUnitConfig(JsonParser.class)

class JsonParserTest {
    @Autowired
    private JsonParser jsonParser;

    @Test
    void testConstractor() {
        assertThat(jsonParser).isNotNull();
    }
}