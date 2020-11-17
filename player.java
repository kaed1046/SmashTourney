public class player extends competitor{
    //private character character;
    static protected String name;
    private decorator skill;
    private int seed;
    private observer observer;
    player () {}
    player (String name_) {
        player.name = name_;
    }
    public int getSeed() {
        return seed;
    }
    public void setSeed(int seed_) {
        seed = seed_;
    }
}