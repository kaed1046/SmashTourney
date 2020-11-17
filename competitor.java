public class competitor {
    //private character character;
    static protected String name;
    private decorator skill;
    private int seed;
    private observer observer;

    private int wins;
    private int losses;
    private competitor[] played;
    private int numCompetitorsPlayed;
    competitor () {}
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
    public void addWin() {
        wins++;
    }
    public void addLoss() {
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
            if(otherComp_.name == played[i].name) {
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
