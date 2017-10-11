package GenericSearch;

/**
 * Created by mostafa on 10/11/17.
 */
public abstract class Operator {
    protected CostEvaluator costFunction;
    protected abstract State applyOperator(State currentState);
}
