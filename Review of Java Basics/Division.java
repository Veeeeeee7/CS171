import java.util.ArrayList;
import java.util.Scanner;

public class Division {

    public static String fractionAsDecimal(int numerator, int denominator) {

        ///////////////////////////////////////////
        // ADD YOUR CODE HERE (AND NOWHERE ELSE) //
        ///////////////////////////////////////////
        StringBuilder answer = new StringBuilder();
        answer.append(numerator / denominator + ".");
        int remainder = numerator % denominator;

        ArrayList<Integer> remainders = new ArrayList<>();
        StringBuilder decimal = new StringBuilder();

        while (remainder != 0) {
            if (remainders.contains(remainder)) {
                int repeatIndex = remainders.indexOf(remainder);
                for (int i = decimal.length(); i > repeatIndex; i--) {
                    decimal.insert(i, "\u0305");
                }
                answer.append(decimal);

                if (answer.charAt(answer.length() - 1) == '.') {
                    answer.deleteCharAt(answer.length() - 1);
                }

                return answer.toString();
            }
            remainders.add(remainder);
            remainder *= 10;
            decimal.append(remainder / denominator);
            remainder %= denominator;
        }
        answer.append(decimal);
        if (answer.charAt(answer.length() - 1) == '.') {
            answer.deleteCharAt(answer.length() - 1);
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        System.out.println("Enter some number of fractions of positive integers (separated by spaces).");
        System.out.println("Long division will then be used to find their (exact) decimal forms.");
        // example input:
        // 8/2 3/2 1/8 1/7 101/3 1/6 1/12 970/23 1691/330 3179893/9906

        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();

        // String line = "8/2 3/2 1/8 1/7 101/3 1/6 1/12 970/23 1691/330 3179893/9906";
        String[] quotients = line.split(" ");
        for (String quotient : quotients) {
            String[] parts = quotient.split("/");
            int num = Integer.parseInt(parts[0]);
            int denom = Integer.parseInt(parts[1]);
            System.out.println("" + num + "/" + denom + " = " + fractionAsDecimal(num, denom));
        }
    }
}