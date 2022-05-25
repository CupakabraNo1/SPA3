# Zadaci sa vežbi 1

## SPA3, vezbe 1


- Napraviti sopstvenu implementaciju grafa

- Ucitavanje iz fajla, u formatu kao sto je dat u knjizi (V, pa E, pa E
  redova sa parovima)

- Koristiti listu susedstava.  U "cistoj" Javi, ako su cvorovi samo int-ovi
do broja cvorova, najvise smisla ima nesto kao List<Set<Integer>>. Ako
se koristi konkretan ArrayList i HashSet u implementaciji, performanse su
merljive sa matricom susedstava

## Klasa Graf
- <u>polja: susedi, broj cvorova, broj grana</u>
- <u>metodi: broj cvorova, broj grana, susedi od cvora, dodaj granu,...</u>

- <u>Rekurzivni DFS</u>
  - <u>naci komponentu pocev od nekog cvora</u>
  - <u>naci sve komponente i ispisati ih (skupove cvorova)</u>
  - <u>naci da li postoji put izmedju dva cvora</u>
  - <u>naci i ispisati bilo koji put izmedju dva data cvora</u>

- <u>Razmotriti iterativnu implementaciju DFS (i analogiju sa BFS koji sledi)</u>

- Iterativni BFS
  - <u>ispisati rastojanja do svih tacaka od neke zadate</u>

  - napraviti metod koji vraća da li postoje konture u grafu (jednostavnija varijanta da li je zadati čvor deo neke konture)