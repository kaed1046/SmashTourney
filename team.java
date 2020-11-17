class team extends competitor{
    protected String name;
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
}