public class Gegenstand {
    private String name,langeBeschreibung;
    private int preis;
    private boolean dropped;

    static enum Woerter {
        EAT,BEAM,SETBEAM;
    }
    public Gegenstand(String name,String langeBeschreibung,int preis) {
        this.name=name;
        this.langeBeschreibung=langeBeschreibung;
        this.preis=preis;
    }

    public void setPreis (int preis) {
        this.preis=preis;
    }

    public int getPreis () {
        return this.preis;
    }

    public String getName () {
        return this.name;
    }

    public boolean isDropped () {
        return dropped;
    }

    public void setDropped (boolean dropped) {
        this.dropped=dropped;
    }

    public String getlangeBeschreibung () {
        if (this.preis==0) {
            return "   > "+this.name+" (FREE): "+this.langeBeschreibung+"\n";
        }
        return "   > "+this.name+" ("+this.preis+"€): "+this.langeBeschreibung+"\n";
    }

    public String toString(){
        return getlangeBeschreibung();
    }
}