# Arkkitehtuurikuvaus

## Rakenne

Ohjelmanpakkaus rakenne on kolmitasoinen.


*Rottasimulaattori.ui* sisältää graafisen käyttöliittymän JavaFX osat.

*Rottasimulaattori.game* sisältää pelinlogiikan ja kaiken pelin toteutukseen liittyen.



*Rottasimulaattori.dao* sisältää tietokanta olion, jonka avulla tietokantaa luetaan ja kirjoitetaan


**Pakkausrakenne**


![alt text](https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/imgs/pakkaus1.png "Pakkausrakenne.")

**UML Kaavio**


![alt text](https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/imgs/kaavio.png "UML luonnos.")

## Pelilogiikka

Pelinlogiikkaa hoitaa pääasiassa Gamelogic olio. GameLogic pitää kirjaa pelin pistetilanteesta. Gamelogic tarjoaa muille luokille mahdollisuuden kysellä tämän hetkistä pistetilannetta, ja myös Stage oliota käyttöliittymää varten. Pelin hahmot (Entityt) laitetaan suoraan käyttöliittymään *Gamescene*.

Pelilogiikkaa kuvaa seuraava luokkakaavio:

![alt text](https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/imgs/luokkakaavio2.png "Luokkakaavio")

## Päätoiminnallisuudet


## Sekvenssikaavio

Seuraavat sekvenssikaaviot kuvaavat tärkeitä ominaisuuksia projektissa.

### Pelaaminen

Kun käyttäjä painaa start painiketta etenee sovellus seuraavalla tavalla:

![alt text](https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/imgs/sekvenssi.png "Sekvenssikaavio")

Start painiketta painaessa UI tässä tilanteessa Menu olio vaihtaa näkymän GameSceneen. Gamescene kutsuu konstruktorissaan metodia initContent(), joka lukee muistiin tallenetun tason ja luo tarvittavat Entityt ympäri kenttää sen mukaan. *(PlayerEntity, Entity, CoinEntity, GoalEntity)*.
Tämän jälkeen jää GameScene kuuntelemaan käyttäjän näppäin painalluksia ja toimii sen mukaan. Jos käyttäjä painaa painikkeita: *D/OIKEA*, niin kutsutaan PlayerEntityn metodia moveRight(). Metodille annetaan kaksi parametria, jotka ovat arvo tässä tilanteessa **5** ja kaikki PlatformEntityt, jotta PlayerEntity voi tarkistaa pystyykö se liikkumaan oikealle nuo 5 askelta. Jos pelaaja osuu kolikkoon, niin GameScene kutsuu GameLogic olion metodia *addScore()*, joka lisää yhden pisteen pistetilanteeseen. Jos pelaaja osuu maaliin *GoalEntity*, niin kutsutaan samaista metodia *addScore(10)* Gamelogikista ja annetaan sille parametriksi 10, eli lisätään pistetilanteeseen 10 pistettä. Tämän jälkeen kutsutaan PlayerEntityn metodia *killPlayer()*, joka "tappaa" pelaajan, eli vaihtaa boolean arvo *Alive = false*. Tämän jälkeen kutsutaan GameScenen metodia *gameOver()*, joka hoitaa pelin lopetuksen ja siirtymisen *EndGameScene* näkymään.

### Hiscorejen tallennus
![alt text](https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/imgs/daosekvenssi.png "HiscoreSekvenssikaavio")

Kun käyttäjä on päässyt EndGameScene näkymään, ja on valmis tallettamaan oman pelituloksensa kysytään käyttäjän nimeä. Käyttäjä kirjoittaa nimensä tekstikenttään ja painaa **Submit** painiketta. Tässä vaiheessa EndGameScene kutsuu luo HiscoresDao olion, jonka konstruktori pitää luo tietokanta tiedoston, jos sitä ei ole vielä tehty, jos on niin ohjelman suoritus jatkuu normaalisti. Sitten kutsutaan metodia SaveScores(name, score), joka tallettaa pelaajan nimen ja pistemäärän tietokantaan. Tämän jälkeen vaihetaan näkymä takaisin päävalikkoon *Menu*.



## Ohjelman rakenteeseen jääneet heikkoudet
### Pelinlogiikka

Tein projektille pohjan aluksi, jossa suurin osa pelinlogiikasta suoritettiin GameScenessä. Refaktoroin projektini ja ajauduin ongelmaan, jonka takia en pystynyt eriyttämään kaikkea toiminnallisuutta eri luokille. Tämän takia GameScene on hieman laajempikokonaisuus.

### Stage olio

Samasta syystä kuin edellinen, joudun siirtäämään Stage oliota ympäri eri luokkia, jotta saan eri näkymät toimivaan yhteen. Olisi aluksi jo pitänyt luoda luokka joka vastaisi kaikesta siirtymisestä näkymien välillä.