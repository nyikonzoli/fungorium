import java.util.Scanner;

public class Menu {
    public void displayMenu() {
        System.out.print("""
                
                1: Start new round
                2: Grow mycelium to Tekton
                3: Grow mycelium to Infertile Tekton
                4: Grow mycelium to Absorbing Tekton
                5: Grow mycelium to Deadend Tekton
                6: Cut mycelium
                7: Eat normal spore
                8: Eat slowing spore
                9: Eat speeding spore
                10: Eat paralyzing spore
                11: Eat cut blocking spore
                12: Move insect
                13: Tekton split
                14: Grow mushroom
                15: Grow super mushroom
                16: Spread spores as super mushroom
                0: Exit
                """);
    }

    public void chooseOption() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input");
                sc.next();
            }

            choice = sc.nextInt();

            if(choice < 0 || choice > 16){
                System.out.println("Invalid input");
            }

        }
        while (choice != 0);

        System.out.println("\n");

        sc.close();
    }
    /**
     * Asks the user to make a yes-or-no decision
     * @return Returns a boolean corresponding to the user's answer
     */
    public static boolean userDecision() {
        System.out.println("[y/n]: ");
        Scanner sc = new Scanner(System.in);
        char c = 0;
        while (c != 'y' || c != 'n') {
            c = (char)sc.nextByte();
        }
        sc.close();
        return c == 'y';
    }
}
