/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User1
 */
public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void kassassaOikeaAlkusaldo() {
        assertEquals(paate.kassassaRahaa(), 100000);
    }
    
    @Test
    public void lounaitaMyytyAlussaNolla() {
        int lounaat = paate.edullisiaLounaitaMyyty() + paate.maukkaitaLounaitaMyyty();
        assertEquals(lounaat, 0);
    }
    
    @Test
    public void edullisenLounaanKateisostoKasvattaaSaldoa() {
        paate.syoEdullisesti(240);
        assertEquals(paate.kassassaRahaa(), 100240);
    }
    
    @Test
    public void edullisenLounaanVaihtorahaOikein() {
        assertEquals(paate.syoEdullisesti(250), 10);
    }
    
    @Test
    public void maukkaanLounaanKateisostoKasvattaaSaldoa() {
        paate.syoMaukkaasti(400);
        assertEquals(paate.kassassaRahaa(), 100400);
    }
    
    @Test
    public void maukkaanLounaanVaihtorahaOikein() {
        assertEquals(paate.syoMaukkaasti(450), 50);
    }
    
    @Test
    public void edullinenLounasKasvattaaMyytyjaLounaita() {
        paate.syoEdullisesti(240);
        assertEquals(paate.edullisiaLounaitaMyyty(), 1);
    }
    
    @Test
    public void maukasLounasKasvattaaMyytyjaLounaita() {
        paate.syoMaukkaasti(400);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 1);
    }
    
    @Test
    public void kassanSaldoEiMuutuJosRahaaEiOleEdulliseenLounaaseen() {
        paate.syoEdullisesti(100);
        assertEquals(paate.kassassaRahaa(), 100000);
    }
    
    @Test
    public void kassanSaldoEiMuutuJosRahaaEiOleMaukkaaseenLounaaseen() {
        paate.syoMaukkaasti(100);
        assertEquals(paate.kassassaRahaa(), 100000);
    }
    
    @Test
    public void edullisenKaikkiRahatPalautetaan() {
        assertEquals(paate.syoEdullisesti(100), 100);
    }
    
    @Test
    public void maukkaanKaikkiRahatPalautetaan() {
        assertEquals(paate.syoMaukkaasti(100), 100);
    }
    
    @Test
    public void myytyjenEdullistenMaaraEiMuutu() {
        paate.syoEdullisesti(100);
        assertEquals(paate.edullisiaLounaitaMyyty(), 0);
    }
    
    @Test
    public void myytyjenMaukkaidenMaaraEiMuutu() {
        paate.syoMaukkaasti(100);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void rahatRiittavatEdulliseenLounaaseen() {
        assertTrue(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void rahatRiittavatMaukkaaseenLounaaseen() {
        assertTrue(paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void edullinenLounasMaksutetaanKortilta() {
        paate.syoEdullisesti(kortti);
        assertEquals(kortti.saldo(), 760);
    }
    
    @Test
    public void maukasLounasMaksutetaanKortilta() {
        paate.syoMaukkaasti(kortti);
        assertEquals(kortti.saldo(), 600);
    }
    
    @Test
    public void edullinenLounasKorttimaksullaKasvattaaMyytyjaLounaita() {
        paate.syoEdullisesti(kortti);
        assertEquals(paate.edullisiaLounaitaMyyty(), 1);
    }
    
    @Test
    public void maukasLounasKorttimaksullaKasvattaaMyytyjaLounaita() {
        paate.syoMaukkaasti(kortti);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 1);
    }
    
    @Test
    public void rahatEivatRiitaEdulliseenLounaaseen() {
        kortti.otaRahaa(800);
        assertFalse(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void rahatEivatRiitaMaukkaaseenLounaaseen() {
        kortti.otaRahaa(700);
        assertFalse(paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kortinSaldoEiMuutuJosRahaEiRiitaEdulliseen() {
        kortti.otaRahaa(800);
        paate.syoEdullisesti(kortti);
        assertEquals(kortti.saldo(), 200);
    }
    
    @Test
    public void kortinSaldoEiMuutuJosRahaEiRiitaMaukkaaseen() {
        kortti.otaRahaa(800);
        paate.syoMaukkaasti(kortti);
        assertEquals(kortti.saldo(), 200);
    }
    
    @Test
    public void kortillaMyytyjenEdullistenMaaraEiMuutu() {
        kortti.otaRahaa(800);
        paate.syoEdullisesti(kortti);
        assertEquals(paate.edullisiaLounaitaMyyty(), 0);
    }
    
    @Test
    public void kortillaMyytyjenMaukkaidenMaaraEiMuutu() {
        kortti.otaRahaa(800);
        paate.syoMaukkaasti(kortti);
        assertEquals(paate.maukkaitaLounaitaMyyty(), 0);
    }
    
    @Test
    public void edullinenKorttiostoEiMuutaKassanSaldoa() {
        paate.syoEdullisesti(kortti);
        assertEquals(paate.kassassaRahaa(), 100000);
    }
    
    @Test
    public void maukasKorttiostoEiMuutaKassanSaldoa() {
        paate.syoMaukkaasti(kortti);
        assertEquals(paate.kassassaRahaa(), 100000);
    }
    
    @Test
    public void kortinLatausMuuttaaKortinSaldoa() {
        paate.lataaRahaaKortille(kortti, 20);
        assertEquals(kortti.saldo(), 1020);
    }
    
    @Test
    public void kortinLatausKasvattaaKassanSaldoa() {
        paate.lataaRahaaKortille(kortti, 500);
        assertEquals(paate.kassassaRahaa(), 100500);
    } 
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaArvoa() {
        paate.lataaRahaaKortille(kortti, -5);
        assertEquals(kortti.saldo(), 1000);
    }
}
