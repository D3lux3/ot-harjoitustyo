# Käyttöohje

## Lataaminen ja käynnistys.
Pelin lataaminen onnistuu [täältä!](https://github.com/D3lux3/ot-harjoitustyo/releases/tag/).

Käynnistys onnistuu suorittamalla komento konsolissa ja/tai klikkaamalla ohjelmaa.

```
java -jar platformer.jar
```

## Päävalikko

- Start
  - Aloittaa pelin
- Hiscores
  - Pääset näkemään kymmenen parhaan pelaajan suorituksen.
- Exit
  - Sulkee sovelluksen
<div align="center">
<img align="center" src="https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/imgs/menusc.png" width="400">
</div>
<br />
<br />
<br />
<br />


## Pelinäkymä
Kontrollit:
```
W / YLÖS / SPACE = Hyppy.
D / OIKEA = Liiku oikealle.
A / VASEN = Liiku vasemmalle.
```
Pelin ideana on kerätä kuvassa näkyviä kolikkoja(tukikuukausia).
Peli päättyy jos pelaaja putoaa alas tai pääsee maaliin. Maalista saa +10 pistettä.

<div align="center">
<img align="center" src="https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/imgs/pelisc.png" width="400">
</div>

<br />
<br />
<br />
<br />

## Editor näkymä
Kontrollit:
```
W  = Liiku ylös.
D = Liiku oikealle.
A  = Liiku vasemmalle.
```

Klikkaamalla ruutua lisätään tasoon siihen kohtaan olion(Laatikko, kolikko yms..). Monta kertaa klikkaamalla samaan kohtaan pystyy vaihtamaan haluamansa peli objektin. Klikkaamalla Save voi oman taso muutoksen tallentaa tiedostoon. Load nappia painamalla voi ladata kentän tiedostosta. Menua klikkaamalla vaihtuu näkymä takaisin päävalikkoon.
<div align="center">
<img align="center" src="https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/imgs/editorsc.png" width="400">
</div>

<br />
<br />
<br />
<br />

## Hiscore näkymä

Täältä näkee kymmenen parasta pelisuoritusta. Painamalla menu painiketta päästee takaisin päävalikkoon.
<div align="center">
<img align="center" src="https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/imgs/hiscoresc.png" width="400">
</div>

<br />
<br />
<br />
<br />


## Pelin lopetusnäkymä

Tänne on tarkoitus pelin jälkeen kirjoittaa pelaajan nimi, jotta se saadaan talletettua tietokantaan ja näkyviin hiscore näkymään.
<div align="center">
<img align="center" src="https://raw.githubusercontent.com/D3lux3/ot-harjoitustyo/master/documentation/imgs/endgamesc.png" width="400">
</div>