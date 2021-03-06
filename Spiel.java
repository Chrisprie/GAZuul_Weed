import java.util.Stack;
import java.util.Set;
class Spiel 
{
    private Parser parser;
    private Raum aktuellerRaum;
    private Stack<Raum> backStack;
    private Spieler s;
    private int zuege;
    /**
     * Erzeuge ein Spiel und initialisiere die interne Raumkarte.
     */
    public Spiel() 
    {
        raeumeAnlegen();
        parser = new Parser();
        backStack = new Stack<Raum>();
        zuege=3;
    }

    /**
     * Erzeuge alle Räume und verbinde ihre Ausg�nge miteinander.
     */
    private void raeumeAnlegen()
    {
        Raum duenger,farming1,farming2,kreuzung1,living1,living2,kunde,main1,main2,haus, main3, kreuzung2, second1, second2,green1,green2,plantage,waffe;

        // die Raeume erzeugen
        duenger = new Raum("im Baumarkt");
        farming1 = new Raum("in der Farming Ave.");
        farming2 = new Raum("in der Farming Ave.");
        kreuzung1 = new Raum("auf einer Kreuzung");
        living1 = new Raum("in der Living Ave.");
        living2 = new Raum("in der Living Ave.");
        kunde = new Raum("bei deinem Kunden");
        main1 = new Raum("in der Main St.");
        main2 = new Raum("in der Main St.");
        haus = new Raum("in deinem Haus");
        main2=new Raum("auf der Main Street");
        main3 = new Raum("auf der Main Street");
        kreuzung2 = new Raum("auf einer Kreuzung");
        second1 = new Raum("auf der Secondary Street");
        second2 = new Raum("auf der Secondary Street");
        green1=new Raum("auf der Green Avenue");
        green2=new Raum("auf der Green Avenue");
        plantage=new Raum("in deiner Plantage");
        waffe=new Raum("im Waffenshop");

        // die Ausgaenge initialisieren
        String[] k=new String[4];
        k[0]="north";k[1]="east";k[2]="south";k[3]="west";
        duenger.setzeAusgang(k[3],farming2);
        farming1.setzeAusgang(k[0],farming2);
        farming1.setzeAusgang(k[2],kreuzung1);
        farming2.setzeAusgang(k[1],duenger);
        farming2.setzeAusgang(k[2],farming1);
        kreuzung1.setzeAusgang(k[0],farming1);
        kreuzung1.setzeAusgang(k[1],main1);
        kreuzung1.setzeAusgang(k[2],living1);
        living1.setzeAusgang(k[0],kreuzung1);
        living1.setzeAusgang(k[2],living2);
        living2.setzeAusgang(k[0],living1);
        living2.setzeAusgang(k[1],kunde);
        kunde.setzeAusgang(k[3],living2);
        main1.setzeAusgang(k[1],main2);
        main1.setzeAusgang(k[3],kreuzung1);
        main2.setzeAusgang(k[1],main3);
        //main2.setzeAusgang(k[2],haus);
        main2.setzeAusgang(k[3],main1);
        haus.setzeAusgang(k[0],main2);
        main3.setzeAusgang(k[1], kreuzung2);
        main3.setzeAusgang(k[3],main2);
        kreuzung2.setzeAusgang(k[1],second1);
        kreuzung2.setzeAusgang(k[2], green1);
        kreuzung2.setzeAusgang(k[3], main3);
        second1.setzeAusgang(k[1],second2);
        second1.setzeAusgang(k[3], kreuzung2);
        second2.setzeAusgang(k[2], waffe);
        second2.setzeAusgang(k[3],second1);
        green1.setzeAusgang(k[0],kreuzung2);
        green1.setzeAusgang(k[2],green2);
        green2.setzeAusgang(k[0],green1);
        green2.setzeAusgang(k[3],plantage);
        plantage.setzeAusgang(k[1],green2);
        waffe.setzeAusgang(k[0],second2);

        haus.addGegenstand(new Gegenstand("1gegenstand","Ich bin ein Gegenstand",100));
        s = new Spieler(haus,"Spieler Lars");
        aktuellerRaum = haus;  // das Spiel startet draussen
    }

    /**
     * Die Hauptmethode zum Spielen. L�uft bis zum Ende des Spiels
     * in einer Schleife.
     */
    public void spielen() 
    {            
        willkommenstextAusgeben();

        // Die Hauptschleife. Hier lesen wir wiederholt Befehle ein
        // und f�hren sie aus, bis das Spiel beendet wird.

        boolean beendet = false;
        while (! beendet && this.zuege>0) {
            Befehl befehl = parser.liefereBefehl();
            beendet = verarbeiteBefehl(befehl);
        }
        if (zuege<=0) {
            System.out.println("Du hast keine Züge mehr!");
        }
        System.out.println("Danke für dieses Spiel. Auf Wiedersehen.");
    }

    /**
     * Einen Begr��ungstext f�r den Spieler ausgeben.
     */
    private void willkommenstextAusgeben()
    {
        System.out.println();
        System.out.println("Willkommen zu Zuul_Weed!");
        System.out.println("Dies ist das neueste unglaublichste brillianteste Spiel der Welt");
        System.out.println("Tippen sie 'help', wenn Sie Hilfe brauchen.");
        System.out.println();
        rauminfoausgeben();
    }

