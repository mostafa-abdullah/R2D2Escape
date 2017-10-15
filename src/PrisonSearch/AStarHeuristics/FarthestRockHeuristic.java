package PrisonSearch.AStarHeuristics;

import GenericSearch.State;
import GenericSearch.StateHeuristic;
import PrisonSearch.Cell;
import PrisonSearch.PrisonState;

public class FarthestRockHeuristic extends StateHeuristic{
    private final int INFINITY = (int) 1e9;
    @Override
    public int calculate(State s) {
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

        return s.getCost() + max;
    }
}
