package PrisonSearch.AStarHeuristics;

import GenericSearch.State;
import GenericSearch.StateHeuristic;
import PrisonSearch.Cell;
import PrisonSearch.PrisonState;

/**
 * Created by mostafa on 10/15/17.
 */
public class UnmatchedRocksHeuristic extends StateHeuristic {
    @Override
    public int calculate(State s) {
        PrisonState state = (PrisonState) s;
        int count = 0;
        Cell[][] grid = state.getGrid();
        for(Cell[] row: grid)
            for(Cell cell: row)
                if(cell == Cell.ROCK)
                    count++;
        return s.getCost() + count;
    }
}
