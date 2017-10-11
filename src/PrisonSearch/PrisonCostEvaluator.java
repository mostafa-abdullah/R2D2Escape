package PrisonSearch;

import GenericSearch.CostEvaluator;

public class PrisonCostEvaluator extends CostEvaluator{
    private final int INFINITY = (int) 1e9;
    public int getCost(Object type) {
        MoveType moveType = (MoveType) type;
        if(moveType == MoveType.NORMAL_MOVE)
            return 1;
        else if(moveType == MoveType.ROCK_PUSH)
            return 2;
        return INFINITY;
    }
}
