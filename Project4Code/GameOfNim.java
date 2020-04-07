/**
 * 
 *
 * @author Michael Cummings
 * @version 4, 4.7.2020
 */
import java.util.Random;
import java.util.Scanner;

public class GameOfNim {
    private int minMarbles, maxMarbles, marblesInPile;
    private boolean isHumansTurn, isComputersTurn;
    Random randomGenerator = new Random();
    
    /**
     * Constructor for GameOfNim
     * @param min is the minimum number of marbles that will be in the pile
     * @param max is the maximum number of marbles that will be in the pile
     */
    public GameOfNim(int min, int max) {
        minMarbles = min;
        maxMarbles = max;
        marblesInPile = randomGenerator.nextInt(maxMarbles - minMarbles) + minMarbles;
    }
    
    /**
     * Defines the win conditions for the game
     */
    private void winConditions() {
        if (isComputersTurn && !isHumansTurn) {
            System.out.println("You win!");
        }
        else if (isHumansTurn && !isComputersTurn) {
            System.out.println("The computer wins!");
        }
    }
    
    /**
     * Prompts the user to enter the number of marbles they want to take from the pile
     */
    private void humanTurn() {
        isHumansTurn = true;
        Scanner input = new Scanner(System.in);
        if (marblesInPile == 1) { //player will skip turn and lose if marble pile is 1 at start of turn
            int marblesToTake = 0; 
        }
        else {
            while(true){
                System.out.println("Please enter how many marbles you want to take from the pile: ");
                int marblesToTake = input.nextInt();
                if (marblesToTake > marblesInPile / 2 || marblesToTake < 1) { //player is only allowed to take at most half the marbles in the pile
                    System.out.println("Please enter a valid number of marbles to take (number must be a positive integer, and not more than half the number of marbles in the pile)");
                    continue;
                }
                marblesInPile -= marblesToTake;
                System.out.println("You took " + marblesToTake + " marbles from the pile. There are now " + marblesInPile + " marbles in the pile.");
                isHumansTurn = false;
                if (marblesInPile == 1) {
                    isComputersTurn = true;
                    winConditions();
                }
                else {break;}
            }
        }
    }
    
    /**
     * A computer-controlled player that takes a random number less than half of the total marbles in the pile every turn
     */
    private void dumbComputerTurn() {
        isComputersTurn = true;
        if (marblesInPile == 1) { //if there is only one marble in the pile the computer will skip their turn
            int marblesToTake = 0;
        }
        else {
            int marblesToTake = randomGenerator.nextInt(marblesInPile / 2) + 1;
            marblesInPile -= marblesToTake;
            System.out.println("The computer took " + marblesToTake + " marbles from the pile. There are now " + marblesInPile + " marbles in the pile.");
            isComputersTurn = false;
            if (marblesInPile == 1) {
                isHumansTurn = true;
                winConditions();
            }
        }
    }
    
    /**
     * A computer-controlled player that takes enough marbles from the pile each turn to make the size of the pile a power of 2, minus 1.
     */
    private void smartComputerTurn() {
        isComputersTurn = true;
        int closestPowerOfTwo = 0;
        for (int i = 0; closestPowerOfTwo < (marblesInPile / 2); i++) {
            closestPowerOfTwo = (int)(Math.pow(2, i));
        }
        if (marblesInPile == 1) {
            int marblesToTake = 0;
        }
        else if (marblesInPile <= 3){
            int marblesToTake = 1;
            marblesInPile -= marblesToTake;
            System.out.println("The computer took " + marblesToTake + " marbles from the pile. There are now " + marblesInPile + " marbles in the pile.");
            isComputersTurn = false;
            if (marblesInPile == 1) {
                isHumansTurn = true;
                winConditions();
            }
        }
        else {
            int marblesToTake = (marblesInPile - closestPowerOfTwo) + 1; //computer will take enough marbles to make the size of the pile a power of 2 minus 1
            if (marblesToTake > (marblesInPile / 2)) {
                marblesToTake = (marblesInPile - (closestPowerOfTwo * 2) + 1); //have to put in this loop so computer doesn't cheat by taking more than half the pile.
            }
            if (marblesToTake == 0) {
                marblesToTake = randomGenerator.nextInt(marblesInPile / 2) + 1; //Computer throws a tantrum and refuses to take any marbles if the size of the pile is already a power of two minus one, so this if statement makes it take a random number of marbles
            }
            marblesInPile -= marblesToTake;
            System.out.println("The computer took " + marblesToTake + " marbles from the pile. There are now " + marblesInPile + " marbles in the pile.");
            isComputersTurn = false;
            if (marblesInPile == 1) {
                isHumansTurn = true;
                winConditions();
            }
        }
    }
    
    /**
     * Simulates a game of Nim against a computer-controlled player
     */
    public void play() {
        int marblePile = marblesInPile;
        isHumansTurn = false;
        isComputersTurn = false; //initializing turn booleans to false
        
        System.out.println("Game begins");
        System.out.println("Initially there are " + marblePile + " marbles in the pile");
        
        int turnDecide = randomGenerator.nextInt(2) + 1; //decides whether the human or computer goes first depending on whether a 1 or a 2 is generated
        int computerDecide = randomGenerator.nextInt(2) + 1; //decides between smart and dumb computer
        if (turnDecide % 2 == 1) {
            if (computerDecide % 2 == 1) {
                System.out.println("You are playing against the DUMB computer"); //Remove this message later, just need for testing
                System.out.println("You will go first.");
                while (marblePile >= 1) {
                    humanTurn();
                    dumbComputerTurn();
                }
            }
            else {
                System.out.println("You are playing against the SMART computer"); //Remove this message later, just need for testing
                System.out.println("You will go first.");
                while (marblePile >= 1) {
                    humanTurn();
                    smartComputerTurn();
                }
            }
        }
        else {
            if (computerDecide % 2 == 1) {
                System.out.println("You are playing against the DUMB computer"); //Remove this message later, just need for testing
                System.out.println("The computer will go first.");
                while (marblePile >= 1) {
                    dumbComputerTurn();
                    humanTurn();
                }
            }
            else {
                System.out.println("You are playing against the SMART computer"); //Remove this message later, just need for testing
                System.out.println("The computer will go first.");
                while (marblePile >= 1) {
                    smartComputerTurn();
                    humanTurn();
                }
            }
        }
    }
}
