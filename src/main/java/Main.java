import io.magicthegathering.javasdk.api.CardAPI;
import io.magicthegathering.javasdk.resource.Card;

import java.nio.file.attribute.UserPrincipal;
import java.util.Scanner;
import java.io.*;
import java.util.List;

public class Main {
public static void main(String[] args) {

mainMenu();
System.out.println("Closing");
}
public static String searchCard(){
    return null;
}
public static void mainMenu(){
    // Create scanner for user input
    Scanner userInput = new Scanner(System.in);
    //Set default choice
    int choice=0;
    // Ask for user input
    do{
        //print menu
        System.out.printf("Choose an option.\n" +
                "1) Search for Card\n" +
                "2) Update card list **Warning will take a while**\n" +
                "3) Exit\n");
        // get choice
        String choiceStr = userInput.nextLine();
        // attempt to parse integer into option
        try{
            //parse string into integer
            choice= Integer.parseInt(choiceStr);
            // see if the integer is an option
            switch (choice){
                case 1: searchCard();
                    break;
                case 2: updateList();
                    System.out.println("Card list updated");
                    break;
                case 3:
                    break;
                default: System.out.println("Invalid Input");
                    choice=3;
                    break;
            }
        }catch (Exception NumberFormatException){
            choice=0;
            System.out.printf("Invalid input\n");
        }} while (choice!=3);
    userInput.close();

}
public static void updateList(){
    //create list to store cards and get all cards from the API
    List<Card> cards = CardAPI.getAllCards();
    // Try to write the cards to a file
    try {
        FileWriter writer = new FileWriter("src/main/resources/card_List.txt");
        //Write each card in the list
        for(Card str: cards) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    } catch(Exception ex) {
        // print stack trace if failed
        ex.printStackTrace();
    }

}
}
