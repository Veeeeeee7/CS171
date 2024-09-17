import java.util.List;

public interface StateAllowingBacktracking<Choice> {

    boolean isSolution();

    List<Choice> nextChoicesToConsider();

    boolean isValid(Choice choice);

    void makeChoice(Choice choice);

    void undoChoice(Choice choice);
}