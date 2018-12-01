import java.util.Scanner;
import java.util.Random;
public class Main {

    public static int lP = 0; //lateral position
    public static int vPB = 0; //veritcal position of bottom half of Mario
    public static int vPT = 1; //vertical position of top half of Mario
    public static int[][] master = new int[3][10]; //rows x columns
    public static Scanner scan = new Scanner(System.in);
    public static String output;

    public static void main(String[] args) {
        Random rand = new Random();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < master[0].length; j++) {
                master[i][j] = 0;
            }
        }
        master[0][0] = 2;
        master[1][0] = 2; // rows x columns

       for (int i = 1; i < 9; i++) {
            master[2][i] = rand.nextInt(2);
       }

       for (int i = 1; i < 9; i++) {
            if (master[2][i] == 0) {
                master[0][i] = rand.nextInt(2);
            }
       }

       do {
            gameUsage();
            System.out.printf("%s\n\n", output);
       } while(lP < 9);

        System.out.printf("Congratulations! You have completed the level.");
    }

    public static void printGame() { //good
        //code for printing array and game 1-X, 2-*, 0-space, i rows x j columns
        for (int j = master.length-1; j >= 0; j--) {
            for (int i = 0; i < master[0].length; i++) {
                System.out.printf("%s ", assignment(master[j][i]));
            }
            System.out.printf("\n");
        }

    }

    public static String assignment(int a) { //good
        if(a == 0){
            return "O";
        }
        else if(a == 1){
            return "X";
        }
        else if(a == 2){
            return "*";
        }
        else {
            return "error";
        }
    }

    public static void gameUsage() { //issue is here
        printGame();
        System.out.printf("Enter a command (jump, duck, right, left).\n>>");
        String command = scan.next();
        switch (command) {
            case "left":
                if((lP > 0) && (master[vPB][lP - 1] == 0) && (master[vPB][lP - 1] == 0)){
                    master[vPT][lP] = 0;
                    master[vPB][lP] = 0;
                    master[vPT][lP - 1] = 2;
                    master[vPB][lP - 1] = 2;
                    lP--;
                    output = "You've moved left. But, why?";
                }
                else {
                    output = "That's not allowed.";
                }
                break;

            case "right":
                if((master[vPT][lP + 1] == 0) && (master[vPB][lP + 1] == 0)) {
                    master[vPT][lP] = 0;
                    master[vPB][lP] = 0;
                    master[vPT][lP + 1] = 2;
                    master[vPB][lP + 1] = 2;
                    lP++;
                    output = "You've moved right. Looks like progress to me.";
                }
                else if((vPT == 2) && (master[0][lP + 1] == 0)) {
                    master[vPT][lP] = 0;
                    master[vPB][lP] = 0;
                    master[vPT - 1][lP + 1] = 2;
                    master[vPB - 1][lP + 1] = 2;
                    lP++;
                    output = "You've moved right. Looks like progress to me.";
                }
                else {
                    output = "That's not allowed.";
                }
                break;

            case "jump":
                if((master[vPT + 1][lP + 1] == 0) && (master[vPB + 1][lP + 1] == 0)) {
                    master[vPT][lP] = 0;
                    master[vPB][lP] = 0;
                    master[vPT + 1][lP + 1] = 2;
                    master[vPB + 1][lP + 1] = 2;
                    lP++;
                    vPT++;
                    vPB++;
                    output = "Look at those hops.";
                }
                else {
                    output = "That's not allowed.";
                }
                break;

            case "duck":
                if((master[vPT - 1][lP + 1] == 0) && (master[vPB - 1][lP + 1] == 0)) {
                    master[vPT][lP] = 0;
                    master[vPB][lP] = 0;
                    master[vPT - 1][lP + 1] = 2;
                    master[vPB - 1][lP + 1] = 2;
                    lP++;
                    vPT--;
                    vPB--;
                    output = "Phew... That was a close one.";
                }
                else {
                    output = "That's not allowed.";
                }
                break;

            case "default":
                output = "That's not a command.";
        }
    }
}
        /*Scanner scan = new Scanner(System.in); This is a different game. A random number guesser between 0 and 100
        Random rand = new Random();
        String output;
        int rando = rand.nextInt(101);
        int upperBound = 101;
        int lowerBound = 0;
        int tries = 0;
        int choice;
        System.out.printf("I'm thinking of a number between 0 and 100. Guess.\n");

        do {
            System.out.print(">>");
            choice = scan.nextInt();

            if (rando == choice) {
                output = "You've won.";
            } else if (lowerBound < rando && rando > choice) {
                output = "Your guess was less than the actual";
            } else if (rando < choice && rando < upperBound) {
                output = "Your guess was more than the actual";
            } else {
                output = "Your guess didn't fit the paramer.";
            }
            tries++;
            System.out.printf("%s It took %d tries.\n", output, tries);
        } while (rando != choice);

    }*/

