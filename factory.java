class factory {
    factory () {}
    public player createPlayer (String playerType, String name) {
        if(playerType == "competitive") {
            return new competitiveplayer(name);
        }
        if(playerType == "casual") {
            return new casualplayer(name);
        }
        if(playerType == "combination") {
            return new combinationplayer(name);
        }
        return null;
    }
}