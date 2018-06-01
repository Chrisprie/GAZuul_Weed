/**
 * Diese Klasse modelliert R�ume in der Welt von Zuul.
 * 
 * Ein "Raum" repr�sentiert einen Ort in der virtuellen Landschaft des
 * Spiels. Ein Raum ist mit anderen R�umen �ber Ausg�nge verbunden.
 * M�gliche Ausg�nge liegen im Norden, Osten, S�den und Westen.
 * F�r jede Richtung h�lt ein Raum eine Referenz auf den 
 * benachbarten Raum.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
import java.util.HashMap;
import java.util.Set;
class Raum 
{
    public String beschreibung;
    public HashMap<String,Raum> ausgaenge;

    /**
     * Erzeuge einen Raum mit einer Beschreibung. Ein Raum
     * hat anfangs keine Ausg�nge.
     * @param beschreibung enth�lt eine Beschreibung in der Form
     *        "in einer K�che" oder "auf einem Sportplatz".
     */
    public Raum(String beschreibung) 
    {
        this.beschreibung = beschreibung;
        ausgaenge=new HashMap<String,Raum>();
    }

    /**
     * Definiere die Ausg�nge dieses Raums. Jede Richtung
     * f�hrt entweder in einen anderen Raum oder ist 'null'
     * (kein Ausgang).
     * @param richtung Die richtung durch die man den Raum verlassen kann
     * @param ausgang der entsprechende Ausgang der Richtung
     */
    public void setzeAusgang(String richtung,Raum nachher) 
    {
        ausgaenge.put(richtung,nachher);
    }

    public String gibAusgaengeAlsString () {
        String erg="Ausgänge: ";
        
        Set<String> s = ausgaenge.keySet();
        for (String ausgang:s) {
            erg+=ausgang+" ";
        }
        return erg;
    }
    
    public Raum gibAusgang (String richtung) {
        return ausgaenge.get(richtung);
    }

    /**
     * @return Die Beschreibung dieses Raums.
     */
    public String gibBeschreibung()
    {
        return "Du bist " +beschreibung;
    }

    /**
     * @return gibt die Beschreibung und alle Ausgänge als String zurück
     */
    public String toString() {
        return gibBeschreibung()+"\n"+gibAusgaengeAlsString();
    }
}
