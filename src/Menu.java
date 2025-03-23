import java.util.Scanner;

public class Menu {
    private static Scanner sc;
    /**
     * Displays the menu options 
     */
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
                16: Spread spores as normal mushroom
                17: Spread spores as super mushroom
                0: Exit
                """);
    }
    /**
     * Method to read and evaluate user input on the System.in stream
     */
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
                    MushroomMaster mm2 = (MushroomMaster)skeleton.objects.get("mmaster");
                    mm2.initiateMyceliumGrowth((Tekton)skeleton.objects.get("tN"), (Tekton)skeleton.objects.get("tN2"));
                    break;
                case 3:
                    skeleton.sixTekton();
                    MushroomMaster mm3 = (MushroomMaster)skeleton.objects.get("mmaster");
                    mm3.initiateMyceliumGrowth((Tekton)skeleton.objects.get("tH"), (Tekton)skeleton.objects.get("tI"));
                    break;
                case 4:
                    skeleton.sixTekton();
                    MushroomMaster mm4 = (MushroomMaster)skeleton.objects.get("mmaster");
                    mm4.initiateMyceliumGrowth((Tekton)skeleton.objects.get("tH"), (Tekton)skeleton.objects.get("tA"));
                    break;
                case 5:
                    skeleton.sixTekton();
                    MushroomMaster mm5 = (MushroomMaster)skeleton.objects.get("mmaster");
                    mm5.initiateMyceliumGrowth((Tekton)skeleton.objects.get("tH"), (Tekton)skeleton.objects.get("tD"));
                    break;
                case 6:
                    skeleton.sixTekton();
                    InsectMaster im6 = (InsectMaster)skeleton.objects.get("imaster");
                    im6.initiateMyceliumCutting((Mycelium)skeleton.objects.get("myc1"));
                    break;
                case 7:
                    skeleton.eatRegularSpore();
                    InsectMaster im7 = (InsectMaster)skeleton.objects.get("imaster");
                    im7.initiateSporeEating((Tekton)skeleton.objects.get("tN"));
                    break;
                case 8:
                    skeleton.eatSlowingSpore();
                    InsectMaster im8 = (InsectMaster)skeleton.objects.get("imaster");
                    im8.initiateSporeEating((Tekton)skeleton.objects.get("tN"));
                    break;
                case 9:
                    skeleton.eatSpeedingSpore();
                    InsectMaster im9 = (InsectMaster)skeleton.objects.get("imaster");
                    im9.initiateSporeEating((Tekton)skeleton.objects.get("tN"));
                    break;
                case 10:
                    skeleton.eatParalyzingSpore();
                    InsectMaster im10 = (InsectMaster)skeleton.objects.get("imaster");
                    im10.initiateSporeEating((Tekton)skeleton.objects.get("tN"));
                    break;
                case 11:
                    skeleton.eatCutBlockingSpore();
                    InsectMaster im11 = (InsectMaster)skeleton.objects.get("imaster");
                    im11.initiateSporeEating((Tekton)skeleton.objects.get("tN"));
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
