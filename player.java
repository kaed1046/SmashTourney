public class player extends competitor{
    //private character character;
    protected String name;
    private decorator skill;
    private int seed;
    public int handicap;
    player (bean sourcebean) {
        super(sourcebean);
        handicap = 0;
    }
//    player (String name_) {
//        player.name = name_;
//        handicap = 0;
//    }
    public int getSeed() {
        return seed;
    }
    public void setSeed(int seed_) {
        seed = seed_;
    }
}