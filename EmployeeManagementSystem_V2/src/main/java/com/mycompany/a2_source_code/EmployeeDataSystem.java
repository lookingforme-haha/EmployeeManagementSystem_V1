package com.mycompany.a2_source_code;

import static java.lang.Integer.MIN_VALUE;
import java.util.Scanner;

public class EmployeeDataSystem {
    public static Menu menu = new Menu();
    public static Scanner scan = new Scanner(System.in);

    @SuppressWarnings("static-access")
	public static void main(String[] args) {
        System.out.print("Enter your ID and the system will find out the lagest digit in your ID> ");
        String ID = scan.nextLine();
        System.out.println("largest digit in your ID "+ CheckMaxNum(ID));
        
        // calling menu method in Menu class
        menu.DisplayMenu(CheckMaxNum(ID));
    }   
    
    // find the max digit of the given ID
    private static int CheckMaxNum(String ID) {
        int max = MIN_VALUE;
        for (int i = 0; i < ID.length(); i++) {
            char digitChar = ID.charAt(i);

            if (Character.isDigit(digitChar)) {
                int digit = Character.getNumericValue(digitChar);
                max = Math.max(max, digit);
            }
        }
        return max;
    }
}
