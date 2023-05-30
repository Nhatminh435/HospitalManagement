package viewer;

import java.util.Scanner;

/**
 * This class supply methods to display menu.
 * @author N.Minh
 */
public class Menu {
    /**
     * Display menu of Nurse or Patient Management
     * @param options: options of menu you want to display
     * @param nameBoard: name of menu you want to display
     * @return 
     */
    public static int getChoice(Object[] options, String nameBoard) {
        int getChoice = 0;
            System.out.println(" ---------------------------");
        System.out.printf("|    %7s's management   |\n", (Character.toUpperCase(nameBoard.charAt(0)) + nameBoard.substring(1)));
            System.out.println("|---------------------------|");
        for (int i = 0; i < options.length; i++) {
            System.out.printf("| %d. %-22s |\n", (i+1), options[i]);
        }
        System.out.println("| Other. Exit               |");
        System.out.println(" ---------------------------");
        System.out.print("Your options from 1 - " + options.length + ": ");
        Scanner sc = new Scanner(System.in);
        String getChoiceStr = sc.nextLine().trim();
        if ((!getChoiceStr.isEmpty()) && (getChoiceStr.matches("[1-9]")) && (Integer.parseInt(getChoiceStr) > 0) && (Integer.parseInt(getChoiceStr) <= (options.length + 1))) {
            getChoice = Integer.parseInt(getChoiceStr);
        }
        
        return getChoice;
    }
}

