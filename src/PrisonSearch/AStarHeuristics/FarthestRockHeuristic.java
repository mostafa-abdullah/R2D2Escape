package PrisonSearch.AStarHeuristics;

import GenericSearch.State;
import GenericSearch.StateHeuristic;
import PrisonSearch.Cell;
import PrisonSearch.PrisonState;

public class FarthestRockHeuristic extends StateHeuristic{
    /**
     * Calculate the manhattan distance between the player and the farthest rock not on a pressure pad
     * @param: The state calculate the heuristic value for
     * @return: The heuristic value calculated for the given state
     */
    @Override
    public int calculate(State s) {
        final int INFINITY = (int) 1e9;

        PrisonState state = (PrisonState) s;
        int myX = -1, myY = -1;
        Cell[][] grid = state.getGrid();
        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[i].length; j++)
                if(grid[i][j] == Cell.ME) {
                    myX = i; myY = j;
                }

        int[] iBorders = {0, grid.length};
        int[] jBorders = {0, grid[0].length};
        int max = 0;
        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[i].length; j++)
                if(grid[i][j] == Cell.ROCK) {
                    for(int bi = 0; bi < 2; bi++)
                        for(int bj = 0; bj < 2; bj++)
                            if(i == iBorders[bi] && j == jBorders[bj])
                                return INFINITY;
                    max = Math.max(max, Math.abs(myX - i) + Math.abs(myY - j));
                }

        return max;
    }
}
