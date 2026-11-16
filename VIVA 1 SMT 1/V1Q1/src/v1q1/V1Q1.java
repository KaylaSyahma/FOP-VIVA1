package v1q1;

import java.util.Scanner;

public class V1Q1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Get original originalMessage 
        System.out.print("Enter Original Message: ");
        String originalMessage = input.nextLine();
        
        // return the encoded message
        StringBuilder encodedMessage = new StringBuilder();
        // Step 2: Process each character
        int i = 0;

        while (i < originalMessage.length()) {
            char ch = originalMessage.charAt(i);

            // Step 3: Lowercase letters
            // Character = java built-in class
            if (Character.isLowerCase(ch)) {
                // index karakter dibagi 5, ambil sisanya, tambah 1
                int shift = (i % 5) + 1;
                char result = (char) ('a' + (ch - 'a' + shift) % 26); // 26 letters in the alphabet
                // append = menambahkan karakter ke dalam StringBuilder
                encodedMessage.append(result);
                i++;
            }

            // Step 4: Uppercase letters
            else if (Character.isUpperCase(ch)) {
                char mirror = (char) ('z' - (ch - 'A'));
                encodedMessage.append("<").append(mirror).append(">");
                i++;
            }

            // Step 5: Digit runs
            else if (Character.isDigit(ch)) {
                int count = 1;

                while (i + count < originalMessage.length()
                        && originalMessage.charAt(i + count) == ch) {
                    count++;
                }

                int digit = ch - '0';
                encodedMessage.append(9 - digit);

                if (count > 1) {
                    encodedMessage.append("x").append(count);
                }

                i += count;
            }

            // Step 6: Space runs
            else if (ch == ' ') {
                encodedMessage.append("_");

                while (i < originalMessage.length()
                        && originalMessage.charAt(i) == ' ') {
                    i++;
                }
            }

            // Symbols
            else if (ch == '+' || ch == '-' || ch == '*'
                    || ch == '/' || ch == '=') {

                String symbols = "+-*/=";
                int position = symbols.indexOf(ch);
                char previous = symbols.charAt((position + 4) % 5);

                encodedMessage.append(previous);
                i++;
            }

            // Other characters remain unchanged
            else {
                encodedMessage.append(ch);
                i++;
            }
        }
        System.out.println("Encoded Message: " + encodedMessage);
        System.out.println("Original Length: " + originalMessage.length() + " | Encoded Length: " + encodedMessage.length());
        input.close();
    }
}
