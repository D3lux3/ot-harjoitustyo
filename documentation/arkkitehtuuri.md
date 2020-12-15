# Arkkitehtuurikuvaus

## Rakenne

Ohjelmanpakkaus rakenne on kolmitasoinen.


*Rottasimulaattori.ui* sisältää graafisen käyttöliittymän JavaFX osat.

*Rottasimulaattori.game* sisältää pelinlogiikan ja kaiken pelin toteutukseen liittyen.



*Rottasimulaattori.dao* sisältää tietokanta olion, jonka avulla tietokantaa luetaan ja kirjoitetaan


**Pakkausrakenne**


![alt text](https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/pakkaus1.png "Pakkausrakenne.")

**UML Kaavio**


![alt text](https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/kaavio.png "UML luonnos.")

## Pelilogiikka

Pelinlogiikkaa hoitaa pääasiassa Gamelogic olio. GameLogic pitää kirjaa pelin pistetilanteesta. Gamelogic tarjoaa muille luokille mahdollisuuden kysellä tämän hetkistä pistetilannetta, ja myös Stage oliota käyttöliittymää varten. Pelin hahmot (Entityt) laitetaan suoraan käyttöliittymään *Gamescene*.

Pelilogiikkaa kuvaa seuraava luokkakaavio:

![alt text](https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/luokkakaavio2.png "Luokkakaavio")


## Sekvenssikaavio
![alt text](https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/sekvenssi.png "Sekvenssikaavio")
