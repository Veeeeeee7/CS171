import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class PermutationsFinder {

    private class Choice {

        ///////////////////
        // ADD CODE HERE //
        ///////////////////
        private int a;

        public Choice(int a) {
            this.a = a;
        }

        public int value() {
            return a;
        }
    }

    private class State implements StateAllowingBacktracking<Choice> {

        private Stack<Choice> choicesMade;

        //////////////////////////////////////////////////////////////////////
        // ADD CONSTRUCTORS AND INSTANCE METHODS HERE, AS NEEDED OR DESIRED //
        //////////////////////////////////////////////////////////////////////
        private int k;
        private int n;

        public State(int n, int k) {
            choicesMade = new Stack<Choice>();
            this.n = n;
            this.k = k;
        }

        public boolean isSolution() {
            return choicesMade.size() == k;
        }

        public List<Choice> nextChoicesToConsider() {
            if (choicesMade.isEmpty()) {
                ArrayList<Choice> list = new ArrayList<>();
                for (int i = 1; i <= n; i++) {
                    list.add(new Choice(i));
                }
                return list;
            }
            ArrayList<Choice> list = new ArrayList<Choice>();
            int i = 1;
            while (i <= n) {
                list.add(new Choice(i));
                i++;
            }
            return list;
        }

        public boolean isValid(Choice choice) {
            for (Choice c : choicesMade) {
                if (c.value() == choice.value()) {
                    return false;
                }
            }
            return true;
        }

        public void makeChoice(Choice choice) {
            choicesMade.push(choice);
        }

        public void undoChoice(Choice choice) {
            choicesMade.pop();
        }

        public String toString() {
            // int a = 0;
            // int power = (int) Math.pow(10, k - 1);
            // for (Choice c : choicesMade) {
            // a += c.value() * power;
            // power /= 10;
            // }
            // return String.valueOf(a);
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            for (Choice c : choicesMade) {
                sb.append(c.value() + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");
            return sb.toString();
        }

    }

    private int n;
    private int k;
    private StateAllowingBacktracking<Choice> state;
    static private int numPermutationsFound;

    public PermutationsFinder(int n, int k) {
        this.n = n;
        this.k = k;
        this.state = new State(n, k);
        PermutationsFinder.numPermutationsFound = 0;
    }

    public void searchFromCurrentState() {

        if (state.isSolution()) {
            reactToSolution();
            return; // stop pursuing this path
        }

        for (Choice choice : state.nextChoicesToConsider()) {
            if (state.isValid(choice)) {
                state.makeChoice(choice);
                searchFromCurrentState(); // <-- the recursive call!
                state.undoChoice(choice); // try another path
            }
        }
    }

    private void reactToSolution() {
        System.out.println(state);
        numPermutationsFound++;
    }

    public static void main(String[] args) {
        System.out.println(
                "Enter n and k separated by a space to find all permutations of k values selected from 1 to n:");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();
        scanner = new Scanner(line);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.close();

        scanner.close();

        PermutationsFinder finder = new PermutationsFinder(n, k);
        finder.searchFromCurrentState();
        System.out.println("number of permutations found: " + PermutationsFinder.numPermutationsFound);
    }
}
// HELPED BY: NOBODY