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
                16: Spread spores as normal mushroom
                17: Spread spores as super mushroom
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
            Skeleton skeleton = new Skeleton();
            choice = sc.nextInt();
            switch (choice) {
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
                    skeleton.tektonsWithMushroom();
                    MushroomMaster mushroomMaster15 = (MushroomMaster)skeleton.objects.get("mmaster");
                    MushroomBody mushroomBody15 = (MushroomBody)skeleton.objects.get("mushroom");
                    mushroomMaster15.initiateSuperMushroomGrowth(mushroomBody15);
                    break;
                case 16:
                    skeleton.tektonsWithMushroom();
                    MushroomMaster mushroomMaster16 = (MushroomMaster)skeleton.objects.get("mmaster");
                    MushroomBody mushroomBody = (MushroomBody)skeleton.objects.get("mushroom");
                    Tekton tN = (Tekton)skeleton.objects.get("tN");
                    mushroomMaster16.initiateSporeSpreading(mushroomBody, tN);
                    break;
                default:
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
        while (c != 'y' && c != 'n') {
            c = sc.next().charAt(0);
        }
        sc.close();
        return c == 'y';
    }
}
