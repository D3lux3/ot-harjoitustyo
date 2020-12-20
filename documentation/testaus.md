## Testausdokumentti

Ohjelman testaus tapahtuu automatisoiduin yksikkö- ja integraatiotestein JUnitilla.

## Yksikkö- ja integraatiotestaus

### Pelilogiikka

Pelilogiikkaa testaavat integraatiotestit *PlayerEntityTest*, *GameLogicTest* *LevelTest*. Joiden testitapaukset testaavat pisteiden hallintaa, Pelikentän oikeellisuutta ja Pelaajan liikettä.

Testit käyttävät testejä varten tehtyjä konstruktoreja, jotka eivät ole riippuvaisia JavaFx:ästä. Esim. stagesta tai ImagePatternista.

### DAO

DAO Luokan testaus tapahtuu luomalla ensin test.db niminen tietokantatiedosto ja ennen jokaista testitapausta sen tyhjentäminen. Testitapaukset käsittelevät kaikkia SQL kyseliytä jota projekti hyödyntää. Testien jälkeen hyödynnetään JUnitin  @AfterClass ominaisuutta, jotta test.db tietokanta voidaan poistaa testien jälkeen.


### Testauskattavuus
Kaikkea muuta paitsi käyttöliittymää ja Main olioita testataan. Yksittäisiä metodeja, jotka ovat riippuvasia JavaFX:n on jäänyt testaamatta, näitä varten olen luonut erillisiä metodeita vain testausta varten jotka, eivät ole riippuvasia edellä mainituista asioista. Myös equals ja hashcodet ovat jääneet osittain testaamatta.

Rivikattavuus on 70%

![alt text](https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/imgs/kattavuus.png "Testikattavuus")