# Arkkitehtuurikuvaus  

## Rakenne  

Ohjelman koodin pakkausrakenne on seuraava:  
  
<img src="https://github.com/ansolotli/otm-harjoitustyo/blob/master/Fishquest/dokumentaatio/kuvat/pakkauskaavio.png" width="200">  

Pakkaus *fishquest.gui* sisältää JavaFX-käyttöliittymän ja sen luomiseen liittyvät luokat. Pakkaus *fishquest.logics* sisältää sovelluslogiikan ja pakkaus *fishquest.dao* tietojen pysyväistallennukseen liittyvän tietokantaluokan ja DAO-rajapinnan.  

## Käyttöliittymä  

Käyttöliittymä sisältää kolme erillistä näkymää  
  
  * aloitusnäkymä  
  * pelinäkymä  
  * huipputulosnäkymä,  
  
joista jokainen on toteutettu omana Scene-oliona. Kukin näkymä luodaan omassa luokassaan pakkauksessa *fishquest.gui*, joita kutsutaan käyttöliittymäluokasta *GameApplication*.  

## Sovelluslogiikka  

Sovelluksen logiikkaa kuvaa seuraava pakkaus-/luokkakaavio:  

<img src="https://github.com/ansolotli/otm-harjoitustyo/blob/master/Fishquest/dokumentaatio/kuvat/luokkakaavio_pakkauksilla.png" width="400">  

## Tietojen pysyväistallennus  

Pakkauksen *fishquest.dao* luokka ScoreDao huolehtii pelitulosten tallettamisesta SQL-tietokantaan. Score-Dao -luokka noudattaa Data Access Object -suunnittelumallia.  

### Tietokanta  

Sovellus tallettaa pelaajien pelitulokset SQL-tietokantaan. Tietokantaan luodaan taulu HighScore, johon tallennetaan kunkin tuloksen yksilöivä id, pelaajan nimi ja pelaajan pelissä saama pistemäärä.
