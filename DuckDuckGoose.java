
package com.mycompany.duckduckgoose;

import java.util.Random;
import java.util.Scanner;

/**
 *This class is a game called Duck Duck Goose which takes the number of players
 * and their names along with the number of rounds to be played as inputs 
 * assuming that they would be placed in a circle. The last person is the IT 
 * person, the person who calls duck and goose. A random number will be 
 * generated to decide who will be called goose, and once called,
 * the goose and the IT person will move anticlockwise and clockwise 
 * respectively along the circle of players until they reach the goose's initial
 * position. The speed is also decided randomly for both and the one who reaches 
 * first is the winner and the loser becomes the new IT person.
 * 
 * @author Hrittija Bhattacharjee
 * ID: 1690727
 * Date: 19 March 2023
 * AUSCI 235
 */
public class DuckDuckGoose {
    static DCircLinkList <String> list = new DCircLinkList();
    static Random random = new Random();
    static int numRounds;
    static DNode <String> it_Player;
    public static void main(String[] args) {
        playerEntry();
        for (int rounds = 0; rounds < numRounds; rounds++){
            roundPlay(rounds);
            
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("Done. Final State.\n" +
        "Game circle: "+list+"\n" +
        "It-Person: "+it_Player.getElement());
        System.out.println("=======================================");
    }
/**
 * This method takes the inputs by scanner for the number of players, their
 * names and the rounds to be played and stores the player names in the list
 * using the addLast method.
 */
public static void playerEntry(){
        Scanner input = new Scanner(System.in);
        System.out.println("How many players? ");
        int numPlayers = input.nextInt();
        input.nextLine();
        for (int i = 1; i < numPlayers; i++) {
            System.out.println("Please enter Player "+ i +"'s name:");
            String name = input.nextLine();
            list.addLast(name);
        }
        //This input is for the last player who will be the it person.
        System.out.println("Please enter Player "+(numPlayers)+"'s name:");
        String itPlayer_name = input.nextLine();
        it_Player = new DNode(itPlayer_name);
        System.out.println("How many rounds?");
        numRounds=input.nextInt();

        
        
}
/**
 * This method generates a random and the IT player keeps calling the players
 * duck until that random number and the next one is the goose.It prints the 
 * duck players and the goose player and the present circle.
 * @return the node of the goose player
 */
public static DNode<String> duckduckGooseCall(){
    int randomNumber = random.nextInt(20) + 1;
    System.out.println("Random number generated is: " + randomNumber);
    for(int i = 0; i < randomNumber; i++){
        System.out.print(list.getFirstElement() + " duck; ");
        list.rotateCCW();
    }
    DNode <String> goosePlayer = list.removeFirstNode();
    System.out.println(goosePlayer.getElement() + " GOOSE");
    System.out.println();
    System.out.println("Up jumps:  " + goosePlayer.getElement());
    System.out.println("Game Circle:   " + list);
    return goosePlayer;
}
/**
 * This method prints the rounds and generates the two random numbers for the 
 * IT player and the goose player so that they reach the final position.
 * @param rounds for the number of rounds to be played by this group of people
 */
public static void roundPlay(int rounds){
    System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
    System.out.println("Round " + rounds);
    System.out.println("Game Circle:   " + list);
    System.out.println("It-Person: " + it_Player.getElement());
    System.out.println("=======================================");
    DNode <String> goose_Player = duckduckGooseCall();
    DNode <String> itPosition = list.getFirstNode(); 
    DNode <String> goosePosition = list.getLastNode();
    int winner=-1;
    while(winner==-1){
        int gooseNum = random.nextInt(list.getSize()) + 1;
        int itNum = random.nextInt(list.getSize()) + 1;
        System.out.println("  **Speeds:   It-Person "+itNum+", Goose " + 
                gooseNum);
        if (itNum > gooseNum){
            System.out.print("     It-Person running past: ");
            itPosition=playerMovement(itPosition, itNum,1);
            System.out.print("     Goose running past:     ");
            goosePosition=
                   playerMovement(goosePosition, gooseNum,0);
     
            
        }
        else{
            System.out.print("     Goose running past:     ");
            goosePosition=
                   playerMovement(goosePosition, gooseNum,0);
            System.out.print("     It-Person running past: ");
            itPosition=playerMovement(itPosition, itNum,1);
   
            
        }
        
        winner = getWinner(itPosition,goosePosition,itNum,
                gooseNum);
        
        
    }
    it_Player=roundWinner(winner,goose_Player,it_Player);

}
/**
 * This method takes the current position of the player along with the random 
 * number that has been generated for it and prints the players/nodes they are 
 * crossing and changes the current position each time.
 * @param currentPos the present position of the player(IT or Goose)
 * @param moveNum random number generated for the player whose current position 
 * has been given
 * @param dir the direction of the player to be run, 0 for the goose and 1 for 
 * the IT person
 * @return the current position after the movement
 */
public static DNode<String> playerMovement(DNode<String> currentPos,
        int moveNum,int dir )
{
    for(int i = 0; i < moveNum;i++){
        System.out.print(currentPos.getElement()+" ");
        if (dir == 0){
            currentPos = currentPos.getPrevious();
            if (currentPos==list.getLastNode()){
                break;
            }
        }
        else{
            currentPos = currentPos.getNext();
            if (currentPos==list.getFirstNode()){
                break;
            }
        }
        
    }
    System.out.println();
    return currentPos;    
}

/**
 * This method takes the current positions of the it person and goose and 
 * determines if they have reached their final destination and who reaches first
 * or if they need more random numbers to reach there.
 * @param itPosition the node position of the it player while moving
 * @param goosePosition the node position of the goose player while moving
 * @param itRandom the random number generated for it person's movement
 * @param gooseRandom the random number generated for the goose player movement
 * @return integer value of 1(if goosePlayer reaches first), 0( if IT person
 * reaches first) and -1(if they still need another set of random numbers to 
 * reach the main destination.
 */
public static int getWinner(DNode<String> itPosition, DNode<String>goosePosition
        ,int itRandom, int gooseRandom){
    if (itRandom > gooseRandom){
        if (itPosition == list.getFirstNode()){
            return 0;
        }
        else if(goosePosition == list.getLastNode()){
            return 1;
        }
        
    }
    else{
        if (goosePosition == list.getLastNode()){
            return 1;
        }
        else if(itPosition == list.getFirstNode()){
            return 0;
        }
        
    }
    return -1;
}
/**
 * This method prints the winning message for the winner of a round and add the 
 * winner to the game circle again
 * @param winnerNum if the number is 1, then goose is the winner and if 0 the 
 * the IT person is the winner for that round
 * @param goose_Player node for the goose player
 * @param it_Player node for the IT player
 * @return the node for the player who lost so that it can be used for the new
 * IT person.
 */
public static DNode<String> roundWinner(int winnerNum, 
        DNode <String> goose_Player,DNode <String> it_Player){
    System.out.println();
    if (winnerNum==1){
        System.out.println("Goose ("+ goose_Player.getElement()+") wins");
        list.addNodeAsFirst(goose_Player); /* add the goose player to the
        game circle list again.
        */
        return it_Player;
    }
    else{
        System.out.println("It-person ("+ it_Player.getElement()+") wins");
        list.addNodeAsFirst(it_Player);
        return goose_Player;

    }
}
}