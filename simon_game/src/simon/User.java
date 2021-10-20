/**
 * User.java
 * Sub-class
 * Bren-Gelyn Padlan
 * ICS4UC
 * June 2019
 */

package simon;

import java.util.Scanner;

public class User {
    public static String userPattern;

    /* Gets user input and assigns it to userPattern.
        pre: none
        post: The user input was assigned to userPattern.
     */
    public static void getGuess() {
        Scanner input = new Scanner(System.in);
        System.out.print("Repeat the pattern:  ");
        userPattern = input.next();

        System.out.println();
    }
}
