/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package v1q2;

/**
 *
 * @nauraariyanti
 */ 
import java.util.Scanner;

public class V1Q2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //Get monthly deposit
        System.out.println("Enter monthly deposit (RM): ");
        double deposit = sc.nextDouble();
        
        //Get annual interest
        System.out.println("Enter annual interest rate:");
        double annualRate = sc.nextDouble();
        
        //Convert annual interest rate to mobthly rate
        //Ask if user wants to make a withdrawal
        //Option yes -> get details
        //Start the saving balance at 0
        //Simulate savings growth
        //Check withdrawal
        //Apply compound interest
        //Display the amount at the end of 12 months
        //Display the amount at the end of 24 months
        
        sc.close();
    }
    
}
