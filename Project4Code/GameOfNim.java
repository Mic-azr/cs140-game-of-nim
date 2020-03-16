/**
 * 
 *
 * @author Michael Cummings
 * @version 1, 3.10.2020
 */
import java.util.Random;
import java.util.Scanner;

public class GameOfNim {
    private int minMarbles, maxMarbles, marblesInPile, humanTurnCount, computerTurnCount;
    Random randomGenerator = new Random();
    
    /**
     * Constructor for GameOfNim
     * @param min is the minimum number of marbles that will be in the pile
     * @param max is the maximum number of marbles that will be in the pile
     */
    public GameOfNim(int min, int max) {
        minMarbles = min;
        maxMarbles = max;
    }
    
    /**
     * Defines the win conditions for the game
     */
   private void winConditions() {
        if (marblesInPile == 1) {
            if (humanTurnCount > computerTurnCount) {
                System.out.println("You win!");
            }
            else {
                System.out.println("The computer wins!");
            }
        }
    }
    
    /**
     * Prompts the user to enter the number of marbles they want to take from the pile
     */
    private void humanTurn() {
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.println("Please enter how many marbles you want to take from the pile: ");
            int marblesToTake = input.nextInt();
            if (marblesToTake > marblesInPile / 2 || marblesToTake < 1) { //player is only allowed to take at most half the marbles in the pile
                System.out.println("Please enter a valid number of marbles to take (number must be a positive integer, and not more than half the number of marbles in the pile)");
                continue;
            }
        marblesInPile -= marblesToTake;
        System.out.println("You took " + marblesToTake + " marbles from the pile. There are now " + marblesInPile + " marbles in the pile.");
        humanTurnCount += 1;
        break;
        }
    }
    
    /**
     * 
     */
    private void dumbComputerTurn() {
        if (marblesInPile == 1) {
            int marblesToTake = 0;
        }
        else {
            int marblesToTake = randomGenerator.nextInt(marblesInPile / 2) + 1;
            marblesInPile -= marblesToTake;
            System.out.println("The computer took " + marblesToTake + " marbles from the pile. There are now " + marblesInPile + " marbles in the pile.");
            computerTurnCount += 1;
        }
    }
    
    /**
     * 
     */
    private void smartComputerTurn() {
    
    }
    
    /**
     * Simulates a game of Nim against a computer-controlled player
     */
    public void play() {
        marblesInPile = randomGenerator.nextInt(maxMarbles) + minMarbles;
        
        System.out.println("Game begins");
        System.out.println("Initially there are " + marblesInPile + " marbles in the pile");
        
        //int turnDecide = randomGenerator.nextInt(1); //decides whether the human or computer goes first depending on whether a 0 or a 1 is generated
        //if (turnDecide == 1) {
        
        while (marblesInPile > 1) {
           humanTurn();
           dumbComputerTurn();
        }
            
        winConditions();
    }
        
        

        
    //}
}
