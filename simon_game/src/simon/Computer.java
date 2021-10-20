/**
 * Computer.java
 * Sub-class
 * Bren-Gelyn Padlan
 * ICS4UC
 * June 2019
 */
package simon;

public class Computer {
    public static String simonPattern;
    public static int patternCount;

    /* Sets the number pattern
        pre: none
        post: simonPattern is set/updated.
     */
    public static void setPattern() {
        int rand;
        if(patternCount == 0) {
            rand = (int) generateRandNum(0, 9);
            simonPattern = Integer.toString(rand);
            patternCount += 1;
        } else {
            rand = (int) generateRandNum(0, 9);
            simonPattern += rand;
            patternCount += 1;
        }
    }

    /* Prints simonPattern in console.
        pre: none
        post: simonPattern is printed.
     */
    public static void getPattern() {
        System.out.println("The number is " + simonPattern + "\n");
    }

    /* Generates random number.
        pre: none
        post: The double rand is returned.
     */
    public static double generateRandNum(double min, double max) {
        double rand;
        rand = (max - min + 1) * Math.random() + min;
        return (rand);
    }
}
