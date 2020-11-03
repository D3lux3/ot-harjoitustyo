package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void kortinSaldoAluksiOikea() {
        assertEquals(10, kortti.saldo());
    }

    @Test
    public void saldonKasvatusToimii() {
        kortti.lataaRahaa(50);
        assertEquals("saldo: 0.60", kortti.toString());
    }

    @Test
    public void rahanOttaminenOnnistuu() {
        assertTrue(kortti.otaRahaa(5));
        assertEquals(5, kortti.saldo());
        kortti.otaRahaa(5);
        assertEquals(0, kortti.saldo());
        assertFalse(kortti.otaRahaa(5));
        assertEquals(0, kortti.saldo());
    }

}
