# Stabla - zadatak 2
## Zadatak 2 - n-arna stabla

- Napraviti brojeci skup - tj skup koji pamti koliko puta je nesto dodato u njega. Uzeti da se u skup dodaju samo stringovi.

- Napraviti glavni program (moze main u okviru skupa) koji iz nekog fajla ucitava stringove, smesta ih u skup i ispisuje broj pojava.

- U okviru skupa treba da postoje metodi:

    - <u>dodavanje stringa u skup</u>

    - <u>za ispitivanje koliko puta je neki string dodat, koji vraca 0 ako dosad taj string nije vidjen</u>

    - <u>ispis svih stringova dosada vidjenih i njihov broj</u>

    - <u>vracanje liste sa svim stringovima koji su ubacivani u skup</u>

    - <u>vracanje skupa reci koje su ubacivane</u>

    - <u>vracanje liste najcescih reci. Ako ima vise sa istim brojem pojava, treba sve vratiti, ako ima samo jedna koja se najvise pojavljuje vratiti listu sa samo njom. Razmotriti prosirenja klase koja vode do optimizacije ovog metoda ako je cesto potreban</u>.

    - <u>vracanje liste reci koje pocinju datim prefiksom</u>

    - <u>brisanje neke reci iz skupa. ovo se moze razmatrati odvojeno od optimizacije za najcesce reci</u>.

## U vezi implementacije
Korisititi n-arna stabla, u duhu strukture Trie.

Npr moze se predpostaviti varijanta sa ASCII znacima i napraviti niz od 256 pokazivaca. Moze se uzeti i varijanta sa samo "redovnim" malim slovima i napraviti niz od 26 pokazivaca i usput konvertovati vrednosti da se mapiraju na ovo.

Za zanimljivije implementacije koje mogu da uzmu proizvoljne ulaze, a da ne zauzimaju sulude kolicine memorije, moze se koristiti i nesto kao Map<Character, Cvor>.