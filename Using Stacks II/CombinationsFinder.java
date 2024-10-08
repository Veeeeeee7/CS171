import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class CombinationsFinder {

    final Choice INCLUDE = new Choice(true);
    final Choice OMIT = new Choice(false);

    private class Choice {
        private boolean b;

        public Choice(boolean b) {
            this.b = b;
        }

        public boolean value() {
            return b;
        }
    }

    private class State implements StateAllowingBacktracking<Choice> {

        private Stack<Choice> choicesMade;
        private int numIncluded;
        private int n;
        private int k;

        //////////////////////////////////////////////////////////////////////
        // ADD CONSTRUCTORS AND INSTANCE METHODS HERE, AS NEEDED OR DESIRED //
        //////////////////////////////////////////////////////////////////////
        public State(int n, int k) {
            this.n = n;
            this.k = k;
            numIncluded = 0;
            choicesMade = new Stack<Choice>();
        }

        public boolean isSolution() {
            return choicesMade.size() == n && numIncluded == k;
        }

        public List<Choice> nextChoicesToConsider() {
            ArrayList<Choice> list = new ArrayList<Choice>();
            list.add(new Choice(true));
            list.add(new Choice(false));
            return list;
        }

        public boolean isValid(Choice choice) {
            return numIncluded <= k && choicesMade.size() <= n;
        }

        public void makeChoice(Choice choice) {
            choicesMade.push(choice);
            if (choice.value()) {
                numIncluded++;
            }
        }

        public void undoChoice(Choice choice) {
            Choice c = choicesMade.pop();
            if (c.value()) {
                numIncluded--;
            }
        }

        public String toString() {
            StringBuilder IOPart = new StringBuilder();
            IOPart.append("{");
            StringBuilder numPart = new StringBuilder();
            numPart.append("{");
            int i = 1;
            for (Choice c : choicesMade) {
                if (c.value()) {
                    IOPart.append("I");
                    numPart.append(i + ",");
                } else {
                    IOPart.append("O");
                }
                i++;
            }
            IOPart.append("}");
            numPart.deleteCharAt(numPart.length() - 1);
            numPart.append("}");
            return IOPart.append(numPart).toString();
        }
    }

    private int n;
    private int k;
    private StateAllowingBacktracking<Choice> state;
    static private int numCombinationsFound;

    public CombinationsFinder(int n, int k) {
        this.n = n;
        this.k = k;
        this.state = new State(n, k);
        CombinationsFinder.numCombinationsFound = 0;
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
        numCombinationsFound++;
    }

    public static void main(String[] args) {
        System.out.println(
                "Enter n and k separated by a space to find all combinations of k values selected from 1 to n:");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();
        scanner = new Scanner(line);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        scanner.close();

        scanner.close();

        CombinationsFinder finder = new CombinationsFinder(n, k);
        finder.searchFromCurrentState();
        System.out.println("number of combinations found: " + CombinationsFinder.numCombinationsFound);
    }
}
// HELPED BY: NOBODY
