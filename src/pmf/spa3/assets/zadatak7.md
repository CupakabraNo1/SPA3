# Dvostrani brzi skup

## Napraviti klasu Imenik.

U njoj treba da su uparene vrednosti brojeva telefona i imena osoba. I jedno i drugo tretirati kao stringove.

Ideja je da se jedan broj telefona moze pojaviti samo jednom, dok se istovremeno ne sme pojaviti ni isto ime vise puta.

Bitno je i da imena mogu da se izlistaju abecededno u svakom momentu.

Brojevi telefona nam nisu bitni u kom su redosledu.

Implementaciju bazirati na postojecim klasama za skladistenje podataka, a primarni cilj je da se implementiraju trazena ogranicenja. Sledeci cilj je da sve bude sto brze, mozemo "zrtvovati" memoriju radi brzine.

Neki od metoda koji se ocekuju:

- public String getTel(String osoba)

- public String getOsoba(String tel)

- public boolean put(String osoba, String tel)

- public Set<String> getBrojevi()

- public List<String> getOsobe()
// vracena lista treba da bude sortirana po osobama


## Interaktivni meni
Napraviti strukturu za interaktivni meni sa podmenijima. Stavke u meniju treba da imaju svoj opis, taster za aktivaciju, potencijalnu akciju i potencijalni podmeni. Racuna se da stavka koja nema svoj podmeni finalna, tj njen odabir ce pokrenuti neku akciju. Akcije i pokretanje nisu tema zadatka, nego ce se samo ispisati sam tekst akcije. Tasteri za aktivaciju moraju biti jedinstveni u okviru grupe stavki koje se odjednom prikazuju.

Napraviti program koji formira neki meni, te ga prikazuje korisniku, tako da se vide sve stavke sa nivoima dubine.

"Prirodna" organizacija menija je u binarnom stablu, jedino sto nisu u pitanju klasicno "levo" i "desno" dete, nego podmeni i sledeca stavka u istom meniju. Na primer krenuvsi od korena i prateci pokazivace na sledecu stavku trebalo bi da izlistamo sve stavke iz najviseg nivoa menija. Ako koren ima podmeni, to je pokazivac na prvu stavku iz podmenija, a prateci njegove "sledece" veze, izlistacemo sve stavke iz tog podmenija. Ovakvo stablo nije BST.

Za potrebe jedinstvenosti slova u nekom meniju se moze u "roditelju" te liste pamtiti dosad ubacena slova.

Za dodavanje stavki u kompletan meni bi trebalo da se zadaje kompletna putanja do stavke, na primer "Edit", "indent", "auto indent", ili da se koriste aktivaciona slova za putanju. Alternativa u opstem slucaju je da se uvedu "id"-ovi za menije, pa se stavka dodaje u podmeni sa nekim id-om.

Napraviti i interaktivnu verziju. Korisniku dati da unese po slovo i da time bira menije i podmenije. Ako se unese '.' prebaciti se skroz na pocetak menija. Kad se odabere neka konacna akcija ispisati je i vratiti se na pocetak. Ako korisnik unese nesto sto ne postoji u trenutnom meniju ponovo ga ispisati.