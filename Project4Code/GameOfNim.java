/**
 * 
 *
 * @author Michael Cummings
 * @version 2, 3.23.2020
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
        if (marblesInPile == 1) {
            int marblesToTake = 0;
        }
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
        int closestPowerOfTwo = 0;
        for (int i = 0; closestPowerOfTwo < (marblesInPile / 2); i++) {
            closestPowerOfTwo = (int)(Math.pow(2, i));
        }
        if (marblesInPile == (closestPowerOfTwo - 1)) { //FIXME: Only works for the closestPowerOfTwo
            dumbComputerTurn(); //if the size of the pile is already a power of two minus one, the computer will make a random move
        }
        else if (marblesInPile < 3){
            int marblesToTake = 1;
            computerTurnCount += 1;
        }
        else if (marblesInPile == 1) {
            int marblesToTake = 0;
        }
        else {
        int marblesToTake = (marblesInPile - closestPowerOfTwo) + 1; //computer will take enough marbles to make the size of the pile a power of 2 minus 1
        marblesInPile -= marblesToTake; //FIXME: Computer makes illegal moves by taking more than half the pile
        System.out.println("The computer took " + marblesToTake + " marbles from the pile. There are now " + marblesInPile + " marbles in the pile.");
        computerTurnCount += 1;
        }
    }
    
    /**
     * Simulates a game of Nim against a computer-controlled player
     */
    public void play() {
        marblesInPile = randomGenerator.nextInt(maxMarbles - minMarbles) + minMarbles;
        
        System.out.println("Game begins");
        System.out.println("Initially there are " + marblesInPile + " marbles in the pile");
        
        int turnDecide = randomGenerator.nextInt(2) + 1; //decides whether the human or computer goes first depending on whether a 1 or a 2 is generated
        int computerDecide = randomGenerator.nextInt(2) + 1; //decides between smart and dumb computer
        if (turnDecide % 2 == 1) {
            if (computerDecide % 2 == 1) {
                System.out.println("You are playing against the DUMB computer"); //Remove this message later, just need for testing
                System.out.println("You will go first.");
                while (marblesInPile > 1) {
                humanTurn();
                dumbComputerTurn();
                }
            }
            else {
                System.out.println("You are playing against the SMART computer"); //Remove this message later, just need for testing
                System.out.println("You will go first.");
                while (marblesInPile > 1) {
                humanTurn();
                smartComputerTurn();
                }
            }
        }
        else {
            if (computerDecide % 2 == 1) {
                System.out.println("You are playing against the DUMB computer"); //Remove this message later, just need for testing
                System.out.println("The computer will go first.");
                while (marblesInPile > 1) {
                dumbComputerTurn();
                humanTurn();
                }
            }
            else {
                System.out.println("You are playing against the SMART computer"); //Remove this message later, just need for testing
                System.out.println("The computer will go first.");
                while (marblesInPile > 1) {
                    smartComputerTurn();
                    humanTurn();
                }
            }
        }
        winConditions();
    }
}