    private void rauminfoausgeben () {
        System.out.println(aktuellerRaum.gibLangeBeschreibung());
    }

    /**
     * Verarbeite einen gegebenen Befehl (f�hre ihn aus).
     * @param befehl Der zu verarbeitende Befehl.
     * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
     */
    private boolean verarbeiteBefehl(Befehl befehl) 
    {
        boolean moechteBeenden = false;

        Befehlswort befehlswort = befehl.gibBefehlswort();
        
         if(befehlswort == Befehlswort.UNKNOWN) {
            System.out.println("Ich weiß nicht, was Sie meinen...");
            return false;
        }else if (befehlswort == Befehlswort.HELP)
            hilfstextAusgeben();
        else if (befehlswort == Befehlswort.GO)
            wechsleRaum(befehl);
        else if (befehlswort == Befehlswort.QUIT) {
            moechteBeenden = beenden(befehl);
        } else if (befehlswort == Befehlswort.LOOK) {
            umsehen();
        } else if (befehlswort == Befehlswort.BACK) {
            back();
        }else if (befehlswort == Befehlswort.TAKE) {
            take(befehl.gibZweitesWort());
        }else if (befehlswort == Befehlswort.DROP) {
            drop(befehl.gibZweitesWort());
        }
        return moechteBeenden;
    }

    private void drop(String name){
        if(name == null){
           System.out.println("welchen Gegenstand meinst du?");
           return;
        }
        Gegenstand g = s.getGegenstand(name);
        if(g == null){
            System.out.println("du besitzt diesen Gegenstand nicht");
            return;
        }
        aktuellerRaum.addGegenstand(s.getGegenstand(name));
        s.removeGegenstand(name);
    }

    private void take(String name){
        if(name == null){
           System.out.println("welchen Gegenstand meinst du?");
           return;
        }
        Gegenstand g = aktuellerRaum.getGegenstand(name);
        if(g == null){
            System.out.println("dieser Gegenstand liegt nicht im Raum?");
            return;
        }
        s.addGegenstand(aktuellerRaum.getGegenstand(name));
        aktuellerRaum.gegenstaende.remove(name);
    }

    private void back () {
        if (backStack.isEmpty()) {System.out.println("Ich kann nicht zurück gehen");
        } else {
            aktuellerRaum = backStack.pop();
            System.out.println(aktuellerRaum.gibLangeBeschreibung());
        }
    }

    private void umsehen () {
        System.out.println(aktuellerRaum.gibLangeBeschreibung());
    }
    // Implementierung der Benutzerbefehle:

    /**
     * Gib Hilfsinformationen aus.
     * Hier geben wir eine etwas alberne und unklare Beschreibung
     * aus, sowie eine Liste der Befehlsw�rter.
     */
    private void hilfstextAusgeben() 
    {
        System.out.println("Du musst deinem Kundem die Ware liefern");
        System.out.println("Du irrst auf schlechten schizophren Badesalzen ahnungslos durch die Stadt");
        System.out.println();
        System.out.println("Dir stehen folgende Befehle zur Verfügung: "+parser.zeigeBefehle());
        System.out.println("\n"+aktuellerRaum.gibLangeBeschreibung());
    }

    /**
     * Versuche, den Raum zu wechseln. Wenn es einen Ausgang gibt,
     * wechsele in den neuen Raum, ansonsten gib eine Fehlermeldung
     * aus.
     */
    private void wechsleRaum(Befehl befehl) 
    {
        if(!befehl.hatZweitesWort()) {
            // Gibt es kein zweites Wort, wissen wir nicht, wohin...
            System.out.println("Wohin möchtest du gehen?");
            return;
        }

        String richtung = befehl.gibZweitesWort();

        // Wir versuchen den Raum zu verlassen.
        Raum naechsterRaum = aktuellerRaum.ausgaenge.get(befehl.gibZweitesWort());

        if (naechsterRaum == null) {
            System.out.println("Die Stimmen in meinem Kopf verbieten mir diesen Weg!");
        }
        else {
            backStack.push(aktuellerRaum);
            boolean erg=false;
            Set<String> set = naechsterRaum.ausgaenge.keySet();
            for (String s:set) {
                if (naechsterRaum.ausgaenge.get(s)==aktuellerRaum) {
                    erg=true;
                }
            }
            if (!erg) {
                System.out.println("Das Badesalz hat eine Psychose ausgelöst, sodass du nicht mehr zurück gehen kannst.");
                backStack.clear();
            }
            aktuellerRaum = naechsterRaum;
            zuege--;
            System.out.println("Du hast noch "+this.zuege+" Züge\n"+aktuellerRaum.gibLangeBeschreibung());
        }
    }

    /**
     * "quit" wurde eingegeben. �berpr�fe den Rest des Befehls,
     * ob das Spiel wirklich beendet werden soll.
     * @return 'true', wenn der Befehl das Spiel beendet, 'false' sonst.
     */
    private boolean beenden(Befehl befehl) 
    {
        if(befehl.hatZweitesWort()) {
            System.out.println("Was soll beendet werden?");
            return false;
        }
        else {
            return true;  // Das Spiel soll beendet werden.
        }
    }
}
