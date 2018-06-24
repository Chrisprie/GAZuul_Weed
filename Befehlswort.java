public enum Befehlswort {
    GO("go"),QUIT("quit"),HELP("help"),LOOK("look"),BACK("back"),TAKE("take"),DROP("drop"),UNKNOWN("?");
    
    private String befehlswort;
    
    Befehlswort(String befehlswort) {
        this.befehlswort=befehlswort;
    }
    
    public String toString () {
        return befehlswort;
    }
}