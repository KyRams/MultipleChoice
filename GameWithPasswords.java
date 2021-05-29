// Filename GameWithPasswords.java
// Written by Kyle Ramsay
// Written on 14 Apr 2021

import java.nio.file.*;
import java.util.Scanner;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;

public class GameWithPasswords {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\Users\\krdra\\Desktop\\Java\\Unit 13\\usernamePasswords.txt");
        InputStream usernameInput = null;
        boolean validation = false;
        Scanner validationInput = new Scanner(System.in);
        try {
            int count = 0;
            usernameInput = Files.newInputStream(path);
            BufferedReader usernameReader = new BufferedReader(new InputStreamReader(usernameInput));
            System.out.println("Enter username >>");
            String username = validationInput.nextLine();
            System.out.println("Enter password >>");
            String password = validationInput.nextLine();
            String fileUsername = usernameReader.readLine();
            String[] usernamePassword = fileUsername.split(",");
            // System.out.println(usernamePassword[0] + " " + usernamePassword[1]);
            while (fileUsername != null && validation == false) {
                usernamePassword = fileUsername.split(",");
                if (username.equals(usernamePassword[0])) {
                    if (password.equals(usernamePassword[1])) {
                        System.out.println("Access Granted!");
                        validation = true;

                    } else {
                        fileUsername = usernameReader.readLine();
                    }
                } else {
                    fileUsername = usernameReader.readLine();
                }

            }
            if (validation == false) {
                System.out.println("Invalid username or password");
            }
            usernameReader.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        if (validation == true) {
            String highScore = "";
            Path file = Paths.get("C:\\Users\\krdra\\Desktop\\Java\\Unit 13\\quizHighScores.txt");
            InputStream fileInput = null;
            try {
                fileInput = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fileInput));
                String s = null;
                String current = "";
                s = reader.readLine();
                while (s != null) {
                    highScore = s;
                    s = reader.readLine();
                }
                System.out.println("Current High Score " + highScore);
                fileInput.close();
            } catch (Exception e) {
                System.out.println(e);
            }

            String[] questions = new String[] { "Name the climbers knot.\nA) Figure-eight B) Noose C) Mighty",
                    "Name the most popular belay device.\nA) ATC B) Gri Gri C) Mega-Jul",
                    "Name a hold where only a finger can fit in it\nA) Pocket B) Sloper C) Crimp",
                    "Name the most popular crag in Canmore\nA) Grassi B) Wasootch C)EEOR",
                    "Name the second most popular crag in Canmmore\nA) Cougar B) Baldy C)Rundle",
                    "What is the gym in Calgary with the biggest walls\nA) Rocky B) Chinook C)Phoenix",
                    "Why do we double check each other before climbing\nA) Safety B) Fear C) Rules",
                    "What do we yell when we fall\nA) Falling B) Climbing C) Going down",
                    "What do you yell before throwing rope\nA) Rope B) Heads up! C) Watch out!",
                    "What is K country's most popular crag\nA) Baldy B)Wasootch C) Carrot creek" };
            char[] answers = new char[] { 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A', 'A' };
            int correctAsnwers = 0;
            int falseAsnwers = 0;

            Scanner input = new Scanner(System.in);

            for (int i = 0; i < questions.length; ++i) {
                System.out.println((i + 1) + ")" + questions[i]);
                char answer = input.next().charAt(0);
                while (answer != 'A' && answer != 'B' && answer != 'C') {
                    System.out.println("Invalid selection...");
                    System.out.println(questions[i]);
                    answer = input.next().charAt(0);
                }
                if (answer == answers[i]) {
                    System.out.println("Correct!");
                    ++correctAsnwers;
                } else {
                    System.out.println("Incorrect. Answer is: " + answers[i]);
                    ++falseAsnwers;
                }
            }
            System.out.println("Correct answers: " + correctAsnwers + " Incorrect answers: " + falseAsnwers);
            if (correctAsnwers > Integer.parseInt(highScore)) {
                System.out.println("New high score: " + correctAsnwers);
                highScore = "\n" + correctAsnwers;
                byte[] data = highScore.getBytes();
                OutputStream output = null;
                try {
                    output = new BufferedOutputStream(Files.newOutputStream(file, APPEND));
                    output.write(data);
                    output.flush();
                    output.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }
}
// Scanner
// array of questions
// array of answers
// prompt user for each array item for questions.
// if answer is not abc ask again
// take answer (ABC)
// compare it to the array of asnwers.
// if answer matches input,
// output correct!
// count this answer.
// if answer incorrect,
// display the correct answer is...
// display text stating how many correct and incorrect answers.