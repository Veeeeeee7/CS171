public interface ProblemSpecAndTools {

    // Classes implementing this interface should include sufficient
    // instance variables to completely specify a problem/task whose output
    // can be accomplished via tail recursion and an accumulator.

    void updateAccumulatorBaseCase();

    void updateAccumulatorNonBaseCase();

    boolean isBaseCase();

    void reduceProblem();

    Object accumulator();

}