package com.mycompany.source_code_v1;

public class Wage {
    private double eWage;
    private int sLevel;
    private String eName;
    private int OTHours;
    private int workedHours;
    
    public Wage(){
        
    }
    
    public Wage(int sLevel, String eName, int OTHours, int workedHours) {
        this.sLevel = sLevel;
        this.eName = eName;
        this.OTHours = OTHours;
        this.workedHours = workedHours;
    } 
    
    public void setsLevel(int sLevel) {
        this.sLevel = sLevel;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }
    
    public double geteWage() {
        return eWage;
    }

    public int getsLevel() {
        return sLevel;
    }

    public String geteName() {
        return eName;
    }

    public int getOTHours() {
        return OTHours;
    }
    
    public int getWorkedHours(){
        return workedHours;
    }
    //calculates the total wage of a employee and returns the value
    public double FindCorrectWage(int Slevel, int Whours){
        int wage = 0;
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
    //wage calculating algorithm 
    public double calcWage(int Whours, int wage){
        double totalWage = 0;
        int otHours = 0;
        
        if (Whours > 40) {
            otHours = Whours - 40;
            this.OTHours = otHours;
            totalWage = 1.5*wage*otHours + 40*wage;
        } else {
            totalWage = wage*Whours;
        }
        
        this.eWage = totalWage;
        return totalWage;
    }
}


