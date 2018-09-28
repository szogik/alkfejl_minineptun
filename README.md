# Alkalmazások fejlesztése beadandó feladat - Mini Neptun

# Funkcionális követelmények:

  1. A funkciók elérése autentikációhoz kötött. Elsőként regisztráció szükséges. A regisztrált felhasználó bejelentkezés után tudja elérni az egyes funkciókat. Az felhasználók szerepkörüktől függően eltérő jogosultságokkal rendelkeznek.
  2. Bejelentkezéshez szükséges a jelszó és a felhasználónév megadása. Az esetleges hibákat (pl. rossz jelszó, felhasználó nem létezik stb.) jelezni kell a felhasználónak.
  3. A bejelentkezés után a felhasználó a kezdőoldalra kerül. Innen a menüben található gombok segítségével navigálhat az oldalon.
  4. Lehetőség van a tantárgyak és kurzusok listázására, az eredmények szűrésére.
    - A listából kiválaszthatóak az egyes elemek, amelyek törölhetőek, módosíthatóak, illetve új elemek létrehozhatóak (oktató vagy admin esetében).
    - A hallgató szintén megtekintheti a kurzusokat. Az általa még fel nem vett kurzust felveheti, a felvett kurzust leadhatja.
  5. A hallgatók és oktatók meglévő kurzusaikat egy külön oldalon megtekinthetik, a találatokat szűrhetik.
  
# Nem funkcionális követelmények:

 - Könnyen kezelhető, áttekinthető felhasználói felület.
 - A felhasználók adatainak biztonságát garantálni kell.
 - Az alkalmazásnak támogatnia kell a különböző böngészőket és eszközöket (PC, mobil stb).

# Fogalmak:

  - Tantárgy: Az egyes kurzusokhoz tartozó tantárgy.
  - Kurzus: Valamilyen tantárgyhoz tartozó tanóra, amelyet oktató tart és hallgatók látogatják.
  - Kurzus felvétele: A hallgatónak lehetősége van a meghírdetett kurzusok felvételére.
  - Kurzus létrehozása: Az oktatók és adminok kurzusokat hozhatnak létre az egyes tantárgyakhoz.
  
# Szerepkörök:

  - Hallgató: Jogosult a meghírdetett kurzusok és a kurzust felvett hallgatók megtekintésére, a kurzusok felvételére, felvett kurzusainak leadására.
  - Oktató: Jogosult az általa oktatott tárgyakban kurzus létrehozására, a meghírdetett kurzusok és a kurzust felvett hallgatók megtekintésére, saját kurzusainak törlésére.
  - Admin: Létrehozhat, módosíthat, törölhet bármilyen kurzust. A hallgatókat fel- és lejelentkeztetheti a kurzusokról.
