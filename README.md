## Maven (Maven 3.9.9) 
**Használata:** 

Elvileg a Maven wrappert tartalmazza a root mappa, ezért külön a Maven globális telepítése nélkül is meg lehet úszni. 
Ezt mindenki magának a projekt root mappájában kiadott `./mvnw clean install` paranccsal tudja megtenni. Innen már kéne futnia a `mvn` kezdetű parancsoknak, de ha VSCodeban fenn van a Mavenes extension (a Java pack része) akkor a fájlrendszer alatt is a Maven fül alatt ott van sok *kattintható* nyalánkság. 

**Extra:**

A Maven fájrendszere kicsit bloated, de csak jobban vannak nestelve a mappák meg ilyesmi, tűrhető. Illetve a .gitignore is be lett állítva, hogy a sok felesleget defaultból ne vegye a commithoz.

## Tesztek

**Figyelem, ez csak tervezet!** Minden tesztesethez a `/src/test/resources` mappában kell egy teszteset nevű mappa, benne `start.txt`, `expected.txt`, `commands.txt`, melyek a kiinduló állapotot, az elvárt végállapotot és a bemeneti parancsokat tartalmazzák.