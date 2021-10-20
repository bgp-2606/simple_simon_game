/**
 * Simon Game
 * Main.java
 * Bren-Gelyn Padlan
 * ICS4UC
 * A beta version of Simon: Follow the Pattern game.
 * June 2019
 * Game is intended for all ages.
 */

package simon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {
    public static int score = 0;
    public static int highScore = 0;
    public static boolean gameOver = false;
    public static boolean roundOver = false;
    public static boolean quit = false;
    public static int time = 1500;

    public static Stage window;
    public static Stage rulesWindow;


    public static void main(String[] args) {
        String enter;

        Scanner input = new Scanner(System.in);
        launch(args);
        if (!quit) {
            /* Every game run*/
            do {
                roundOver = false;

                System.out.println("SIMON (follow the pattern) \n \n");
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("   V");

                /* Every round of guessing the pattern */
                do {
                    // Creates the pattern.
                    Computer.setPattern();
                    Computer.getPattern();

                    // Pauses the app for a certain amount of time.
                    try {
                        Thread.sleep(time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    hidePattern();

                    // Prompts user for guess.
                    User.getGuess();

                    compareGuess(Computer.simonPattern, User.userPattern);

                    // Updates time. Time gets faster as the pattern gets longer.
                    if (time >= 500){
                        time -= 50;
                    } else {
                        time = 500;
                    }

                } while (roundOver == false);

                System.out.print("Do you want to try again? (y = yes, n = no)  ");
                enter = input.nextLine();
                if (enter.equalsIgnoreCase("y")) {
                    reset();
                } else {
                    gameOver = true;
                }
            } while (gameOver == false);
        }


    }

    /* Creates and shows the main window.
        pre: none
        post: The main window is shown.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        window.setTitle("SIMON");
        window.setScene(new Scene(root, 800, 600));
        window.setResizable(false);
        window.show();
    }

    /* Shows the rules in another window.
       pre: none
       post: The rules window is shown.
     */
    public void openRules() throws IOException {
        rulesWindow = new Stage();

        Parent root = FXMLLoader.load(getClass().getResource("rules.fxml"));
        Scene scene = new Scene(root, 600, 400);
        rulesWindow.setScene(scene);
        rulesWindow.setTitle("SIMON: Rules");
        rulesWindow.setResizable(false);
        rulesWindow.initModality(Modality.APPLICATION_MODAL);
        rulesWindow.show();

    }

    /* Compares the simonPattern with the userPattern.
        pre: simonPattern and userPattern must not be null.
        post: A message will be printed out.
     */
    public static void compareGuess(String simonPattern, String userPattern) {
        if (simonPattern.equals(userPattern)) {
            score += 1;

            switch ((int)Computer.generateRandNum(1, 5)) {
                case 1:
                    System.out.println("Good job! \n"); break;
                case 2:
                    System.out.println("Your memory is sharp! \n"); break;
                case 3:
                    System.out.println("Keep it up! \n"); break;
                case 4:
                    System.out.println("You scored! \n"); break;
                case 5:
                    System.out.println("Nice! \n"); break;
            }

        } else {
            System.out.println("The correct answer is " + Computer.simonPattern);
            displayScore();

            switch ((int)Computer.generateRandNum(1, 4)) {
                case 1:
                    System.out.println("Better luck next time. \n"); break;
                case 2:
                    System.out.println("Too bad. \n"); break;
                case 3:
                    System.out.println("Try again. \n"); break;
                case 4:
                    System.out.println("One more try? \n"); break;

            }

            score = 0;
            roundOver = true;
        }
    }

    /* Displays score and highScore in the console.
        pre: none
        post: score and highScore are printed.
     */
    public static void displayScore() {
        if (score > highScore) {
            highScore = score;
        }
        System.out.println("Your score is " + score);
        System.out.println("Your highest score is " + highScore);
    }

    /* Prints 500 lines of empty string.
        pre: none
        post: A 500 lines of code are printed.
     */
    public static void hidePattern() {
        for (int i=0; i<500; i++) {
            System.out.println();
        }
    }

    /* Resets the game.
        pre: none
        post: patternCount is set to zero.
                time is set back to 1500.
     */
    public static void reset() {
        Computer.patternCount = 0;
        time = 1500;
    }

    /* Closes main window when quit button is clicked.
        pre: none
        post: The main window is closed.
     */
    public void closeWindow() {
        quit = true;
        window.close();
    }

    /* Hides main window when start button is clicked.
        pre: none
        post: The main window is closed.
     */
    public void hideWindow() {
        window.close();
    }

    /* Closes rules window when back button is clicked.
        pre: none
        post: The rules window is closed.
     */
    public void closeRules() {
        quit = true;
        rulesWindow.close();
        quit = false;
    }


}
