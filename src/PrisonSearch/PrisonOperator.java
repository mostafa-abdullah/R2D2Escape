package PrisonSearch;

import GenericSearch.Operator;
import GenericSearch.State;

public class PrisonOperator extends Operator{
    /**
     * Simulator of an Operator over a given state
     */
    private  int vertical, horizontal;
    PrisonOperator(int ver, int hor, PrisonCostEvaluator costFunction) {
        this.costFunction = costFunction;
        this.vertical = ver;
        this.horizontal = hor;
    }

    private boolean isInsideGrid(int x, int y, int gridLen, int gridWid) {
        return x >= 0 && x < gridLen && y >= 0 && y < gridWid;
    }

    /**
     * Apply the operator over a given state
     * @param currentState: The state to apply the operator on
     * @return The new state after applying the operator
     */
    public PrisonState applyOperator(State currentState) {
        PrisonState currentPrisonState = (PrisonState) currentState;
        PrisonState newPrisonState = currentPrisonState.nextState();
        Cell[][] grid = newPrisonState.grid;

        int myX = -1;
        int myY = -1;

        int N = grid.length;
        int M = grid[0].length;

        boolean canEnterTeleport = true;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == Cell.ME || grid[i][j] == Cell.PRESSURE_PAD_ME) {
                    myX = i;
                    myY = j;
                }
                // can not enter the teleport in case there is a rock not on a pressure pad
                canEnterTeleport &= grid[i][j] != Cell.ROCK;
            }

        int newX = myX + vertical, newY = myY + horizontal;
        int normalMoveCost = costFunction.getCost(MoveType.NORMAL_MOVE);
        int rockPushCost = costFunction.getCost(MoveType.ROCK_PUSH);
        newPrisonState.incCost(normalMoveCost);
        if(isInsideGrid(newX, newY, N, M)) {
            Cell myCell = grid[myX][myY], newCell = grid[newX][newY];
            if(newCell == Cell.EMPTY || newCell == Cell.EMPTY_PRESSURE_PAD) {
                // move forward as a normal move
                grid[newX][newY] = newCell == Cell.EMPTY ? Cell.ME : Cell.PRESSURE_PAD_ME;
                grid[myX][myY] = myCell == Cell.ME ? Cell.EMPTY : Cell.EMPTY_PRESSURE_PAD;
            }

            else if(newCell == Cell.ROCK || newCell == Cell.PRESSURE_PAD_ROCK) {
                newPrisonState.incCost(rockPushCost - normalMoveCost);
                int afterRockX = newX + vertical, afterRockY = newY + horizontal;
                if(isInsideGrid(afterRockX, afterRockY, N, M)) {
                    Cell afterRockCell = grid[afterRockX][afterRockY];
                    if(afterRockCell == Cell.EMPTY || afterRockCell == Cell.EMPTY_PRESSURE_PAD) {
                        // push the rock forward
                        grid[afterRockX][afterRockY] = afterRockCell == Cell.EMPTY ? Cell.ROCK : Cell.PRESSURE_PAD_ROCK;
                        grid[newX][newY] = newCell == Cell.ROCK ? Cell.ME : Cell.PRESSURE_PAD_ME;
                        grid[myX][myY] = myCell == Cell.ME ? Cell.EMPTY : Cell.EMPTY_PRESSURE_PAD;
                    }
                }
            }
            else if(newCell == Cell.TELEPORT && canEnterTeleport) {
                // enter the teleport
                grid[myX][myY] = myCell == Cell.ME ? Cell.EMPTY : Cell.EMPTY_PRESSURE_PAD;
            }
            else {
                // do nothing if obstacle
                return null;
            }

        }
        else
            return null;

        return newPrisonState;
    }
}
