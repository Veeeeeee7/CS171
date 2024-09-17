import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class KnightsTourSolver {

    private final static int[][] MOVES = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 },
            { -1, -2 }, { -2, -1 } };

    private class Choice {
        private int move;

        public Choice(int move) {
            this.move = move;
        }

        public int move() {
            return move;
        }
    }

    private class State implements StateAllowingBacktracking<Choice> {

        ///////////////////
        // ADD CODE HERE //
        ///////////////////
        private int boardSize;
        private int[][] board;
        private ArrayList<Choice> choices;

        Stack<Choice> choicesMade = new Stack<Choice>();
        private int currentX;
        private int currentY;

        private int moveNumber;

        public State(int boardSize) {
            moveNumber = 1;
            this.currentX = 0;
            this.currentY = 0;
            this.boardSize = boardSize;
            board = new int[boardSize][boardSize];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    board[i][j] = 0;
                }
            }
            board[0][0] = 1;

            choices = new ArrayList<>();
            choices.add(new Choice(0));
            choices.add(new Choice(1));
            choices.add(new Choice(2));
            choices.add(new Choice(3));
            choices.add(new Choice(4));
            choices.add(new Choice(5));
            choices.add(new Choice(6));
            choices.add(new Choice(7));
        }

        public boolean isSolution() {
            // for (int i = 0; i < board.length; i++) {
            // for (int j = 0; j < board[i].length; j++) {
            // if (board[i][j] == 0) {
            // return false;
            // }
            // }
            // }
            // return true;
            return choicesMade.size() == boardSize * boardSize - 1;
        }

        public List<Choice> nextChoicesToConsider() {
            return choices;
        }

        public boolean isValid(Choice choice) {
            int x = currentX + MOVES[choice.move()][0];
            int y = currentY + MOVES[choice.move()][1];
            return x >= 0 && x < boardSize && y >= 0 && y < boardSize && board[x][y] == 0;
        }

        public void makeChoice(Choice choice) {
            choicesMade.push(choice);
            currentX += MOVES[choice.move()][0];
            currentY += MOVES[choice.move()][1];
            moveNumber++;
            board[currentX][currentY] = moveNumber;
        }

        public void undoChoice(Choice choice) {
            Choice c = choicesMade.pop();
            moveNumber--;
            board[currentX][currentY] = 0;
            currentX -= MOVES[c.move()][0];
            currentY -= MOVES[c.move()][1];

        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    sb.append(board[i][j] + "\t");
                }
                sb.append("\n");
            }
            return sb.toString();
        }

    }

    private StateAllowingBacktracking<Choice> state;
    static private int numSolutionsFound;

    public KnightsTourSolver(int boardSize) {

        this.state = new State(boardSize);
        KnightsTourSolver.numSolutionsFound = 0;
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
        numSolutionsFound++;
        System.out.println("Solution " + numSolutionsFound + System.lineSeparator() + state);
    }

    public static void main(String[] args) {
        System.out.println("Enter board size to solve:");
        Scanner scanner = new Scanner(System.in);
        int boardSize = scanner.nextInt();
        scanner.close();

        KnightsTourSolver finder = new KnightsTourSolver(boardSize);
        finder.searchFromCurrentState();
    }

}