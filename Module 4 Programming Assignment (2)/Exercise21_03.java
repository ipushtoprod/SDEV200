import java.io.*;
import java.util.*;

public class Exercise21_03 {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java Exercise21_03 filename");
            System.exit(1);
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("File " + args[0] + " does not exist");
            System.exit(2);
        }
        System.out.println("The program is");
        printFileContent(file);
        System.out.println("The number of keywords in the program is " + countKeywords(file));
    }

    public static void printFileContent(File file) throws IOException {
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    public static int countKeywords(File file) throws Exception {
        // Array of all Java keywords + true, false and null
        String[] keywordArray = {
            "abstract", "assert", "boolean", "break", "byte", "case", "catch",
            "char", "class", "const", "continue", "default", "do", "double",
            "else", "enum", "extends", "final", "finally", "float", "for",
            "goto", "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static", "strictfp",
            "super", "switch", "synchronized", "this", "throw", "throws",
            "transient", "try", "void", "volatile", "while", "true", "false", "null"
        };

        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordArray));
        int count = 0;

        Scanner input = new Scanner(file);
        boolean inBlockComment = false;
        boolean inString = false;

        while (input.hasNextLine()) {
            String line = input.nextLine().trim();

            if (line.length() == 0) continue;

            if (line.contains("/*")) {
                inBlockComment = true;
            }
            if (line.contains("*/")) {
                inBlockComment = false;
                continue;
            }
            if (inBlockComment) continue;

            if (line.contains("//")) {
                line = line.substring(0, line.indexOf("//"));
                if (line.length() == 0) continue;
            }

            Scanner lineScanner = new Scanner(line);

            while (lineScanner.hasNext()) {
                String token = lineScanner.next();
                if (token.startsWith("\"")) {
                    if (!token.endsWith("\"") || token.length() == 1) {
                        inString = true;
                    }
                }
                if (inString && token.endsWith("\"")) {
                    inString = false;
                    continue;
                }
                if (inString) continue;

                if (keywordSet.contains(token)) {
                    count++;
                }
            }
            lineScanner.close();
        }
        input.close();
        return count;
    }
}
