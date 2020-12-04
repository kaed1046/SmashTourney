class factory {
    factory () {}
    public player createPlayer (String playerType) {
        bean eventbean = new bean();
        if(playerType == "competitive") {
            return new competitiveplayer(eventbean);
        }
        if(playerType == "casual") {
            return new casualplayer(eventbean);
        }
        if(playerType == "combination") {
            return new combinationplayer(eventbean);
        }
        return null;
    }
}