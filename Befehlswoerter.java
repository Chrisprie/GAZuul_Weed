import java.util.HashMap;
import java.util.Set;
class Befehlswoerter{
    
    private HashMap<String,Befehlswort> gueltigeBefehle; 
    
    
    public String gibBefehlsliste () {
        String erg="";
        Set<String> set = gueltigeBefehle.keySet();
        for (String befehl:set) {
            erg+=befehl+" ";
        }
        return erg;
    }
    
    public Befehlswoerter()
    {
        gueltigeBefehle = new HashMap<String, Befehlswort>();
        System.out.println(Befehlswort.GO.toString());
        for (Befehlswort befehl: Befehlswort.values()) {
            
            gueltigeBefehle.put(befehl.toString(), befehl);
            if (befehl!= Befehlswort.UNKNOWN) {
                //gueltigeBefehle.put(befehl.toString(), befehl);
            }
        }
    }

    public Befehlswort gibBefehlswort(String wort)
    {
        Befehlswort befehlswort = gueltigeBefehle.get(wort);
        if(befehlswort != null) {
            return befehlswort;
        }
        else {
            return Befehlswort.UNKNOWN;
        }
    }
    
    /**
     * Pr�fe, ob eine gegebene Zeichenkette ein g�ltiger
     * Befehl ist.
     * @return 'true', wenn die gegebene Zeichenkette ein g�ltiger
     * Befehl ist, 'false' sonst.
     */
    public boolean istBefehl(String eingabe)
    {
        if (gueltigeBefehle.get(eingabe)!=null) {
        return true;
        }
        return false;
    }
}
