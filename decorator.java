class decorator extends player {
    public player decoratedPlayer;
    public decorator(bean sourcebean){ //DECORATOR - add sauce, filling, and toppings to rolls
        super(sourcebean);
    }
    public void addtoDecorate(player toDecorate) {
        decoratedPlayer = toDecorate;
    }
    public void addHandicap() {
        handicap = decoratedPlayer.handicap + 1;
        //name = decoratedPlayer.name+"*";
        decoratedPlayer.setName(decoratedPlayer.getName()+"*");
    }

}