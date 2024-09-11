import java.util.Scanner;

public class TailRecursionToIterationTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a positive integer n to see the value of n!");
        int n = Integer.parseInt(scanner.nextLine()); // try 10

        System.out.println((new TailRecursionToIteration(new FactorialPSAT(n))).findRecursively());
        System.out.println((new TailRecursionToIteration(new FactorialPSAT(n))).findIteratively());
        System.out.println();

        System.out.println("Enter a string of text to see it reversed"); // try "the quick brown fox jumped over the
                                                                         // lazy dog"
        String s = scanner.nextLine();

        System.out.println((new TailRecursionToIteration(new StringReverserPSAT(s))).findRecursively());
        System.out.println((new TailRecursionToIteration(new StringReverserPSAT(s))).findIteratively());
        System.out.println();

        System.out.println(
                "Enter two positive integers (separated by a space) to find their greatest common divisor (gcd)");
        int a = scanner.nextInt(); // try 54321
        int b = scanner.nextInt(); // try 9876

        System.out.println((new TailRecursionToIteration(new GcdPSAT(a, b))).findRecursively());
        System.out.println((new TailRecursionToIteration(new GcdPSAT(a, b))).findIteratively());

        scanner.close();
    }
}