public class competitor {
    //private character character;
    static protected String name;
    private decorator skill;
    private int seed;
    private observer observer;

    private int wins;
    private competitor[] rr_winlist = new competitor[50];
    private int losses;
    private competitor[] rr_loselist = new competitor[50];
    private competitor[] played = new competitor[50];
    private int numCompetitorsPlayed;
    competitor () {
        wins = 0;
        losses = 0;
    }
    competitor (String name_) {
        competitor.name = name_;
    }
    public int getSeed() {
        return seed;
    }
    public void setSeed(int seed_) {
        seed = seed_;
    }
    public void setName(String name_) {
        name = name_;
    }
    public String getName() {
        return name;
    }
    public void addWin(competitor w) {
        rr_winlist[wins] = w;
        wins++;
    }
    public void addLoss(competitor l) {
        rr_loselist[losses] = l;
        losses++;
    }
    public int getWins() {
        return wins;
    }
    public int getLosses() {
        return losses;
    }
    public void addCompPlayed(competitor comp_) {
        played[numCompetitorsPlayed] = comp_;
        numCompetitorsPlayed++;
    }
    public boolean checkIfPlayed(competitor otherComp_) {
        for(int i = 0; i < numCompetitorsPlayed; i++) {
            if(otherComp_ == played[i]) { //IDENTITY - each object unique
                return true;
            }
        }
        return false;
    }
    public void resetCompPlayed() {
        for(int i = 0; i < numCompetitorsPlayed; i++) {
            played[i] = null;
        }
        numCompetitorsPlayed = 0;
    }
}
