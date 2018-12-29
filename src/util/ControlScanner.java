/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author QinYing
 */
public class ControlScanner {
    private final Scanner input = new Scanner(System.in);
    
    public String nextLine(String question) {
        System.out.print(question);
        return input.nextLine();
    }

    public boolean getConfirmation(String question) {
        boolean retry = true;
        char c = 'Y';
        while (retry) {
            retry = false;
            System.out.print(question);
            c = input.nextLine().charAt(0);
            if (c != 'Y' && c != 'y' && c != 'N' && c != 'n') {
                retry = true;
                System.out.println("      ERROR: Invalid confirmation option !");
                System.out.println("");
            }
        }
        return c == 'Y' || c == 'y';
    }

    public double nextDouble(String question) {
        boolean retry = true;
        double val = -1;
        while (retry) {
            retry = false;
            System.out.print(question);
            try {
                val = input.nextDouble();
            } catch (InputMismatchException ex) {
                System.out.println("      ERROR: Invalid input.(double required)");
                System.out.println("");
                retry = true;
            } finally {
                clear();
            }
        }
        return val;
    }

    public double nextDouble(String question, String errorMessage, double min, double max) {
        boolean retry = true;
        double val = -1;
        while (retry) {
            retry = false;
            val = ControlScanner.this.nextDouble(question);
            if (val < min || val > max) {
                System.out.println("      ERROR: " + errorMessage);
                System.out.println("");
                retry = true;
            }
        }
        return val;
    }

    public int nextInt(String question) {
        boolean retry = true;
        int val = -1;
        while (retry) {
            retry = false;
            System.out.print(question);
            try {
                val = input.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("      ERROR: Invalid input.(integer required)");
                System.out.println("");
                retry = true;
            } finally {
                clear();
            }
        }
        return val;
    }

    public int nextInt(String question, String errorMessage, int min, int max) {
        boolean retry = true;
        int val = -1;
        while (retry) {
            retry = false;
            val = ControlScanner.this.nextInt(question);
            if (val < min || val > max) {
                System.out.println("      ERROR: " + errorMessage);
                System.out.println("");
                retry = true;
            }
        }
        return val;
    }

    private void clear() {
        input.nextLine();
    }
}
