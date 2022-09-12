package com.cdg;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Unit test for simple App.
 */
public class AppTest {

    // given
    // when
    // then
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @BeforeEach
    void readFile() {
        reader = new LogfileReader();
    }

    @Test
    public void fileReadOneLine() {
        // given
        // when
        // then
    }
}
