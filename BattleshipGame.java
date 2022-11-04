import java.util.*;

public class BattleshipGame{
    private static Scanner input = new Scanner(System.in);

    /**
     * Prompts a user for a positive int value and returns it.
     * 
     * @param prompt
     *            the desired prompt message to be displayed on screen
     */

    public static int promptForInt(String prompt) {
        int number;        
        do
        {   System.out.print(prompt + ": "); 
            number = input.nextInt();
            if (number <= 0) 
                System.out.println("Enter a positive integer!");
        }while (number <= 0);
        return number;
    }

    /**
     * Displays a nice picture of the river in the Console View
     * 
     * @param river
     *            the array that represents the river
     * @param showShip
     *            indicates if you wish to display the location of the ship
     */

    public static void displayRiver(int[] river, boolean showShip) {
        System.out.print("|");
        for (int val : river) {
            switch (val) {
                case -1: // No Ship
                    System.out.print("x");
                    break;
                case 0: // Unknown
                    System.out.print(" ");
                    break;
                case 1: // Ship Found
                    System.out.print(showShip ? "" : "Y");
                    break;
            }//switch
            System.out.print("|");
        }//for
        System.out.println();
        System.out.println();
    }//displayRiver

    /**
     * main method will use two other methods, will prompt the user to input the length of the river in the game,
     * and game illustration starts.
     * 
     * @Naif Alsulaiman (21230176)
     *           
     * @Assignment2 (03/11/2022)
     */ 
    // main method
    public static void main(String[] arg) {

        int length = promptForInt("\fEnter river length" ); // using promptForInt method to get length number
        System.out.println();// printing line to be exact same as the given screenshot.
        int riverLength[] = new int[length]; // river length is being equal to the length number ^
        int hiddenShip= new Random().nextInt(length);// hiding the first ship in betweenn 0- and the length number
        displayRiver(riverLength, false);  // activating displayRiver method
        boolean showShip = false; // boolean to be used

        while(!showShip)// the game will run as long as the ship is still hidden
        {      
            int playerInput = promptForInt("\n" + "Guess, enter location from 1 - " + length ) -1; // using promptForInt method to observe player input number

            if(playerInput == hiddenShip) // if hidden ship was hit
            {
                System.out.println("Boom!"); // display output msg
                riverLength[playerInput] =1; // activating case 1 in that place (ship)
                showShip=true; // ship is found, while method stops, so games stops.
            }//if
            else // else, hits a any other than hidden ship
            {
                if(riverLength[playerInput] == -1) // if the targeted location is already hit before
                {
                    System.out.println("You must have a bad memory!"); // displays a message 
                }// if
                else{// if the location was unkown and revealed to be empty 
                    System.out.println("Splash...");  // message output
                    riverLength[playerInput] = -1;// activating case -1 in that place (river)

                }//else
            }//if

            displayRiver(riverLength, false);// activating displayRiver method in
        }//while
    }//main
}//class
