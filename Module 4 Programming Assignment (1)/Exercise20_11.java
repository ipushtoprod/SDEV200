import java.io.*;
import java.util.Stack;

public class Exercise20_11 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Exercise20_11 filename");
            System.exit(1);
        }

        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("File " + args[0] + " does not exist");
            System.exit(2);
        }

        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            String line;
            Stack<Character> stack = new Stack<>();
            boolean isCorrect = true;
            
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                for (char ch : line.toCharArray()) {
                    if (ch == '(' || ch == '{' || ch == '[') {
                        stack.push(ch);
                    } else if (ch == ')' || ch == '}' || ch == ']') {
                        if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                            isCorrect = false;
                        }
                    }
                }
            }
            if (!stack.isEmpty()) {
                isCorrect = false;
            }            
            System.out.println(isCorrect ? "Correct grouping pairs" : "Incorrect grouping pairs");            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') || (opening == '{' && closing == '}') || (opening == '[' && closing == ']');
    }
}
