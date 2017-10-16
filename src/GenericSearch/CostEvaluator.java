package GenericSearch;

public abstract class CostEvaluator {
    /**
     * Evaluate the cost of a given move
     * @param moveType: the type of the move
     * @return: the cost of the given move
     */
    public abstract int getCost(Object moveType);
}
