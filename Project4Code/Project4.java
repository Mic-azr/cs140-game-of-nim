import java.util.Scanner;
/**
 * Driver module for Game of Nim.
 *
 * @author Aparna Mahadev
 * @version 2/28/2020
 */
public class Project4    {
    public static void main(String args[])    {
        char userConfirm = 'y'; //defaulting user input to y to play at least one game
        do {
            Scanner console = new Scanner(System.in);
            System.out.print("Enter the minimum number of marbles in your pile: ");
            int min = console.nextInt();
    
            System.out.print("Enter the maximum number of marbles in your pile: ");
            int max= console.nextInt();
            GameOfNim game = new GameOfNim(min, max);
    
            game.play();
       
            System.out.println("Please enter \"y\" if you want to play again, or enter \"n\" to leave the program.");
            userConfirm = console.next().charAt(0);
        } while (userConfirm == 'y');
        System.out.println("Thanks for playing! Goodbye!");
    }
}
