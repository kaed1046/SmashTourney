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
    private static void shuffleArray(team[] array)
    {
        int index;
        team temp;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    } //adapted from stackOverflow: https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array

    public void play(team[] teams) {
        int round = 0;
        team tempArray[] = new team[teams.length];
        while (round < numrounds) {
            for(int i = 0; i < teams.length; i++) {
                tempArray[i] = teams[i];
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
                        teams[i].addWin();
                        teams[j].addLoss();
                    }
                    else {
                        teams[i].addLoss();
                        teams[j].addWin();
                    }
                    teams[i].addTeamPlayed(teams[j]);
                    teams[j].addTeamPlayed(teams[i]);
                    tempArray[i] = null;
                    tempArray[j] = null;
                }
            }
        }
        for(int i = 0; i < teams.length; i++) {
            teams[i].resetTeamsPlayed();
        }
    }
}
