import java.util.HashMap;
import java.util.Set;
public class Spieler {
    private Raum aktuellerRaum;
    private String name;
    private HashMap<String,Gegenstand> gegenstaende;
    private int cash;
    public Spieler (Raum aktuellerRaum) {
        this.aktuellerRaum=aktuellerRaum;
        gegenstaende = new HashMap<String,Gegenstand>();
        cash=50;
    }

    public Spieler (Raum aktuellerRaum, String name) {
        this.aktuellerRaum=aktuellerRaum;
        this.name=name;
        gegenstaende = new HashMap<String,Gegenstand>();
        cash=50;
    }

    public void addGegenstand(Gegenstand g) {
        int preis=g.getPreis();
        if (!g.isDropped() && preis<=cash) {
            g.setDropped(true);
            gegenstaende.put(g.getName(),g);
            System.out.println("gekauft");
        } else if (g.isDropped()) {
            gegenstaende.put(g.getName(),g);
            System.out.println("eingesammelt");
        }else{
          System.out.println("du hast nicht genuegend geld");
        }
        
    }

    public void removeGegenstand (String name) {
        gegenstaende.remove(name);
        System.out.println("Gegenstand gedroped");
    }

    public Gegenstand getGegenstand(String name) {
        return gegenstaende.get(name);
    }

    public String getGegenstaende () {
        String erg="Inventar: \n";

        Set<String> s = gegenstaende.keySet();
        for (String name:s) {
            erg+=gegenstaende.get(name)+"\n";
        }
        return erg;
    }
}