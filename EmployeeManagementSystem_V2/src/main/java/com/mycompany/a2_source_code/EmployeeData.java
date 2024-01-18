package com.mycompany.a2_source_code;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import java.util.Scanner;
import java.util.*;

public class EmployeeData {
    public static Scanner scan = new Scanner(System.in);
    
    private String[] names;
    private int[] hours;
    private double[] wages;
    @SuppressWarnings("unused")
	private int SkillLevel;
    
    public EmployeeData() {
        
    }
    
    public EmployeeData(String[] names, int[] hours, double[] wages) {
        this.names = names;
        this.hours = hours;
        this.wages = wages;
    }
    
    // input data and store them in arrays
    public void InputData(int MaxDigit) {
        int SkillLevel;
        names = new String[MaxDigit];
        hours = new int[MaxDigit];
        wages = new double[MaxDigit];
        
        for(int i=0;i<MaxDigit;i++){
            System.out.print("Please enter employee name "+ (i+1) +": ");
            String name = scan.nextLine();
            
            // name validation
            while (!isValidNameFormat(name) || !isValidNameLength(name)) {
                if (!isValidNameFormat(name)) {
                    System.out.println("The required full name format is: FirstName (MiddleName (if any)] SurName. e.g: Richard (Den) Anderson You entered an invalid name.");
                }
                if (!isValidNameLength(name)) {
                    System.out.println("FirstName [MiddleName (if any)] SurName must not exceed 25 letters \nYou entered an invalid name.");
                }

                System.out.print("\nPlease input a valid name: ");
                name = scan.nextLine();
            }

            names[i] = name;
            
            System.out.print("Please enter skill level (1,2,3): ");
            SkillLevel = scan.nextInt();
            
            // skill level validation
            while(!isValidSkillLevel(SkillLevel)) {               
                System.out.print("Invalid Skill Level please re-enter: ");
                SkillLevel = scan.nextInt();
                scan.nextLine();
            }
            
            System.out.print("Please enter worked hours: ");
            int hour = scan.nextInt();
            scan.nextLine();
            
            // hour validation
            while (!isValidHours(hour)) {
                System.out.print("\nWarning!. Worked hours must be between 0 and 50 weekly.\nPlease enter worked hours: ");
                hour = scan.nextInt();
                scan.nextLine();
            }
            hours[i] = hour;
            wages[i] = FindCorrectWage(SkillLevel, hour);
            
            System.out.println();
        }
        
        // store collected arrays to a object
        EmployeeData emp = new EmployeeData(names, hours, wages);
        System.out.println();
        System.out.println(MaxDigit +" employes' data have been entered and stored in arrays.");
    }
    
    // name format validation
    private static boolean isValidNameFormat(String name) {
        String namePattern = "[a-zA-Z]+( [a-zA-Z]+){1,2}";
        return name.matches(namePattern);
    }
    
    // name length validation
    private static boolean isValidNameLength(String name) {
        return name.length() <= 25;
    }

    // worked hours validation
    private static boolean isValidHours(int hours) {
        return hours >= 0 && hours <= 50;
    }
    
    // skill level validation
    private static boolean isValidSkillLevel(int level) {
        return level >= 1 && level <= 3;
    }
    
    // find the wage
    private double FindCorrectWage(int Slevel, int Whours){
        int wage;
        double totalWage = 0;
        
        switch (Slevel){
            case 1:
                wage = 50;
                totalWage = calcWage(Whours, wage);
                break;
            case 2:
                wage = 70;
                totalWage = calcWage(Whours, wage);
                break;
            case 3:
                wage = 100;
                totalWage = calcWage(Whours, wage);
                break;
        }

        return totalWage;
    }
    
    // calculate the wage using the wage selected in FindCorrectWage() method
    private double calcWage(int Whours, int wage){
        double totalWage;
        int otHours;
        
        if (Whours > 40) {
            otHours = Whours - 40;
            totalWage = 1.5*wage*otHours + 40*wage;
        } else {
            totalWage = wage*Whours;
        }
        
        return totalWage;
    }  
    
    public void sortByName() {
        Integer[] PrevIndex = new Integer[names.length];
         
        // save indexes of the names array to PrevIndex array
        for(int i=0;i<names.length;i++){
            PrevIndex[i] = i;
        }
         
        Arrays.sort(PrevIndex, Comparator.comparing(nameIndex -> names[nameIndex]));
         
        // temporary arrays to hold sorted data
        String[] ParSortedNames = new String[names.length];
        int[] ParSortedHours = new int[hours.length];
        double[] ParSortedWages = new double[wages.length];
         
        for (int i = 0; i < names.length; i++) {
           ParSortedNames[i] = names[PrevIndex[i]];
           ParSortedHours[i] = hours[PrevIndex[i]];
           ParSortedWages[i] = wages[PrevIndex[i]];
        }

        // save sorted and saved arrays to the original arrays
        names = ParSortedNames;
        hours = ParSortedHours;
        wages = ParSortedWages;
         
        display();
    }
    
