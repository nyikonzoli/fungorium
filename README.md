## Maven (Maven 3.9.9) 
**Használata:** 

A Maven wrappert tartalmazza a root mappa, ezért külön a Maven globális telepítése nélkül is meg lehet úszni. 
A Maven parancsok használatát így mindenki a projekt root mappájában kiadott `./mvnw <parancs>` (Linux) vagy `mvnw.cmd <parancs>` (Windows) módon tudja megtenni.

## Tesztek

Minden tesztesethez a `/src/test/resources` mappában kell egy teszteset nevű mappa, benne `start.txt`, `expected.txt`, `commands.txt`, melyek a kiinduló állapotot, az elvárt végállapotot és a bemeneti parancsokat tartalmazzák.

A teszteknél az elejére-végére save, load parancs NEM KELL! Azt a tesztelő osztály automatikusan intézi.