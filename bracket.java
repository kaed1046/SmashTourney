import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

class bracket {
    private int numPlayers;
    private int numTeams;
    bracket () {}

    public void calculateSeed(competitor[] playerlist, int numrounds) {
        competitor samewinslist[] = new competitor[playerlist.length];
        competitor orderedlist[] = new competitor[playerlist.length];
        int orderedidx = 0;
        int numwins = numrounds;
        int sameidx = 0;
        while(numwins >= 0) {
            //getting all ppl with same # wins
            sameidx = 0;
            for(int i = 0; i < playerlist.length; i++) {
                if(playerlist[i].getWins() == numwins) {
                    samewinslist[sameidx] = playerlist[i];
                    sameidx++;
                }
            }
            if(sameidx == 1) { //1 person in list
                orderedlist[orderedidx] = samewinslist[0];
                orderedidx++;
            }
            else {
                //sum # wins of all combined people they beat to order them
                int totBeatWins = 0;
                int maxtot = -1;
                int maxidx = -1;
                int sameleft = sameidx;
                bean eventbean = new bean();
                competitor temp_comp = new competitor(eventbean);
                while(sameleft > 0) {
                    totBeatWins = 0;
                    maxidx = -1;
                    maxtot = -1;
                    for(int i = 0; i < sameidx; i++) {
                        if(samewinslist[i].seeded == false){
                            for(int j = 0; j < samewinslist[i].getWins(); j++) {
                                totBeatWins += samewinslist[i].getWinList(j).getWins();
                            }
                            //System.out.println(totBeatWins);
                            if(totBeatWins > maxtot) {
                                maxtot = totBeatWins;
                                temp_comp = samewinslist[i];
                                maxidx = i;
                            }
                            totBeatWins = 0;
                        }
                    }
                    orderedlist[orderedidx] = temp_comp;
                    //System.out.println(orderedlist[orderedidx].getName() + " " + maxtot);
                    orderedidx++;
                    samewinslist[maxidx].seeded = true;
                    sameleft--;
                }
            }
            numwins--;
            samewinslist = new competitor[playerlist.length];
        }
        for(int i = 0; i < orderedlist.length; i++) {
            for(int j = 0; j < playerlist.length; j++) {
                if(orderedlist[i] == playerlist[j]) {
                    //System.out.println(playerlist[j].getName() + " " + playerlist[j].getWins());
                    playerlist[j].setSeed(i + 1); //seed is order of orderedlist
                }
            }
        }
        for(int i = 0; i < playerlist.length; i++) {
            playerlist[i] = orderedlist[i];
            //System.out.println("Seed"+i+": "+playerlist[i].name);
        }
    }
    public void printBracket(competitor[] playerlist) {

    }
    public void generateBracket(competitor[] playerlist){
        bean eventbean = new bean();
        int numCompet = playerlist.length;
        int roundNum = 1;
        Scanner scoot = new Scanner(System.in);
        int movesOn;

        competitor player1 = null;
        competitor player2 = null;
        while (numCompet>=2){
            int counter = 0;
            int j = 0;
            int l = numCompet-1;
            int k = 0;
            System.out.println("Round " + roundNum);
            roundNum++;
            competitor[] tempplayerlist = new competitor[numCompet / 2];
            while(counter < numCompet/2) {

                //find player 1

                for (int i = j; i < numCompet / 2; i++) {
                    if (playerlist[i].getDead() == false) {
                        player1 = playerlist[i];
                        break;
                    }
                }
                //find player 2
                for (int i = l; i >= numCompet / 2; i--) {

                    if (playerlist[i].getDead() == false) {
                        player2 = playerlist[i];
                        break;
                    }
                }
                counter++;
                System.out.println(player1.getName() + " vs " + player2.getName());
                System.out.println("Winner: " + player1.getName() + "(1) or " + player2.getName() + " (2)");
                movesOn = scoot.nextInt();
                if (movesOn == 1) {
                    tempplayerlist[k] = player1;
                    player2.setDead(true);
                } else {
                    tempplayerlist[k] = player2;
                    player1.setDead(true);
                }
                k++;
                j++;
                l--;
            }
            numCompet = numCompet/2;
            for (int i = 0; i < numCompet; i++){
                playerlist[i] = tempplayerlist[i];
            }
            for (int i = 31; i >= numCompet; i--) {
                playerlist[i] = null;
            }
        }
        System.out.println("Congratulations, "+playerlist[0].getName()+ "! You are the new champion!");

    }

