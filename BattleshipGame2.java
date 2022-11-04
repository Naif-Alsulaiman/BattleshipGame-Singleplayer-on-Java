import java.util.*;

public class BattleshipGame2{
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
                    System.out.print(showShip ? "Y" : "Y");
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

        int length = promptForInt("\fEnter river length" ); // prompts the user to input length number
        System.out.println();// printing line to be exact same as the given screenshot.
        int riverLength[] = new int[length]; // river length is equal to that length number inserted
        int[] hiddenShip={1,2}; // ships array to hide 2 ships, (not the best way but it works fine.)
        hiddenShip[0] = new Random().nextInt(length);// hiding 1st ship in the length number
        hiddenShip[1] = new Random().nextInt(length);// hiding 2nd ship in the length number 
        displayRiver(riverLength, false);  // activating river method in here
        boolean showShip = false, firstShipFound = false, secondShipFound = false; // boolean local variables to be used

        while(hiddenShip[0]==hiddenShip[1])// to ensure that the two ships are in different places.
        {
            hiddenShip[1] = new Random().nextInt(length);//rerolls the 2nd ship place untill they are both unique
        }

        while(!showShip)//the game will run as long as the ships are still hidden
        {

            int playerInput = promptForInt("\n" + "Guess, enter location from 1 - " + length ) -1;// using promptForInt method to observe player input number
            if(playerInput == hiddenShip[0])// if first ship was hit and second ship is not.
            {
                if(riverLength[playerInput] == 1)// if the first ship was already found and the user tried to to hit the same location again.
                {
                    System.out.println("You must have a bad memory!"); // message display
                }
                else{
                    System.out.println("Boom!");// displays Boom!
                    riverLength[playerInput] =1;// marks Y in the first ship hidden location
                    firstShipFound = true;// boolean being true, to be used later.
                }//else
            }//if
            if(playerInput == hiddenShip[1])// if second ship was hit and first ship is not.
            {
                if(riverLength[playerInput] == 1)// if the second ship was already found and the user tried to to hit the same location again.
                {
                    System.out.println("You must have a bad memory!");// message display
                }//else
                else{System.out.println("Boom!");// displays Boom!
                    riverLength[playerInput] =1;// marks Y in the second hidden ship location
                    secondShipFound = true;} // the other boolean for the second ship being found is true
            }//if
            if((playerInput != hiddenShip[0])&&(playerInput != hiddenShip[1]))// if the place is not the first or second ship location
            {
                if(riverLength[playerInput] == -1) // cehcks if the location is not already hit
                {
                    System.out.println("You must have a bad memory!"); // if yes, displays this message
                }
                else{
                    System.out.println("Splash...");// prints splash
                    riverLength[playerInput] = -1;// marks the location with -1 (x)
                }//else
            }//if
            if((firstShipFound)&&(secondShipFound))// if both ships are found
            {
                System.out.println("All ships are found!");//displays message to inform the user they found both ships
                showShip=true;//the games stops
            }
            displayRiver(riverLength, false);// activating diplay river method in
        }//while
    }//main
}//class
