import java.util.Random;
import java.util.Scanner;

public class roundrobin {
    public int numrounds;
    public roundrobin() {}
    public void setNumrounds(int rounds) {
        numrounds = rounds;
    }
    public int getNumrounds() {
        return numrounds;
    }
    private static void shuffleArrays(competitor[] array1, competitor[] array2)
    {
        int index;
        competitor temp;
        Random random = new Random();
        for (int i = array1.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array1[index];
            array1[index] = array1[i];
            array2[index] = array2[i];
            array1[i] = temp;
            array2[i] = temp;
        }
    } //adapted from stackOverflow: https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array

    public void play(competitor[] comp) {
        int round = 0;
//        for(int i = 0; i < comp.length; i++) {
//            System.out.println(i);
//            System.out.println(comp[i].getName());
//        }
        competitor tempArray[] = new competitor[comp.length];
        while (round < numrounds) {
            System.out.println("Round: " + round);
            for(int i = 0; i < comp.length; i++) {
                tempArray[i] = comp[i];
            }
            shuffleArrays(tempArray, comp);
            for(int i = 0; i < tempArray.length; i++) {
                //System.out.println("i = " + i);
                int j = i;
                boolean played = true;
                if(tempArray[i] != null) {
                    while(played == true && j < 31) { //go until we find someone not played against
                        j++;
                        if(tempArray[j] != null){
                            //System.out.println("j = " + j);
                            played = tempArray[i].checkIfPlayed(tempArray[j]);
                            //System.out.println("played = " + played);
                        }
                    }
                    System.out.println(tempArray[i].getName() + " vs " + tempArray[j].getName());
                    Scanner sc = new Scanner(System.in); //take user input for winner
                    int winner = 0;
                    while(winner != 1 && winner != 2) {
                        System.out.println("Winner: (1 or 2)");
                        winner = sc.nextInt();
                    }
                    if(winner == 1) {
                        comp[i].addWin(comp[j]);
                        comp[j].addLoss(comp[i]);
                    }
                    else {
                        comp[i].addLoss(comp[j]);
                        comp[j].addWin(comp[i]);
                    }
                    comp[i].addCompPlayed(comp[j]);
                    comp[j].addCompPlayed(comp[i]);
                    tempArray[i] = null;
                    tempArray[j] = null;
                    played = false;
                }
            }
            round++;
        }
        for(int i = 0; i < comp.length; i++) {
            comp[i].resetCompPlayed();
        }
    }

    public void displayStats(competitor[] comp) {
        for(int i = 0; i < comp.length; i++) {
            System.out.println(comp[i].getName());
            System.out.println("Wins: " + comp[i].getWins());
            for(int j = 0; j < comp[i].getWins(); j++) {
                System.out.println(comp[i].getWinList(j).getName());
            }
            System.out.println("Losses: " + comp[i].getLosses());
            for(int j = 0; j < comp[i].getLosses(); j++) {
                System.out.println(comp[i].getLossList(j).getName());
            }
            System.out.println("");
        }
    }
}
