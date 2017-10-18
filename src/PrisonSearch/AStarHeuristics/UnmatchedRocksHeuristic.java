package PrisonSearch.AStarHeuristics;

import GenericSearch.State;
import GenericSearch.StateHeuristic;
import PrisonSearch.Cell;
import PrisonSearch.PrisonState;

public class UnmatchedRocksHeuristic extends StateHeuristic {

    /**
     * Calculate the number of rocks not on a pressure pad
     * @param: The state calculate the heuristic value for
     * @return: The heuristic value calculated for the given state
     */
    @Override
    public int calculate(State s) {
        PrisonState state = (PrisonState) s;
        int count = 0;
        Cell[][] grid = state.getGrid();
        for(Cell[] row: grid)
            for(Cell cell: row)
                if(cell == Cell.ROCK)
                    count++;
        return count;
    }
}
