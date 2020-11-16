class factory {
    factory () {}
    public player createPlayer (String playerType) {
        if(playerType == "competitive") {
            return new competitiveplayer();
        }
        if(playerType == "casual") {
            return new casualplayer();
        }
        if(playerType == "combination") {
            return new combinationplayer();
        }
        return null;
    }
}