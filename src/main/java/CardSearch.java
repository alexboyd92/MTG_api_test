import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CardSearch {

    public static void searchMenu() throws Exception {
        // Create scanner for user input
        String[] result = new String[2];
        result[0]="-1";
        Scanner userInput = new Scanner(System.in);
//        System.out.println("Enter card name to look for");
//        String target = userInput.nextLine();
        String target = "Ancestor's Chosen";
        try {
            result = searchCard(target);
            if (result[0].equals("-1")) {
                System.out.printf("%s Was not found did you mean %s?\n", target, result[1]);
            }
            if (result[0].equals("1"))
                System.out.printf("%s Was found\n",target);
        } catch (Exception e) {
            System.out.println(e);
        }


        //close scanner
        userInput.close();
    }


    public static String[] searchCard(String target) throws FileNotFoundException {
        String[] result = new String[2];
        int x=0;
        //set default value of found to false
        result[0] = "-1";
        // Create a string to store a name they may have meant
        String candidate = "";
        //Stores the current smallest distance from target word
        int currentDistance = 8;
        //placeholder for line read in
        String cardName;
        // open scanner on the card list
        Scanner fileScan = new Scanner(new FileInputStream("src/main/resources/card_List.txt"));

        //get rid of the first empty line
        fileScan.nextLine();
        do {

            //read the next potential card name
            cardName = fileScan.nextLine();

            //take card name to lowercase and cut off files leading characters
            cardName = cardName.substring(11).toLowerCase();

            //if the card name is the same as the target set the result to true forcing them both to lowercase
            if (cardName.equals(target.toLowerCase())) {
                result[0] = "1";
                break;
            } else {
                result[0]="-1";
                //Use string utils from java to determine the Levenshtein Distance
                LevenshteinDistance levDist = new LevenshteinDistance();
                int currDist = levDist.apply(cardName, target);
                // if the distance is smaller store
                if (currDist < currentDistance) {
                    candidate = cardName;
                    currentDistance = currDist;
                }

            }
            //skip lines that are not card names
            for (int j = 0; j < 3; j++) {
                fileScan.nextLine();
            }

        }
        while (fileScan.hasNextLine());
        // close scanner
        fileScan.close();
        result[1]=candidate;
        return result;
    }

}
