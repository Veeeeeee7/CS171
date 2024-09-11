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
    }

    public static class GcdPSAT implements ProblemSpecAndTools {

        ///////////////////
        // ADD CODE HERE //
        ///////////////////
    }
}