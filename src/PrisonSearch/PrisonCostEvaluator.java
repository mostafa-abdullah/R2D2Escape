package PrisonSearch;

import GenericSearch.CostEvaluator;

public class PrisonCostEvaluator extends CostEvaluator{
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
