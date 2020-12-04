import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

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

    public void play(competitor[] comp) throws FileNotFoundException {
        int round = 0;

        //initialize excel sheet
        try (PrintWriter writer = new PrintWriter(new File("robin.csv"))) {
            StringBuilder sb = new StringBuilder();
            sb.append("id,");
            sb.append("Name");
            sb.append(',');
            sb.append("Winner(1 or 2),");
            sb.append(',');
            sb.append('\n');


            competitor tempArray[] = new competitor[comp.length];
            while (round < numrounds) {
                //System.out.println("Round: " + round);
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
                        //add to excel
                        sb.append("1");
                        sb.append(',');
                        sb.append(tempArray[i].getName());
                        sb.append(',');
                        sb.append('\n');
                        sb.append("2");
                        sb.append(',');
                        sb.append(tempArray[j].getName());
                        sb.append(',');
                        sb.append('\n');
                        sb.append('\n');

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
            writer.write(sb.toString());

            System.out.println("Round Robin Generated, download robin.csv to view match-ups");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Please upload results, Result Filename(or sample robinResult.csv): ");
        Scanner birdResult = new Scanner(System.in);
        String robinResult = birdResult.next();
        //read in results
        Scanner birdWinners = new Scanner(new File(robinResult));
        String input;
        String[] arrOfResult = new String[245];

        int stringCount = 0;
        birdWinners.useDelimiter(",");   //sets the delimiter pattern
        while (birdWinners.hasNext())  //returns a boolean value
        {
            input = birdWinners.next();
            if(stringCount>=3&&input.length() != 0){
                arrOfResult[stringCount-3] = input;
            }
            stringCount++;

            if(stringCount>242){
                break;
            }
        }
        birdWinners.close();  //closes the scanner*/
        //int outnum = 1;
        String winner;
        String loser;
        String[] tempFive = new String[5];
        for(int i = 0; i < arrOfResult.length -5; i = i+5){
            tempFive[0] = arrOfResult[i];
            tempFive[1] = arrOfResult[i+1];
            tempFive[2] = arrOfResult[i+2];
            tempFive[3] = arrOfResult[i+3];
            tempFive[4] = arrOfResult[i+4];

            if("1".equals(tempFive[2])){
                //winner is tempFive[1];
                winner = tempFive[1];
                loser = tempFive[4];
            }
            else{
                //winner is tempFive[4];
                winner = tempFive[4];
                loser = tempFive[1];
            }


            bean eventbean = new bean();
            competitor tempwinner = new competitor(eventbean);
            competitor temploser = new competitor(eventbean);
            //competitor tempArray[] = new competitor[comp.length];

            for(int j = 0; j < comp.length; j++) {

                //System.out.println("howdy+++"+ comp[j].getName()+"+++");

                if(comp[j].getName().equals(winner)) {
                    //System.out.println("howdy+++"+ comp[j].getName()+"+++");

                    tempwinner = comp[j];
                    //System.out.println("Player" +tempwinner.getName()+" defeated");

                }
                if(comp[j].getName().equals(loser)) {
                    temploser = comp[j];
                    //System.out.println("Player" +temploser.getName());

                }
            }
            tempwinner.addWin(temploser);
            temploser.addLoss(tempwinner);
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
