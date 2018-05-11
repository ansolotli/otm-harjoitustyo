# Testausdokumentti

Ohjelmaa on testattu sekä automatisoiduin yksikkö- ja integraatiotestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.  

## Yksikkö- ja integraatiotestaus

### Sovelluslogiikka

Automatisoidut testit testaavat pääasiassa sovelluslogiikkaa. Pakkauksen Fishquest.logics luokkia testaavat integraatiotestit luokassa GameLogicsTest. Ne pyrkivät luomaan ja simuloimaan käyttöliittymän pelinäkymässä tapahtuvia toiminnallisuuksia.

### DAO

ScoreDao-luokan ja Database-luokan kautta toimivan SQL-tietokannan toiminnallisuutta testataan luomalla testitietokanta.

### Testikattavuus

Käyttöliittymää ja sen rakentavia luokkia lukuunottamatta sovelluksen testauksen rivikattavuus on 79% ja haarautumakattavuus 57%.  

<img src="https://github.com/ansolotli/otm-harjoitustyo/blob/master/Fishquest/dokumentaatio/kuvat/jacoco.PNG" width=900>  

Testaamatta jäi liikkumiseen liittyviä tekijöitä, kuten pelihahmojen vastaaminen näppäimistön painalluksiin.  

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.  

Sovellusta on testattu tilanteissa, joissa pelaajan saamat pisteet tallentava tietokantataulu on ollut olemassa ja joissa sitä ei ole ollut, jolloin ohjelma on luonut sen itse.

### Toiminnallisuudet
Kaikki vaatimusmäärittelydokumentin ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi.
