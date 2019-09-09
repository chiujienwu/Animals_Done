package us.mattgreen;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private final FileOutput outFile = new FileOutput("animals.txt");
    private final FileInput inFile = new FileInput("animals.txt");

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        ArrayList<Talkable> zoo = new ArrayList<>();

        Scanner keyboard = new Scanner(System.in);
        // Lines to Replace
        zoo.add(new Dog(true, "Pete"));
        zoo.add(new Cat(9, "Anne Belly"));
        zoo.add(new Student(19, "Joe John Johnson"));
        System.out.print("Please Enter name (NA to end): ");
        String name = keyboard.nextLine();
        while(!name.equals("NA")) {
            System.out.println("(S)tudent, (D)og or (C)at: ");
            char classification = keyboard.nextLine().toUpperCase().charAt(0);
            int age = 0;
            int mice = 0;
            switch(classification) {
                case 'S':
                    System.out.print("Please enter the Student's age: ");
                    String inputAge = keyboard.nextLine();

                    try {
                        age = Integer.parseInt(inputAge);
                    }
                    catch(Exception e) {
                        System.out.println("Age improperly entered defaulted to 18.");
                        age = 18;
                    }
                    zoo.add(new Student(age, name));
                    break;
                case 'D':
                    System.out.print("Is your dog friendly (Y)es or (N)o: ");
                    char inputFriendly = keyboard.nextLine().toUpperCase().charAt(0);
                    boolean friendly = (inputFriendly == 'N')?false:true;
                    zoo.add(new Dog(friendly, name));
                    break;
                case 'C':
                    System.out.print("How many mice has your beast slaughtered: ");
                    String inputmice = keyboard.nextLine();

                    try {
                        mice = Integer.parseInt(inputmice);
                    }
                    catch(Exception e) {
                        System.out.println("Mice killed default to Zero.");
                        mice = 0;
                    }
                    zoo.add(new Cat(mice, name));
                    break;
            }
            System.out.print("Please Enter name (NA to end): ");
            name = keyboard.nextLine();
        }
        // End Lines to Replace

        for (Talkable thing : zoo) {
            printOut(thing);
        }
        outFile.fileClose();

        System.out.println("\n*** Reading/printing entire file using fileRead method\n");

        inFile.fileRead();
        inFile.fileClose();

        System.out.println("\n*** Reading/printing one line at a time using fileReadLine method\n");

        FileInput indata = new FileInput("animals.txt");
        String line;
        while ((line = indata.fileReadLine()) != null) {
            System.out.println(line);
        }
    }

    public void printOut(Talkable p) {
        System.out.println(p.getName() + " says = " + p.talk());
        outFile.fileWrite(p.getName() + "|" + p.talk());
    }
}
