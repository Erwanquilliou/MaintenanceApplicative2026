package trivia;

public class Player {
    String name;
    int place;
    int purse;
    Boolean inPenalty = false;
    public Player(String name,int place,int purse) {
        this.name = name;
        this.place = place;
        this.purse = purse;
    }
}
