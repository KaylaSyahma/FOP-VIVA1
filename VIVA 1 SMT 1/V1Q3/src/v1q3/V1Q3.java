package v1q3;

import java.util.Scanner;

public class V1Q3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int billCount = 0;
        double totalBilled = 0;
        double highestBill = 0;
        String highestAccount = "";

        // Step 1: keep processing accounts until the user enters "EXIT"
        while (true) {
            System.out.println("Enter account number (or EXIT to stop): ");
            String account = sc.nextLine().trim();

            if (account.equalsIgnoreCase("EXIT")) {
                break;
            }

            // Step 2: Normalization
            account = account.replace(" ", "").replace("-", "").toUpperCase();

            // Step 3: Validation
            boolean isValid = true;

            if (account.length() != 9) {
                isValid = false;
            } else {
                char first = account.charAt(0);
                if (first != 'R' && first != 'C' && first != 'I') {
                    isValid = false;
                }
                // only check digits when length is safe
                for (int i = 1; i < 9; i++) {
                    if (!Character.isDigit(account.charAt(i))) {
                        isValid = false;
                        break;
                    }
                }
            }

            if (!isValid) {
                System.out.println("Error: Invalid account number. Bill skipped.");
                continue;
            }

            // Step 4: Usage as integer
            int usage = 0;
            while (true) {
                System.out.println("Enter electricity usage (kWh): ");

                if (sc.hasNextInt()) {
                    usage = sc.nextInt();
                    sc.nextLine(); // clear leftover newline
                    if (usage > 0) {
                        break;
                    }
                    System.out.println("Error: Usage must be greater than 0.");
                } else {
                    sc.nextLine(); // discard bad input
                    System.out.println("Error: Usage must be a whole number.");
                }
            }

            // Step 5: Charges by category
            char category = account.charAt(0);
            String categoryName = "";
            double energy = 0;
            double tax = 0;

            switch (category) {
                case 'R':
                    categoryName = "Residential";
                    if (usage <= 200) {
                        energy = usage * 0.218;
                    } else if (usage <= 300) {
                        energy = 200 * 0.218 + (usage - 200) * 0.334;
                    } else if (usage <= 600) {
                        energy = 200 * 0.218 + 100 * 0.334 + (usage - 300) * 0.516;
                    } else {
                        energy = 200 * 0.218 + 100 * 0.334 + 300 * 0.516
                                + (usage - 600) * 0.546;
                    }
                    
                     // minimum charge RM 3.00
                    if (energy < 3.00) {
                        energy = 3.00;
                    }

                    // service tax only if usage > 600
                    if (usage > 600) {
                        tax = energy * 0.08;
                    }
                    break;
                    
                case 'C' :
                    categoryName = "Commercial";
                    energy = usage * 0.435;
                    tax = energy * 0.08;
                    break;
                    
                case 'I' :
                    categoryName = "Industrial";
                    energy = usage * 0.380;
                    
                    if (energy < 600.00) {
                        energy = 600.00;
                    }
                    
                    tax = energy * 0.08;
                    break;
               }   
            
                    double total = energy + tax;
            
            // Step 6 : Output for each bill
            System.out.println("Bill Summary");
            System.out.println("Account Number : " + account);
            System.out.println("Category : " + categoryName);
            System.out.println("Usage : " + usage + "kwh");
            System.out.printf("Energy Charge : RM %.2f%n", energy);
            System.out.printf("Service Tax : RM %.2f%n", tax);
            System.out.printf("Total Bill : RM %.2f%n", total);
            
        
            // Step 7 : Final report
            billCount++;
            totalBilled = totalBilled + total;
            if (total > highestBill) {
                highestBill = total;
                highestAccount = account;
                }
            
            // Requirement 6: final report after EXIT
        System.out.println();
        System.out.println("FINAL REPORT");
        System.out.println("Bills processed : " + billCount);
        if (billCount > 0) {
            System.out.printf("Total billed    : RM %.2f%n", totalBilled);
            System.out.printf("Highest bill    : RM %.2f (%s)%n", highestBill, highestAccount);
        } else {
            System.out.println("No bills were issued.");
        }
            
        }
        
    }
    }
