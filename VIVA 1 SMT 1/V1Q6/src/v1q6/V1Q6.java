/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package v1q6;

/**
 *
 * @author LENOVO
 */
import java.util.Scanner;
public class V1Q6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    // Step 1 : Command string
         System.out.println("Enter flight commands: ");
         String commands = sc.nextLine();

     // Step 2 : Check if valid
        boolean valid = true;
     
        if (commands.length() == 0) {
            valid = false;
        }
        
     // first character cannot be digit
        if (valid && commands.charAt(0) >= '0' && commands.charAt(0) <= '9') {   // FIX: added valid &&
         valid = false; }
        
        for(int i = 0; i < commands.length(); i++) {
            char ch = commands.charAt(i);
        
    // stop if a digit comes right after another digit
         if ( i > 0 && ch >= '0' && ch <= '9'
                && commands.charAt(i - 1) >= '0' && commands.charAt(i - 1) <= '9') {
            valid = false;
        }
        
    // stop if digit is 0 or 1
        if (ch >= '0' && ch <= '9') {
        if (ch == '0' || ch == '1') {
            valid = false;
        }
   // stop if any character is not one of UDLRudlr or a digit.
    }   else if (ch != 'U' && ch != 'D' && ch != 'L' && ch != 'R'
            && ch != 'u' && ch != 'd' && ch != 'l' && ch != 'r') {
        valid = false;
    }
        }   

         if (valid == false) {
            System.out.println("Invalid Command String");
            return;
        }
        
         // state
        int ax = 0, ay = 0;   // Drone A (uppercase)
        int bx = 0, by = 0;   // Drone B (lowercase)
        int blocked = 0, collisions = 0, nearMisses = 0, maxSep = 0, totalSteps = 0;
        
    // Step 3 : Blocked steps
        for (int i = 0; i < commands.length(); i++) {
        char ch = commands.charAt(i);
        if (ch >= '0' && ch <= '9')
                continue;
        
    /* A command may be followed by ONE digit from 2 to 9, meaning the command runs
    that many times in a row (R3 = R, R, R). Each repetition is a separate step. */
    
        int times = 1;
        if (i + 1 < commands.length()&& commands.charAt(i + 1) >= '0' && commands.charAt(i + 1) <= '9') {
        times = commands.charAt(i + 1) - '0';
        }
        
    // Drone A : Uppercase (UDLR) and Drone B : Lowercase (udlr)
    // U / u = +1 Y, D / d = −1 Y, L / l = −1 X, R / r = +1 X.

        int dx = 0;
        int dy = 0;
        
        if (ch == 'U' || ch == 'u') dy = 1;
        if (ch == 'D' || ch == 'd') dy = -1;
        if (ch == 'L' || ch == 'l') dx = -1;
        if (ch == 'R' || ch == 'r') dx = 1;
        
    /*if a step would move a drone outside −3 … 3 on either axis, the drone
stays where it is   */
                        
        for (int t = 0; t < times; t++) {
            totalSteps++;
          if (ch >= 'A' && ch <= 'Z') {
              // Drone A
              if (ax + dx < -3 || ax + dx > 3 || ay + dy < -3 || ay + dy > 3) {
                  blocked++;
              } else {
                  ax = ax + dx;
                  ay = ay + dy;
              }
          } else {
              // Drone B
              if (bx + dx < -3 || bx + dx > 3 || by + dy < -3 || by + dy > 3) {
                  blocked++;
              } else {
                 bx = bx + dx;
                 by = by + dy;  
              }
  
          }  
        
    // Step 4 : Compare drone after EVERY steps (including blocked ones)
        int diffX = Math.abs(ax - bx);   
        int diffY = Math.abs(ay - by);

        if (diffX == 0 && diffY == 0) {
        collisions++;                 
        
        } else if (diffX <= 1 && diffY <= 1) {
        nearMisses++;                  
        }

        if (diffX + diffY > maxSep) {
        maxSep = diffX + diffY;     
        }
        }   // closes t loop
        }   
        
    // Step 5 : Shortest Route
        String routeA = "";
        
        if (ax == 0 && ay == 0) {
            routeA = "HOME";
      } else {
    
    for (int k = 0; k < ax; k++)  routeA = routeA + "R";   // x positif
    for (int k = 0; k < -ax; k++) routeA = routeA + "L";   // x negatif
    for (int k = 0; k < ay; k++)  routeA = routeA + "U";   // y positif
    for (int k = 0; k < -ay; k++) routeA = routeA + "D";   // y negatif
}

        String routeB = "";
        
        if (bx == 0 && by == 0) {
            routeB = "HOME";
      } else {
  
    for (int k = 0; k < bx; k++)  routeB = routeB + "r";
    for (int k = 0; k < -bx; k++) routeB = routeB + "l";
    for (int k = 0; k < by; k++)  routeB = routeB + "u";
    for (int k = 0; k < -by; k++) routeB = routeB + "d";
}
        // Output
        System.out.println("Total Steps: " + totalSteps);
        System.out.println("Blocked Moves: " + blocked);
        System.out.println("Collisions: " + collisions);
        System.out.println("Near Misses: " + nearMisses);
        System.out.println("Maximum Separation: " + maxSep);
        System.out.println("Drone A final position: (" + ax + "," + ay + ") Route: " + routeA);
        System.out.println("Drone B final position: (" + bx + "," + by + ") Route: " + routeB);
        }   // closes main
}           // closes class