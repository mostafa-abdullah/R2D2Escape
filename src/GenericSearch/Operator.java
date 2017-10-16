package GenericSearch;

public abstract class Operator {
    /**
     * A transition from the current state to the next state
     */
    protected CostEvaluator costFunction;

    /**
     * Apply this operator on the given state
     * @param currentState: The state to apply the operator on
     * @return The next state after applying the operator
     */
    protected abstract State applyOperator(State currentState);
}
