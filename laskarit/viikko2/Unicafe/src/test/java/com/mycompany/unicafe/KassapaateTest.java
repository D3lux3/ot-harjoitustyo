package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Kassapaate kassa;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }

    @Test
    public void aluksiOikeaMaaraRahaaKassassa() {
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void kateisOstoToimiiEdullisesti() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(240, kassa.syoEdullisesti(480));
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void kateisOstoToimiiMaukkaasti() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(400, kassa.syoMaukkaasti(800));
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100400, kassa.kassassaRahaa());
    }


    @Test
    public void josOotKoyhaNiinRahatPalautuu() {
        assertEquals(2, kassa.syoMaukkaasti(2));
        assertEquals(2, kassa.syoEdullisesti(2));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void korttiOstoToimiiMaukkaasti() {
        Maksukortti kortti = new Maksukortti(500);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void korttiOstoToimiiEdullisesti() {
        Maksukortti kortti = new Maksukortti(500);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void josKoyhaKorttiNiinRahatEiLahde() {
        Maksukortti kortti = new Maksukortti(5);
        assertFalse(kassa.syoEdullisesti(kortti));
        assertEquals(5, kortti.saldo());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());

        assertFalse(kassa.syoMaukkaasti(kortti));
        assertEquals(5, kortti.saldo());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kortilleRahanLatausOnnistuu() {
        Maksukortti kortti = new Maksukortti(0);
        kassa.lataaRahaaKortille(kortti, 50);
        assertEquals(50, kortti.saldo());
        assertEquals(100050, kassa.kassassaRahaa());
        kassa.lataaRahaaKortille(kortti, -50);
        assertEquals(50, kortti.saldo());
    }
}