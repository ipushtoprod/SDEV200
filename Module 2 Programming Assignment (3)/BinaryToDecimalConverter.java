import java.util.Scanner;

public class BinaryToDecimalConverter {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a binary string: ");
        String binaryString = input.nextLine();
        
        try {
            int decimalValue = bin2Dec(binaryString);
            System.out.println("Decimal value: " + decimalValue);
        } catch (BinaryFormatException e) {
            System.out.println(e.getMessage());
        }
        
        input.close();
    }

    public static int bin2Dec(String binaryString) throws BinaryFormatException {
        //regex to see if it matches the formatting
        if (!binaryString.matches("[01]+")) {
            throw new BinaryFormatException(binaryString + " is not a binary string");
        }

        int decimalValue = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            char ch = binaryString.charAt(i);
            decimalValue = decimalValue * 2 + (ch - '0');
        }
        
        return decimalValue;
    }
}
