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
                    System.out.println(playerlist[j].getName() + " " + playerlist[j].getWins());
                    playerlist[j].setSeed(i + 1); //seed is order of orderedlist
                }
            }
        }
    }
    public void printBracket(competitor[] playerlist) {

    }
    public void generateBracket(competitor[] playerlist){
        competitor player1 = null;
        competitor player2 = null;
        if (playerlist.length%2 != 0){
            //player with highest seed gets a buy
            //implement decorator here
        }
        for (int i = 0; i < (playerlist.length)/2; i++){
            for(int j = 0; j < playerlist.length; j++){
                if(playerlist[j].getSeed() == (i+1)){
                    player1 = playerlist[j];
                }
                else if(playerlist[j].getSeed() == (playerlist.length-i)){
                    player2 = playerlist[j];
                }
            }
            System.out.println("Seed "+ (i+1)+ ": " + player1.getName()+ " vs. Seed "+ (playerlist.length-i) +": "+ player2.getName());

        }

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
        //Some stuff just to test before excel implementation
        /*String[] playerArray = new String[32];
        playerArray[0] = "Carmon Crothers";
        playerArray[1] = "Hortensia Hulett";
        playerArray[2] = "Idella Isley";
        playerArray[3] = "Jackqueline Jacoby";
        playerArray[4] = "Mariah Messerly";
        playerArray[5] = "Justin Jason";
        playerArray[6] = "Wonda Walley";
        playerArray[7] = "Minta Mcentyre";
        playerArray[8] = "Barbar Blandon";
        playerArray[9] = "Jame Jeffrey";
        playerArray[10] = "Retha Rodriquez";
        playerArray[11] = "Carmelo Crichton";
        playerArray[12] = "Margart Mccright";
        playerArray[13] = "Migdalia Marte";
        playerArray[14] = "Yolanda Yingling";
        playerArray[15] = "Carman Chaloux";
        playerArray[16] = "Jenelle Jines";
        playerArray[17] = "Melda Marchi";
        playerArray[18] = "Jennette Joynes";
        playerArray[19] = "Eloise Epperson";
        playerArray[20] = "Verena Voisin";
        playerArray[21] = "Emilie Everman;";
        playerArray[22] = "Lara Luedtke";
        playerArray[23] = "George Gebhart";
        playerArray[24] = "Lashanda Longoria";
        playerArray[25] = "Celine Cintron";
        playerArray[26] = "Hang Hartle";
        playerArray[27] = "Leonard Lover";
        playerArray[28] = "Hector Harker";
        playerArray[29] = "Lorrie Lodge";
        playerArray[30] = "Alfredo Ake";
        playerArray[31] = "Mikki Mata";*/
        
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


            //set flag for now, testing phase

            if (gameMode == "solo"){
                //solo implementation of round robin to create 1v1 bracket
                System.out.println("Casual Round Robin begin");
                fatBird.play(casualPlayerList);
                System.out.println("Casual Round Robin end");
                fatBird.displayStats(casualPlayerList);
                b.calculateSeed(casualPlayerList, fatBird.getNumrounds());
                eventbean.makeEvent("done"); //now rr is over

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
            }
        }

    }
}