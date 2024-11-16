package com.mycompany.mavenproject8;
import java.util.Scanner;

public class main {

    public static Scanner input = new Scanner (System.in);
    public static Search SE = new Search();



    public static int MENU()
    {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║                Main Menu                 ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1.  Boolean Retrieval                    ║");
        System.out.println("║ 2.  Ranked Retrieval                     ║");
        System.out.println("║ 3.  Indexed Documents                    ║");
        System.out.println("║ 4.  Indexed Tokens                       ║");
        System.out.println("║ 5.  Exit                                 ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.print("🌟 Please enter your choice: ");

        int choice = input.nextInt();
        return choice;
    }

    public static void printBoolean(boolean [] result)
    {
        WordCount t = new WordCount ("", result);
        System.out.println(t);
    }


    public static void Boolean_Retrieval_menu()
    {
        String[] Questions = {
                "market AND sports",
                "weather AND warming",
                "business AND world",
                "weather OR warming",
                "market OR sports",
                "market OR sports AND warming"
        };

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("                  Boolean Retrieval                ");
        System.out.println("═══════════════════════════════════════════════════\n");

        for (int i = 0; i < Questions.length; i++) {
            String str = Questions[i];

            System.out.printf("🟆 Query #%d: \"%s\"\n", i + 1, str);
            System.out.println("───────────────────────────────────────────────");
            System.out.print("📄 Result Doc IDs: ");

            printBoolean(SE.Boolean_Retrieval(str, 2)); // Call to your retrieval method

            System.out.println("\n═══════════════════════════════════════════════════\n");
        }
    }

    public static void RepetitionMenu() {
        String[] Questions = {
                "market sports",
                "weather warming",
                "business world market"
        };

        System.out.println("=========================================");
        System.out.println("            Ranked Retrieval             ");
        System.out.println("=========================================\n");

        for (int i = 0; i < Questions.length; i++) {
            String str = Questions[i];

            System.out.printf(">>> Query %d: \"%s\"\n", i + 1, str);
            System.out.println("─────────────────────────────────────");
            System.out.println("DocID\t\tScore");
            System.out.println("─────────────────────────────────────");

            SE.RepetitionRetrieval(str); // This method displays DocID and Score

            System.out.println("=========================================\n");
        }
    }
    public static void Indexed_Documents_menu()
    {
        System.out.println("** Indexed Documents **");
        System.out.println("Indexed Documents " + SE.Pointer.pointers.length);
    }

    public static void Indexed_Tokens_menu()
    {
        System.out.println("######## Indexed Tokens ######## ");
        System.out.println("tokens " + SE.tokens);
    }

    public static void main(String[] args) {

        SE.load("/Users/slymanalmny/Downloads/data (1)/stop.txt", "/Users/slymanalmny/Downloads/data (1)/dataset.csv");

        // TODO code application logic here
        int choice;

        do {
            choice = MENU();
            switch (choice)
            {
                case 1:
                    Boolean_Retrieval_menu();
                    break;

                case 2:
                    RepetitionMenu();
                    break;

                case 3:
                    Indexed_Documents_menu();
                    break;

                case 4:
                    Indexed_Tokens_menu();
                    break;

                case 5:
                    break;

                default:
                    System.out.println("bad choice, try again!");
            }
        } while (choice != 5);
    }

}
