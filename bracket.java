import java.io.File;

class bracket {
    private int numPlayers;
    private int numTeams;
    bracket () {}

    public static void main(String[] args) {
        //Some stuff just to test before excel implementation
        String[] playerArray = new String[32];
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
        playerArray[31] = "Mikki Mata";
        
        factory playerFactory = new factory();
        roundrobin fatBird = new roundrobin();


        String gameType = "casual";
        int numPlayer = 32;
        fatBird.setNumrounds(3);
        //set flag for casual game for now
        //excel implementation will add user input for game type

        if (gameType == "casual"){
            //loop for pre-excel implementation
            player[] casualPlayerList = new player[numPlayer];
            for(int i = 0; i < numPlayer; i++){
                casualPlayerList[i] = playerFactory.createPlayer("casual");
                casualPlayerList[i].setName(playerArray[i]);
                System.out.println(i +" " +  casualPlayerList[i].getName());


            }//create players, factory implementation
            for(int i = 0; i < numPlayer; i++){
                //casualPlayerList[i] = playerFactory.createPlayer("casual", playerArray[i]);
                //System.out.println(playerArray[i]);
                System.out.println(i + " " + casualPlayerList[i].getName());
            }//create players, factory implementation
            String gameMode = "solo";
            //set flag for now, testing phase
            if (gameMode == "solo"){
                //solo implementation of round robin to create 1v1 bracket
                System.out.println("Casual Round Robin begin");
                fatBird.play(casualPlayerList);
                System.out.println("Casual Round Robin end");
                fatBird.displayStats(casualPlayerList);
            }
        }
        else if (gameType == "competitive"){
            //loop for pre-excel implementation
            player[] competitivePlayerList = new player[numPlayer];
            for(int i = 0; i < numPlayer; i++){
                competitivePlayerList[i] = playerFactory.createPlayer("competitive");
                competitivePlayerList[i].setName(playerArray[i]);
            }//create players, factory implementation
        }

    }
}