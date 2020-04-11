/**
 * A program that simulates a game of Nim, a game where two players take marbles out of a pile one after the other.
 * The loser of the game is the player whose turn it is when there is only one marble remaining in the pile.
 *
 * @author Michael Cummings
 * @version 6, 4.10.2020
 */
import java.util.Random;
import java.util.Scanner;

public class GameOfNim {
    private int marblesInPile;
    private boolean isHumansTurn;
    private boolean smartComputerMode;
    Random randomGenerator = new Random();
    Scanner input = new Scanner(System.in);
    
    /**
     * Constructor for GameOfNim
     * @param min is the minimum number of marbles that will be in the pile
     * @param max is the maximum number of marbles that will be in the pile
     */
    public GameOfNim(int min, int max) {
        marblesInPile = randomGenerator.nextInt(max - min + 1) + min;
        int num = randomGenerator.nextInt(2);
        if (num == 0) {
            isHumansTurn = true;
        }
        else {isHumansTurn = false;}
        num = randomGenerator.nextInt(2);
        if (num == 0) {
            smartComputerMode = true;
        }
        else {smartComputerMode = false;}
    }
    
    /**
     * Prompts the user to enter the number of marbles they want to take from the pile
     */
    private void humanTurn() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter how many marbles you want to take from the pile: ");
        int marblesToTake = input.nextInt();
        if ((marblesToTake > marblesInPile / 2 || marblesToTake < 1)) { 
            do { //player is only allowed to take at most half the marbles in the pile
                System.out.println("Please enter a valid number of marbles to take (number must be a positive integer, and not more than half the number of marbles in the pile)");
                marblesToTake = input.nextInt();
            }
            while ((marblesToTake > marblesInPile / 2 || marblesToTake < 1));
        }
        marblesInPile -= marblesToTake;
        System.out.println("You took " + marblesToTake + " marbles from the pile. There are now " + marblesInPile + " marbles in the pile.");
        isHumansTurn = false;
    }
    
    /**
     * A computer-controlled player that takes a random number less than half of the total marbles in the pile every turn
     */
    private void dumbComputerTurn() {
        int marblesToTake = randomGenerator.nextInt(marblesInPile / 2) + 1;
        marblesInPile -= marblesToTake;
        System.out.println("The computer took " + marblesToTake + " marbles from the pile. There are now " + marblesInPile + " marbles in the pile.");
        isHumansTurn = true;
    }
    
    /**
     * A computer-controlled player that takes enough marbles from the pile each turn to make the size of the pile a power of 2, minus 1.
     */
    private void smartComputerTurn() {
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
        }
        isHumansTurn = true;
    }
    
    /**
     * Simulates a game of Nim against a computer-controlled player
     */
    public void play() {
        System.out.println("Game begins");
        System.out.println("Initially there are " + marblesInPile + " marbles in the pile");
        
        if (!smartComputerMode) { //dumb computer mode
            //System.out.println("You are playing against the DUMB computer"); //Remove this message later, just need for testing
            if (isHumansTurn) {
                System.out.println("You will go first.");
            }
            else {
                System.out.println("The computer will go first.");
            }
            while (true) {
                if (isHumansTurn) {
                    humanTurn();
                    if(marblesInPile == 1) { //checking size of pile in between turns
                        System.out.println("Human Wins");
                        return;
                    }
                }
                else { //computers turn
                    dumbComputerTurn();
                    if (marblesInPile == 1) {
                        System.out.println("Computer Wins");
                        return;
                    }
                }
            }
        }
        else { //smart computer mode
            //System.out.println("You are playing against the SMART computer"); //Remove this message later, just need for testing
            if (isHumansTurn) {
                System.out.println("You will go first.");
            }
            else {
                System.out.println("The computer will go first.");
            }
            while (true) {
                if (isHumansTurn) {
                    humanTurn();
                    if(marblesInPile == 1) {
                        System.out.println("Human Wins");
                        return;
                    }
                }
                else { //computers turn
                    smartComputerTurn();
                    if (marblesInPile == 1) {
                        System.out.println("Computer Wins");
                        return;
                    }
                }
            }
        }
    }
}
