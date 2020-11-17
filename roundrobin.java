import java.util.Random;
import java.util.Scanner;

public class roundrobin {
    private int numrounds;
    public roundrobin() {}
    public void setNumrounds(int rounds) {
        numrounds = rounds;
    }
    public int getNumrounds() {
        return numrounds;
    }
    private static void shuffleArray(competitor[] array)
    {
        int index;
        competitor temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    } //adapted from stackOverflow: https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array

    public void play(competitor[] comp) {
        int round = 0;
        competitor tempArray[] = new competitor[comp.length];
        while (round < numrounds) {
            for(int i = 0; i < comp.length; i++) {
                tempArray[i] = comp[i];
            }
            shuffleArray(tempArray);
            for(int i = 0; i < tempArray.length; i++) {
                int j = i;
                boolean played = true;
                if(tempArray[i] != null) {
                    while(played == true) { //go until we find someone not played against
                        j++;
                        if(tempArray[j] != null){
                            played = tempArray[i].checkIfPlayed(tempArray[j]);
                        }
                    }
                    System.out.println(tempArray[i].getName() + " vs " + tempArray[j].getName());
                    Scanner sc = new Scanner(System.in); //take user input for # of days
                    int winner = 0;
                    while(winner != 1 && winner != 2) {
                        System.out.println("Winner: (1 or 2)");
                        winner = sc.nextInt();
                    }
                    if(winner == 1) {
                        comp[i].addWin();
                        comp[j].addLoss();
                    }
                    else {
                        comp[i].addLoss();
                        comp[j].addWin();
                    }
                    comp[i].addCompPlayed(comp[j]);
                    comp[j].addCompPlayed(comp[i]);
                    tempArray[i] = null;
                    tempArray[j] = null;
                }
            }
            round++;
        }
        for(int i = 0; i < comp.length; i++) {
            comp[i].resetCompPlayed();
        }
    }
}
