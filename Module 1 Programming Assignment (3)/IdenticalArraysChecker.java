import java.util.Scanner;

public class IdenticalArraysChecker {
    /** Reads the 3x3 */
    public static int[][] readArray(Scanner input) {
        int[][] matrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = input.nextInt();
            }
        }
        return matrix;
    }
    
    /** Returns true if m1 and m2 are identical */
    public static boolean equals(int[][] m1, int[][] m2) {
        if (m1.length != m2.length || m1[0].length != m2[0].length) {
            return false;
        }

        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[i].length; j++) {
                if (m1[i][j] != m2[i][j]) {
                    return false;
                }
            }
        }
        
        return true;
    }
    /** the main */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter the first 3x3 array:");
        int[][] m1 = readArray(input);

        System.out.println("Enter the second 3x3 array:");
        int[][] m2 = readArray(input);
        
        if (equals(m1, m2)) {
            System.out.println("The two arrays are identical");
        } else {
            System.out.println("The two arrays are not identical");
        }
    }
}
