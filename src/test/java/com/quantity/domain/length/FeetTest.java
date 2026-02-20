package com.quantity.domain.length;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeetTest {

    @Test
    void givenSameValue_whenCompared_shouldReturnTrue() {
        Feet first = new Feet(1.0);
        Feet second = new Feet(1.0);

        assertEquals(first, second);
    }

    @Test
    void givenDifferentValues_whenCompared_shouldReturnFalse() {
        Feet first = new Feet(1.0);
        Feet second = new Feet(2.0);

        assertNotEquals(first, second);
    }

    @Test
    void givenNull_whenCompared_shouldReturnFalse() {
        Feet first = new Feet(1.0);

        assertNotEquals(first, null);
    }

    @Test
    void givenSameReference_whenCompared_shouldReturnTrue() {
        Feet first = new Feet(1.0);

        assertEquals(first, first);
    }

    @Test
    void equalityShouldBeSymmetric() {
        Feet first = new Feet(2.0);
        Feet second = new Feet(2.0);

        assertTrue(first.equals(second));
        assertTrue(second.equals(first));
    }

    @Test
    void equalityShouldBeTransitive() {
        Feet first = new Feet(3.0);
        Feet second = new Feet(3.0);
        Feet third = new Feet(3.0);

        assertEquals(first, second);
        assertEquals(second, third);
        assertEquals(first, third);
    }

    @Test
    void equalityShouldBeConsistent() {
        Feet first = new Feet(4.0);
        Feet second = new Feet(4.0);

        assertEquals(first, second);
        assertEquals(first, second);
    }

    @Test
    void givenDifferentType_shouldReturnFalse() {
        Feet first = new Feet(1.0);

        assertFalse(first.equals("1.0"));
    }
}