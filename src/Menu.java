import java.util.Scanner;

public class Menu {
    private static Scanner sc;
    public static void displayMenu() {
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

    public static void chooseOption() {
        int choice;
        sc = new Scanner(System.in);
        do {

            displayMenu();
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input");
                sc.next();
            }
            Skeleton skeleton = new Skeleton();
            choice = sc.nextInt();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    skeleton.sixTekton();
                    MushroomMaster mm = (MushroomMaster)skeleton.objects.get("mmaster");
                    mm.initiateMyceliumGrowth((Tekton)skeleton.objects.get("tN"), (Tekton)skeleton.objects.get("tN2"));
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
        while (choice != 0);
        sc.close();
        System.out.println("\n");

    }
    /**
     * Asks the user to make a yes-or-no decision
     * @return Returns a boolean corresponding to the user's answer
     */
    public static boolean userDecision() {
        System.out.println("[y/n]: ");
        char c = 0;
        while (c != 'y' && c != 'n') {
            if (sc.hasNext()) {
                c = sc.next().charAt(0);
            }
        }
        return c == 'y';
    }
}
