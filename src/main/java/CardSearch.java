import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CardSearch {

    public static void searchMenu(Scanner userInput) {

        String[] result = new String[4];
        result[0] = "-1";
        String target="";
        System.out.println("Enter card name to look for");
        try {
            target = userInput.nextLine();
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            result = searchCard(target);
            if (result[0].equals("-1")) {
                System.out.printf("%s Was not found did you mean %s?\n" +
                        "1) Yes\n" +
                        "2) No\n", target, result[1]);
                target = userInput.nextLine();
                try {
                    if(Integer.parseInt(target)==1){
                       result=searchCard(result[1]);

                    }else System.out.println("Check the spelling and try again");
                }catch (Exception NumberFormatException){
                    System.out.println("Not a valid input returning to main menu.");
                }
            }
            if (result[0].equals("1"))
                printCard(result);
        } catch (Exception e) {
            System.out.println(e);
        }



    }

    private static void printCard(String[] result) {
        System.out.printf("%s Was found\n" +
                "Its ID is %s\n" +
                "and its mana cost is %s\n", result[1], result[2], result[3]);
    }


    public static String[] searchCard(String target) throws FileNotFoundException {
        String[] result = new String[4];

        int x = 0;
        //set default value of found to false
        result[0] = "-1";
        //Stores the current smallest distance from target word
        int currentDistance = 8;
        //placeholder for line read in
        String cardName;
        // open scanner on the card list
        Scanner fileScan = new Scanner(new FileInputStream("src/main/resources/card_List.txt"));

        //get rid of the first empty line
        try {
            fileScan.nextLine();
        } catch (Exception e) {
            System.out.println(e);
        }
        do {

            //read the next potential card name
            cardName = fileScan.nextLine();

            //take card name to lowercase and cut off files leading characters
            cardName = cardName.substring(11).toLowerCase();

            //if the card name is the same as the target set the result to true forcing them both to lowercase
            if (cardName.equals(target.toLowerCase())) {
                result[0] = "1";
                result[1]=target;
                result[2] = fileScan.nextLine().substring(15);
                result[3] = fileScan.nextLine().substring(11);
                break;
            } else {
                result[0] = "-1";
                //Use string utils from java to determine the Levenshtein Distance
                LevenshteinDistance levDist = new LevenshteinDistance();
                int currDist = levDist.apply(cardName, target);
                // if the distance is smaller store
                if (currDist < currentDistance) {
                    result[1] = cardName;
                    currentDistance = currDist;
                }

            }
            //skip lines that are not card names IF THEY EXIST.
            for (int j = 0; j < 3; j++) {
                if (fileScan.hasNext()) {
                    fileScan.nextLine();
                }
            }

        }
        while (fileScan.hasNextLine());
        // close scanner
        fileScan.close();
        return result;
    }

}
