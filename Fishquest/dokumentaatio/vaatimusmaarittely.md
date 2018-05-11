# Vaatimusmäärittely

## Sovelluksen tarkoitus

Kyseessä on Asteroids-tyyppinen peli, jossa pelaaja liikkuu ja nappaa kaloja osuessaan niihin. 

## Käyttöliittymäluonnos

Sovellus muodostuu kolmesta eri näkymästä:  

<img src="https://github.com/ansolotli/otm-harjoitustyo/blob/master/Fishquest/dokumentaatio/kuvat/kayttisluonnos.jpg" width=600>  

Sovellus avautuu aloitusnäkymään, jossa pelaaja valitsee itselleen nimen ja siirtyy pelinäkymään. Pelin päätyttyä sovellus avaa huipputulosnäkymän.

## Perusversion tarjoamat toiminnallisuudet

### Aloitusnäkymä

  * Pelaaja kirjoittaa nimikirjaimensa tai valitsemansa nimimerkin tekstikenttään  
  * Pelaaja painaa Start-nappia, jolloin pelinäkymä käynnistyy

### Pelinäkymä

  * Pelaaja on vene, joka alussa on peliruudun keskellä
  * Pelaaja saa kalan kiinni osumalla veneellä kalaan
  * Pelikentällä on kareja, joita pelaajan tulee väistää tai peli päättyy
  * Pelissä on kalageneraattori, joka luo erivärisiä kaloja tietyin aikavälein
  * Jokaisesta kiinni otetusta kalasta saa pisteen
  * Peli pitää kirjaa huipputuloksista. Pelin päättyessä huipputulosnäkymä käynnistyy
  
### Huipputulosnäkymä

  * Näkymä näyttää listan, jossa näkyvät peliä pelanneiden pelaajien nimet ja pistemäärät
  * Sama nimi voi esiintyä listalla useaan kertaan
  * Nolla pistettä saaneet pelaajat eivät näy listalla
  
## Jatkokehitysideoita

  * Peliajan rajaaminen tai pelin vaikeuttaminen esim. liikkuvilla kappaleilla, joihin ei saa osua
  * Mahdollisuus aloittaa uusi peli huipputulosnäkymästä käsin
  * Mahdollisuus tyhjentää huipputuloslista
