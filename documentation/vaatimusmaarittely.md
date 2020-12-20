# Vaatimusmäärittely

### Sovelluksen tarkoitus

Sovellukseni tulee olemaan 2d seikkailu peli. Pelissä pelataan rotalla, jonka tarkoitus on kerätä kela-kolikoita, josta pelaaja saa pisteitä. Sovelluksessa on mahdollisuus luoda omia kenttiä ja jakaa niitä ystävien kesken.


### Käyttöliittymäluonnos
![alt text](https://raw.githubusercontent.com/D3lux3/ratsimulator/master/documentation/imgs/luonnos.png "Luonnos")

## Toiminallisuus

### Menunäkymä
- Pelaaja voi aloittaa pelin
- Editor jossa voi luoda oman kentän
- Katsoa hiscoret
- Poistua pelistä

### Editor näkymä
- Pelaajalla mahdollisuus luoda tai muokata valmista kenttää omien halujensa mukaan.
- Mahdollisuus tallentaa oma kenttä .txt tiedostoon.
- Mahdollisuus ladata oma kenttä .txt tiedostosta.

### Hiscore näkymä
- Pelaaja näkee top 10 parasta pelitulosta.
- Painike, josta pelaaja pääsee tarvittaessa takaisin menunäkymään.
- Pelitulokset ladataan SQL tietokannasta.

### Pelin lopetusnäkymä
- Sisältää tekstikentän pelaajan nimeä varten, joka talletetaan pisteiden kanssa tietokantaan.
