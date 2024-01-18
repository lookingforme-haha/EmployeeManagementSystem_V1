package com.mycompany.a2_source_code;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static EmployeeData emp = new EmployeeData();
    
    // display the menu for selection
    public static void DisplayMenu(int MaxDigit){
        Scanner scan = new Scanner(System.in);
        int selection = -1;
        System.out.println("\n\n\t\t\t\t\t\t\t\tEmployee Data Management System\n\t\t\t\t\t\t\t===============================================");
        do{
            System.out.println("\n1. Input & Validate date");
            System.out.println("2. Display");
            System.out.println("3. Sort by name");
            System.out.println("4. Sort by hours");
            System.out.println("5. Search by name");
            System.out.println("6. Search by hours");
            System.out.println("7. Statictics");
            System.out.println("8. Exit");
          
            System.out.print("Enter an option from 1-8: ");            
            try {
                selection = scan.nextInt();
                SelectionValidateAndProcess(selection, MaxDigit);
            } catch (InputMismatchException e) {
                System.out.println("\noption 1-8 must be entered\n");
                scan.nextLine();
            }
            
        } while (selection != 8);
    }
    
    // navigate to the suitable method of EmployeeData class 
    private static void SelectionValidateAndProcess(int selection, int MaxDigit) {
        if (selection > 8 || selection < 1){
            System.out.println("\noption 1-8 must be entered\n");
        } else {
            switch (selection) {
                case 1:
                    emp.InputData(MaxDigit);
                    break;
                case 2:
                    emp.display();
                    break;
                case 3:
                    emp.sortByName();
                    break;
                case 4:
                    emp.sortByHours();
                    break;
                case 5:
                    emp.searchByName();
                    break;
                case 6:
                    emp.searchByHours();
                    break;
                case 7:
                    emp.statistics();
                    break;
            }
        }
    }
}
