import java.util.Scanner;

public class TailRecursionToIteration {

    ProblemSpecAndTools psat;

    public TailRecursionToIteration(ProblemSpecAndTools psat) {
        this.psat = psat;
    }

    public Object findRecursively() {
        if (psat.isBaseCase()) {
            psat.updateAccumulatorBaseCase();
            return psat.accumulator();
        } else {
            psat.updateAccumulatorNonBaseCase();
            psat.reduceProblem();
            return findRecursively();
        }
    }

    public Object findIteratively() {

        ///////////////////
        // ADD CODE HERE //
        ///////////////////
        while (!psat.isBaseCase()) {
            psat.updateAccumulatorNonBaseCase();
            psat.reduceProblem();
        }
        psat.updateAccumulatorBaseCase();
        return psat.accumulator();
    }

    public static class FactorialPSAT implements ProblemSpecAndTools {
        private int n;
        private int a; // accumulator

        public FactorialPSAT(int n) {
            this.n = n;
            this.a = 1;
        }

        public boolean isBaseCase() {
            return n == 0;
        }

        public void reduceProblem() {
            n--;
        }

        public void updateAccumulatorBaseCase() {
            // do nothing
        }

        public void updateAccumulatorNonBaseCase() {
            a = n * a;
        }

        public String toString() {
            return "" + a;
        }

        public Object accumulator() {
            return a;
        }
    }

    public static class StringReverserPSAT implements ProblemSpecAndTools {

        ///////////////////
        // ADD CODE HERE //
        ///////////////////
        private String input;
        private String reverse;

        public StringReverserPSAT(String s) {
            input = s;
            reverse = "";
        }

        public boolean isBaseCase() {
            return input.length() == 0;
        }

        public void reduceProblem() {
            input = input.substring(0, input.length() - 1);
        }

        public void updateAccumulatorBaseCase() {
            // do nothing
        }

        public void updateAccumulatorNonBaseCase() {
            reverse += input.charAt(input.length() - 1);
        }

        public String toString() {
            return reverse;
        }

        public Object accumulator() {
            return reverse;
        }
    }

    public static class GcdPSAT implements ProblemSpecAndTools {

        private int a;
        private int b;

        public GcdPSAT(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public boolean isBaseCase() {
            return b == 0;
        }

        public void reduceProblem() {
            int temp = a;
            a = b;
            b = temp % b;
        }

        public void updateAccumulatorBaseCase() {
            // do nothing
        }

        public void updateAccumulatorNonBaseCase() {
        }

        public String toString() {
            return "" + a;
        }

        public Object accumulator() {
            return a;
        }
    }

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