    public static void main(String[] args) throws FileNotFoundException {
        //parsing a CSV file into Scanner class constructor
        bracket b = new bracket();
        Scanner sc = new Scanner(new File("CompetitivePlayers.csv"));
        String line;
        String[] playerArray = new String[32];
        int stringCount = 0;
        sc.useDelimiter(",");   //sets the delimiter pattern
        while (sc.hasNext())  //returns a boolean value
        {
            line = sc.next();
            if(line.length() != 0){
                if(stringCount <= 3){
                    stringCount++;
                }
                else if ((line.charAt(0) >= 65 && line.charAt(0) < 90)||(line.charAt(0) >= 97 && line.charAt(0) < 123) ){
                    playerArray[stringCount-4] = line;
                    //print statement for bookkeeping
                    System.out.print("\nPlayer" + (stringCount-3)+ ": " + playerArray[stringCount-4]);  //find and returns the next complete token from this scanner
                    stringCount++;
                }
            }
        }
        System.out.print("\n");
        sc.close();  //closes the scanner
        
        factory playerFactory = new factory();
        roundrobin fatBird = new roundrobin();

//        Scanner scan = new Scanner(System.in); //take user input for gametype
//        int gtype = 0;
//        String gameType;
//        while(gtype != 1 && gtype != 2) {
//            System.out.println("Game Type: Casual (1) or Competitive (2)");
//            gtype = scan.nextInt();
//        }
//        if(gtype == 1) {
//            gameType = "casual";
//        }
//        else {
//            gameType = "competitive";
//        }

//        int gmode = 0;
//        String gameMode;
//        while(gmode != 1 && gmode != 2) {
//            System.out.println("Game Mode: Solo (1) or Team (2)");
//            gtype = scan.nextInt();
//        }
//        if(gtype == 1) {
//            gameMode = "solo";
//        }
//        else {
//            gameMode = "team";
//        }

//        Scanner scanrounds = new Scanner(System.in); //take user input for winner
//        int numrounds = 0;
//        while(numrounds < 1 && numrounds > 20) {
//            System.out.println("Number of Rounds in Round Robin: Between 1 and 20");
//            numrounds = scanrounds.nextInt();
//        }
        int numrounds = 3;
        String gameType = "casual";
        fatBird.setNumrounds(numrounds);

        int numPlayer = 32;

        //set flag for casual game for now

        bean eventbean = new bean();
        eventbean.makeEvent("started"); //rr not over
        String gameMode = "solo";

        if (gameType == "casual"){
            //loop for pre-excel implementation
            player[] casualPlayerList = new player[numPlayer];
            for(int i = 0; i < numPlayer; i++){
                casualPlayerList[i] = playerFactory.createPlayer("casual");
                casualPlayerList[i].setName(playerArray[i]);
                eventbean.addPropertyChangeListener(casualPlayerList[i]);
                //System.out.println(i +" " +  casualPlayerList[i].getName());
            }//create players, factory implementation

            if (gameMode == "solo"){
                //solo implementation of round robin to create 1v1 bracket
                System.out.println("Casual Round Robin begin");
                fatBird.play(casualPlayerList);
                System.out.println("Casual Round Robin end");
                fatBird.displayStats(casualPlayerList);
                b.calculateSeed(casualPlayerList, fatBird.getNumrounds());
                eventbean.makeEvent("done"); //now rr is over

                for(int i = 0; i < casualPlayerList.length; i++) {
                    //handicap top 3 seeds
                    if(casualPlayerList[i].getSeed() <= 3) {
                        decorator decoratedPlayer = new decorator(eventbean);
                        decoratedPlayer.addtoDecorate(casualPlayerList[i]);
                        decoratedPlayer.addHandicap();
                    }
                }

                b.generateBracket(casualPlayerList);
            }
        }
        else if (gameType == "competitive"){
            player[] competitivePlayerList = new player[numPlayer];
            for(int i = 0; i < numPlayer; i++){
                competitivePlayerList[i] = playerFactory.createPlayer("competitive");
                competitivePlayerList[i].setName(playerArray[i]);
                eventbean.addPropertyChangeListener(competitivePlayerList[i]);
            }//create players, factory implementation
            if (gameMode == "solo"){
                //solo implementation of round robin to create 1v1 bracket
                System.out.println("Casual Round Robin begin");
                fatBird.play(competitivePlayerList);
                System.out.println("Casual Round Robin end");
                fatBird.displayStats(competitivePlayerList);
                b.calculateSeed(competitivePlayerList, fatBird.getNumrounds());
                eventbean.makeEvent("done"); //now rr is over
                b.generateBracket(competitivePlayerList);

            }

        }

    }
}