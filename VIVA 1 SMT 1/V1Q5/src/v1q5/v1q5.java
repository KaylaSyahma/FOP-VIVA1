/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package v1q5;


import java.util.Scanner;

public class v1q5 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int N;
        int K;

        // Input validation
        do {
            System.out.print("Enter size N: ");
            N = input.nextInt();

            System.out.print("Enter band thickness K: ");
            K = input.nextInt();

            if (N < 5 || N > 25 || N % 2 == 0) {
                System.out.println("Error: N must be an odd number between 5 and 25.");
            }

            if (K < 1 || K > 3 || K > N / 2) {
                System.out.println("Error: K must be between 1 and 3 and not more than N / 2.");
            }

        } while (N < 5 || N > 25 || N % 2 == 0 ||
                 K < 1 || K > 3 || K > N / 2);

        // Find the centre
        int mid = N / 2;

        // Print the diamond pattern
        for (int r = 0; r < N; r++) {

            for (int c = 0; c < N; c++) {

                // Calculate Manhattan distance
                int d = Math.abs(r - mid) + Math.abs(c - mid);

                char symbol;

                if (d == 0) {
                    symbol = '@';
                }
                else if (d <= mid) {

                    if ((d / K) % 3 == 0) {
                        symbol = '#';
                    }
                    else if ((d / K) % 3 == 1) {
                        symbol = '+';
                    }
                    else {
                        symbol = 'o';
                    }

                }
                else if (r < mid && c < mid) {
                    symbol = '/';
                }
                else if (r < mid && c > mid) {
                    symbol = '\\';
                }
                else if (r > mid && c < mid) {
                    symbol = '\\';
                }
                else {
                    symbol = '/';
                }

                System.out.print(symbol);
            }

            System.out.println();
        }

        input.close();
    }
}
