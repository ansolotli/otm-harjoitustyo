# OTM-harjoitustyö

Asteroids-tyyppinen kalapeli FishQuest, joka on toteutettu harjoitustyönä Helsingin yliopiston tietojenkäsittelytieteen kurssille *Ohjelmistotekniikan menetelmät*.

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/ansolotli/otm-harjoitustyo/blob/master/Fishquest/dokumentaatio/vaatimusmaarittely.md)  
[Tuntikirjanpito](https://github.com/ansolotli/otm-harjoitustyo/blob/master/Fishquest/dokumentaatio/tuntikirjanpito.md)  
[Arkkitehtuurikuvaus](https://github.com/ansolotli/otm-harjoitustyo/blob/master/Fishquest/dokumentaatio/arkkitehtuuri.md)

## Releaset

[Viikko 5](https://github.com/ansolotli/otm-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla:

```
mvn test
```
Testikattavuusraportti luodaan komennolla:

```
mvn jacoco:report
```
Kattavuusraporttia voi tarkastella avaamalla selaimessa tiedoston *target/site/jacoco/index.html*.

### Suoritettavan jarin generointi

Komento generoi hakemistoon target suoritettavan jar-tiedoston FishQuest-1.0-SNAPSHOT.jar.

```
mvn package
```

### Checkstyle

Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla:

```
mvn jxr:jxr checkstyle:checkstyle
```
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html.
