import java.util.Scanner;

public class CreditCardValidator {

    //assuming its ok to use the given comments from the question itself as comments for this

    /** Return true if the card number is valid */
    public static boolean isValid(long number) {
        int size = getSize(number);
        return (size >= 13 && size <= 16) &&
               (prefixMatched(number, 4) || prefixMatched(number, 5) ||
                getPrefix(number, 2) == 37 || prefixMatched(number, 6)) &&
               ((sumOfDoubleEvenPlace(number) + sumOfOddPlace(number)) % 10 == 0);
    }

    /** Return the number of digits in d */
    public static int getSize(long d) {
        return String.valueOf(d).length();
    }

    /** Return true if the number d is a prefix for number */
    public static boolean prefixMatched(long number, int d) {
        return String.valueOf(number).startsWith(String.valueOf(d));
    }

    /** Return the first k number of digits from number. If the
     * number of digits in number is less than k, return number. */
    public static long getPrefix(long number, int k) {
        String numStr = String.valueOf(number);
        if (numStr.length() < k) {
            return number;
        } else {
            return Long.parseLong(numStr.substring(0, k));
        }
    }

    /** Get the result from Step 2 */
    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String numStr = String.valueOf(number);
        for (int i = numStr.length() - 2; i >= 0; i -= 2) {
            int digit = Integer.parseInt(numStr.substring(i, i + 1));
            sum += getDigit(digit * 2);
        }
        return sum;
    }

    /** Return this number if it is a single digit, otherwise,
     * return the sum of the two digits */
    public static int getDigit(int number) {
        if (number < 10) {
            return number;
        } else {
            return (number % 10) + (number / 10);
        }
    }

    /** Return sum of odd-place digits in number */
    public static int sumOfOddPlace(long number) {
        int sum = 0;
        String numStr = String.valueOf(number);
        for (int i = numStr.length() - 1; i >= 0; i -= 2) {
            sum += Integer.parseInt(numStr.substring(i, i + 1));
        }
        return sum;
    }

    /** the main */
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a credit card number as a long integer: ");
        long cardNumber = input.nextLong();

        if (isValid(cardNumber)) {
            System.out.println(cardNumber + " is valid");
        } else {
            System.out.println(cardNumber + " is invalid");
        }
        input.close();
    }
}
