package PrisonSearch;

import GenericSearch.CostEvaluator;

public class PrisonCostEvaluator extends CostEvaluator{

    /**
     * Get the cost corresponds to the move according to its type (1 in case of normal move and 2 in case of pushing rock)
     * @param type: The type of the current move
     * @return The cost of the given move
     */
    public int getCost(Object type) {
        final int INFINITY = (int) 1e9;

        MoveType moveType = (MoveType) type;
        if(moveType == MoveType.NORMAL_MOVE)
            return 1;
        else if(moveType == MoveType.ROCK_PUSH)
            return 2;
        return INFINITY;
    }
}
