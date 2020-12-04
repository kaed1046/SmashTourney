public class casualplayer extends player {
    private team team;
    casualplayer (bean sourcebean) {
        super(sourcebean);
    }
//    casualplayer (String name_) {
//        player.name = name_;
//    }
    public team getTeam() {
        return team;
    }
    public void setTeam(team team_) {
        team = team_;
    }
}
