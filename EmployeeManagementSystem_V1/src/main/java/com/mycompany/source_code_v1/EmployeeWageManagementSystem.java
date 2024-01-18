package com.mycompany.source_code_v1;

import java.util.Scanner;
import java.util.Stack;

public class EmployeeWageManagementSystem {   
    static Stack<Wage> empStack = new Stack<>();
    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
    	int N = 4; // change N value to add the desired number of employees
        
        for (int i = 1; i <= N; i++) {
            Wage w = new Wage();
            int empNum = i;
            
            //getting user inputs
            System.out.print("\nEnter the name of the employee "+ i +": ");
            String name = NameValidate(empNum);
            
            System.out.print("Enter the skill level (1, 2, 3) of the employee, "+ name +": ");
            int Slevel = SlevelValidate(name);

            System.out.print("Enter the hours (integer) worked by the employee, "+ name +": ");
            int Whours = WhoursValidate(name);
            
            System.out.printf("The wage of the employee %s <Level %d> for %d hours is $%.2f%n", name, Slevel, Whours, w.FindCorrectWage(Slevel, Whours));

            
            w.seteName(name);
            w.setsLevel(Slevel);
            w.setWorkedHours(Whours); 
            
            empStack.add(w);
        }
        
        //displaying the data 
        System.out.println("\nBelow is the Data Set");
        System.out.println("------------------------------------------------------------------------------");
        System.out.format("%-20s %-15s %-15s %-25s%n", "Employee Name", "Skill Level", "Hours Worked", "Over Time (OT) Applicable\n");

        for (Wage emp: empStack) {            
            System.out.format("%-20s %-15s %-15s %-25s%n",
                    emp.geteName(), emp.getsLevel(), emp.getWorkedHours(), OTCheck(emp));
        }
        
        System.out.println("\nStatictical Information Bar Chart");
        System.out.println("------------------------------------------------------------------------------\n");
        System.out.println("Maximum of the salaries: "+ MaxWage());
        System.out.println("Minimum of the salaries: "+ MinWage());       
        System.out.println("Average Salary: $"+ AvgSal());
        System.out.println();
        System.out.println("Bar chart of the length of worked hours");
        System.out.println("------------------------------------------------------------------------------\n");
        System.out.print("Number of employees who worked less than 10 hours: ");
        NumEmpWorkedEmp(1);
        System.out.println();
        System.out.print("Number of employees who worked 10-20 hours: ");
        NumEmpWorkedEmp(2);
        System.out.println();
        System.out.print("Number of employees who worked above 20 hours: ");
        NumEmpWorkedEmp(3);
        System.out.println();       
        System.out.println("\nThank you for using the Employee Wage Recording System.");
    }
    //calculate the number of employees worked below 10 hours, between 10 to 20 hours above 20 hours
    private static void NumEmpWorkedEmp(int number){
        int LessThanTen = 0;
        int LessThanTwenty = 0;
        int AboveTwenty = 0;
        
        for(Wage emp: empStack){
            if (emp.getWorkedHours() < 10) {
                LessThanTen++;
            } else if (emp.getWorkedHours() >= 10 && emp.getWorkedHours() <= 20) {
                LessThanTwenty++;
            } else if (emp.getWorkedHours() > 20) {
                AboveTwenty++;
            }
        }    
        
        switch (number) {
            case 1:
                for(int count=1;count<=LessThanTen;count++){
                    System.out.print("*");
                }
                break;
            case 2:
                for(int count=1;count<=LessThanTwenty;count++){
                    System.out.print("*");
                }
                break;
            case 3:
                for(int count=1;count<=AboveTwenty;count++){
                    System.out.print("*");
                }
                break;
        }
    }
    //calculate the maximum wage 
    private static String MaxWage() {
    double max = Double.MIN_VALUE;
    String empName = null;

    for (Wage emp : empStack) {
        if (emp.geteWage() > max) {
            max = emp.geteWage();
            empName = emp.geteName();
        }
    }

    return String.format("%.2f (earned by) %s", max, empName);
    }
    //calculate the minimum wage
    private static String MinWage() {
        double min = Double.MAX_VALUE;
        String empName = null;

        for (Wage emp : empStack) {
            if (emp.geteWage() < min) {
                min = emp.geteWage();
                empName = emp.geteName();
            }
        }

        return String.format("%.2f (earned by) %s", min, empName);
    }
    //calculate the average salary
    private static double AvgSal(){
        double total = 0;
        
        for(Wage emp: empStack) {
            total += emp.geteWage();
        }
        
        double avg = total / empStack.size();
        return Double.parseDouble(String.format("%.2f", avg));
    }
    //check whether the employee has ot hours 
    private static String OTCheck(Wage e){                      
        if(e.getOTHours()> 0){
                return "Yes!";
            } else {
                return "No!";
            }
    }
    //check whether the name is blank and calls containsLetters function
    private static String NameValidate(int empNum) {
        String name;
        while (true) {
            name = scan.nextLine();
            if (name.isBlank()) {
                System.out.println("ERROR! Employee Name Can't be Blank.");
                System.out.print("Enter the name of the employee "+ empNum +": ");
            } else if (!isValid(name)) {
                System.out.println("ERROR! Employee Name Can't include Numbers.");
                System.out.print("Enter the name of the employee "+ empNum +": ");
            } else {
                break;
            }
        }
        return name;
    }
    //checks the name for letters after calling in NameValidation function
    private static boolean isValid(String name) {
        return name.matches("[a-zA-Z]+");
    }
    //check whether the skill level between 1 to 3
    private static int SlevelValidate(String name) {
        int Slevel;
        while (true) {          
                Slevel = scan.nextInt();
                if (Slevel < 1 || Slevel > 3) {
                    System.out.println("ERROR! Skill level should not be "+ Slevel);
                    System.out.print("Enter the skill level (1, 2, 3) of the employee, "+ name +": ");
                } else {
                	scan.nextLine();
                    break;
                }
        }
        return Slevel;
    }
    //check whether the working hours not negative
    private  static int WhoursValidate(String name) {
        int Whours;
        while (true) {
                Whours = scan.nextInt();
                if (Whours < 0) {
                    System.out.println("ERROR! Hours worked cannot be negative.");
                    System.out.print("Enter the hours (integer) worked by the employee, "+ name +": ");
                } else {
                	scan.nextLine();
                    break;
                }
        }
        return Whours;
    }
}