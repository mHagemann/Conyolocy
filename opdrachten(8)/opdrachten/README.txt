---------------------------------------------------------------
Als je je programma laat uitvoeren creeert
Code::Blocks daar een 'xterm' voor.

De gijze balk links in het venster is je scrollbar.
Scrollen kan op verschillende manieren:
- met je muis-wieltje "ergens" in het venster
- of, zet de muis in de grijze balk,
  linker muisknop = scroll-vooruit
  rechter muisknop = scroll-achteruit
  middelste muisknop = versleep de scrollbar op/neer


Met CONTROL-RECHTER muisknop kan je een ander "VT Font" uitkiezen,
varierend van "onleesbaar" tot "gigantisch".
De standaard instellingen zijn wat slecht leesbaar;
Je kan er voor zorgen dat xterm een ander font gaat gebruiken;
Als de file .Xresources in je home-directory nog niet bestaat
maak er dan een. Voeg dan de volgende regel toe:

*VT100.utf8Fonts.font:  -*-fixed-*-r-*--20-*-*-*-*-*-iso10646-*

Berg de file op. Daarna kan je twee dingen doen:
of) alle programma's afsluiten, uitloggen en opnieuw inloggen
of) in een terminal venster de opdracht
		xrdb -merge .Xresources
	invoeren.
Van nu af aan zou elke xterm dan het nieuwe, wat beter leesbare,
font moeten gebruiken.
---------------------------------------------------------------
xterm -T $TITLE -e
