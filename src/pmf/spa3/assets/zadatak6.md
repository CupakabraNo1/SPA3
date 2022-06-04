
# Zadatak 1 - stabla i pretrazivanja

- <u>Napraviti sortiranu mapu u kojoj se podaci interno cuvaju u binarnom stablu pretrazivanja. Tipovi za kljuceve i vrednosti treba da su opsti tipovi, nesto oblika <K extends Comparable<K>, V>, da bi imali uporedivost kljuceva.</u>

- <u>Korisiti komparator za sve interne operacije poredjenja kljuceva. Ako nije prosledjen komparator u konstruktoru, koristiti Comparator.naturalOrder().</u>

- <u>Postojanje null kljuceva je odredjeno ponasanjem komparatora, odnosno da li ce on baciti NullPointerException. Za testiranje rada sa takvim kljucevima se moze koristiti Comparator.nullsFirst(Comparator c) koji vraca novi komparator koji prima null, a inace koristi prosledjeni.</u>

- <u>Dozvoljeno je da postoje null vrednosti u mapi vezane za kljuceve.</u>

### Dati fajlovi kola*.txt

sadrze podatke o registarskim tablicama i poslednjoj osobi koja je vozila taj automobil. Podaci su razdvojeni sa ";" u istom redu.

kola1.txt je mali fajl, zgodan za inicijalna testiranja, kola[23]?.txt sadrze iste podatke, ali u raznim redosledima, sto utice na formiranje stabla.

## Metodi koje treba implementirati

 - <u>public void put(K key, V value)</u>
koji u mapu ubacuje vrednost value vezanu za kljuc key. Ako vec postoji vrednost za kljuc, zameniti je. Dozvoljene su null vrednosti.

 - <u>public boolean containsKey(K key) </u>-
koji vraca da li kljuc postoji u mapi.

 - <u>public V get(K key)</u> -
koji vraca vrednost za dati kljuc. Ako kljuc ne postoji u mapi vratiti null. Ovim je automatski nemoguce razlikovati da li u mapi postoji kljuc sa null vrednoscu vezanom za sebe ili ne postoji kljuc u mapi.

 - <u>public K minKey()</u>
 - <u>public K maxKey()</u> -
koji vracaju vrednosti najmanjeg, odnosno najveceg kljuca u mapi. Napisati ova dva metoda bez koriscenja rekurzije.

 - public List<K> keysInRange(K a, K b) -
koji vraca listu kljuceva koji su u nekom opsegu, ukljucujuci i a i b.

 - public int height() -
koji vraca visinu internog stabla - tj rastojanje do najdaljeg lista. Eksperimentisati sa kola[23]?.txt fajlovima koji imaju iste podatke u razlicitim redosledima.

 - public void balanceIfNeeded() -
koja interno stablo rebalansirano da bude ravnomernije rasporedjeno, ali samo ako se visine levog i desnog podstabla iz korena razlikuju za vise od 3. Ne zahteva se velika efikasnost, dozvoljeno je zauzimanje znacajnog dodatnog prostora, npr za nekakav niz.