    public void sortByHours() {
        Integer[] PrevIndex = new Integer[names.length];
        
        // save indexes of the hours array to PrevIndex array
        for(int i=0;i<hours.length;i++){
             PrevIndex[i] = i;
        }
        
        Arrays.sort(PrevIndex, Comparator.comparing(hourIndex -> hours[hourIndex]));
        
        // temporary arrays to hold sorted data
        String[] ParSortedNames = new String[names.length];
        int[] ParSortedHours = new int[hours.length];
        double[] ParSortedWages = new double[wages.length];
        
        for (int i = 0; i < names.length; i++) {
            ParSortedNames[i] = names[PrevIndex[i]];
            ParSortedHours[i] = hours[PrevIndex[i]];
            ParSortedWages[i] = wages[PrevIndex[i]];
        }
        
        // save sorted and saved arrays to the original arrays
        names = ParSortedNames;
        hours = ParSortedHours;
        wages = ParSortedWages;
        
        display();
    }
    
    public void searchByName() {
        System.out.print("Please enter employee name: ");
        String name = scan.nextLine().toLowerCase(); // save name in lower case letters
        int index = -1;
        
        // convert the names array to lower case and compare with the name
        for(int i=0;i<names.length;i++) {
            if (names[i].toLowerCase().equals(name)) {
                index = i;
                break;
            }
        }
        
        if (index != -1) {
            DisplayByIndex(index);
        } else {
            System.out.println("Name not found. Please try again.");
        }

    }
    
    public void searchByHours() {
        System.out.print("Please enter a specific number of worked hours: ");
        int hour = scan.nextInt();
        scan.nextLine();
        int[] index = new int[hours.length];
        int RealIndex = 0;
        
        for(int i=0;i<hours.length;i++){
            if (hours[i] < hour) {
                index[RealIndex] = i;
                RealIndex++;
            }
        }
        if (RealIndex > 0) {
            System.out.println("The following employees have less than "+hour+" worked hours: ");
            for (int j=0;j<RealIndex;j++) {
                System.out.println("\t\t\t\t\t\t\t"+ names[index[j]] + " " + hours[index[j]] + " hours");
            }
        } else {
            System.out.println("zero employees found under "+ hour +" working hours.");
        }
        
    }
    
    public void statistics() {
        System.out.println("\t\t\t\t\t\t\tA Simple Statistics");
        System.out.println("\t\t\t\t\t\t===================================\n");
        System.out.println("\t\t\tThe employee has lowest worked hours: "+ FindMin());
        System.out.println("\t\t\tThe employee has highest worked hours: "+ FindMax());
        System.out.println("\t\t\tThe median value of worked hours: "+ FindAvg());
    }
    
    // find max hours
    private  String FindMax() {
        int max = MIN_VALUE;
        int MaxIndex = -1;
        
        for (int i=0;i<hours.length;i++) {
            if(hours[i] > max) {
                max = hours[i];
                MaxIndex = i;
            }
        }
        
        return names[MaxIndex] + ", " + max + " hours";
    }
    
    // find min hours
    private String FindMin() {
        int min = MAX_VALUE;
        int MinIndex = 0;
        
        for (int i=0;i<hours.length;i++) {
            if(hours[i] < min) {
                min = hours[i];
                MinIndex = i;
            }
        }
        
        return names[MinIndex] + ", " + min + " hours";
    }
    
    // find average hours
    private String FindAvg() {
        int total = 0;
        
        for (int i=0;i<hours.length;i++) {
            total += hours[i];
        }
   
        return (total/hours.length) + " hours";
    }
    
    // tabular format employee details table
    public void display() {
        System.out.println("\n\n\t\t\t\t\t\t\t\tEmployee Worked Hours & Wage\n\t\t\t\t\t\t\t===============================================");
        System.out.print("");
        System.out.printf("%-30s %-15s %-15s%n", "\t\t\t\t\t\t\t\tEmployee", "Worked Hours", "Wage");
        System.out.println("\t\t\t\t\t\t\t------------------------------------------------");
        
        for(int i=0;i<names.length;i++) {
            System.out.printf("%-30s %-15d %-15.2f%n", "\t\t\t\t\t\t\t\t"+ names[i], hours[i], wages[i]);
        }
        
        System.out.println("\n\t\t\t\t\t\t\tTotal: "+ names.length + " entries");
    }
    
    // show data according to the matching index
    private void DisplayByIndex(int index) {
        System.out.println("\t\t\t\t\t\t\t"+ names[index] + " - Worked hours: "+ hours[index] + " - Wage: "+ wages[index]);
        System.out.println("\t\t\t\t\t\t\t----------------------------------------------");
    }
}
