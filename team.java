class team {
    private String name;
    private int wins;
    private int losses;
    private team[] played;
    private int numTeamsPlayed;
    public team () {
        team[] played = new team[50];
        for(int i = 0; i < 50; i++) {
            played[i] = null;
        }
        numTeamsPlayed = 0;
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
    public void addTeamPlayed(team teamPlayed) {
        played[numTeamsPlayed] = teamPlayed;
        numTeamsPlayed++;
    }
    public boolean checkIfPlayed(team otherTeam) {
        for(int i = 0; i < numTeamsPlayed; i++) {
            if(otherTeam.name == played[i].name) {
                return true;
            }
        }
        return false;
    }
    public void resetTeamsPlayed() {
        for(int i = 0; i < numTeamsPlayed; i++) {
            played[i] = null;
        }
        numTeamsPlayed = 0;
    }
}