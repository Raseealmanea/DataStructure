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
        System.out.println("║ 5.  Term Retrieval                       ║");
        System.out.println("║ 6.  Exit                                 ║");
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


    public static void Boolean_Retrieval()
    {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║            Boolean Retrieval             ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1.  index                                ║");
        System.out.println("║ 2.  inverted index                       ║");
        System.out.println("║ 3.  inverted index using BST             ║");
        System.out.println("║ 4.  inverted index using AVL             ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.print("🌟 Please enter your choice: ");
        int choice2 = input.nextInt();
        System.out.print("🌟 Please enter boolean term( AND / OR ): ");
        String trm=input.nextLine();
        trm=input.nextLine();


        System.out.print("Q#: ");
        System.out.println(trm);

        System.out.print("Result doc IDs: ");

            printBoolean(SE.Boolean_Retrieval(trm.trim().toUpperCase(), choice2)); // Call to your retrieval method

            System.out.println("\n═══════════════════════════════════════════════════\n");

    }

    public static void RepetitionMenu() {


        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║            Ranked Retrieval              ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1.  index                                ║");
        System.out.println("║ 2.  inverted index                       ║");
        System.out.println("║ 3.  inverted index using BST             ║");
        System.out.println("║ 4.  inverted index using AVL             ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.print("🌟 Please enter your choice: ");
        int choice3 = input.nextInt();
        System.out.print("🌟 Please enter boolean term: ");
        String trm=input.nextLine();
        trm=input.nextLine();



        System.out.println("## Q: " + trm);


            SE.Ranked_Retrieval(trm,choice3);

            System.out.println("=========================================\n");

    }
    public static void Indexed_Documents()
    {
        System.out.println("** Indexed Documents **");
        System.out.println("Indexed Documents ");
        SE.Indexed_Documents();
    }

    public static void Indexed_Tokens()
    {
        System.out.println("######## Indexed Tokens ######## ");
        System.out.println("tokens ");
        SE.Indexed_Tokens();

    }
    public static void TermRetrieval(){
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║              Term Retrieval              ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1.  index                                ║");
        System.out.println("║ 2.  inverted index                       ║");
        System.out.println("║ 3.  inverted index using BST             ║");
        System.out.println("║ 4.  inverted index using AVL             ║");
        System.out.println("╚══════════════════════════════════════════╝");
        System.out.print("🌟 Please enter your choice: ");
        int choice4 = input.nextInt();
        System.out.print("🌟 Please enter Term: ");
        String trm=input.next();

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("              Result document Number                ");
        System.out.println("═══════════════════════════════════════════════════\n");
        printBoolean(SE.Term_Retrieval(trm.trim().toLowerCase(), choice4 ));
        System.out.println("\n");



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
                    Boolean_Retrieval();
                    break;

                case 2:
                    RepetitionMenu();
                    break;

                case 3:
                    Indexed_Documents();
                    break;

                case 4:
                    Indexed_Tokens();
                    break;

                case 5:
                    TermRetrieval();
                    break;
                case 6:
                    break;

                default:
                    System.out.println("bad choice, try again!");
            }
        } while (choice != 6);
    }

}
