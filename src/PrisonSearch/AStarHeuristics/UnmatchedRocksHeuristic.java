package PrisonSearch.AStarHeuristics;

import GenericSearch.State;
import GenericSearch.StateHeuristic;
import PrisonSearch.Cell;
import PrisonSearch.PrisonState;

public class UnmatchedRocksHeuristic extends StateHeuristic {
    public UnmatchedRocksHeuristic(boolean isGreedy) {
        super(isGreedy);
    }

    public UnmatchedRocksHeuristic() {
        super(false);
    }

    @Override
    public int calculate(State s) {
        PrisonState state = (PrisonState) s;
        int count = 0;
        Cell[][] grid = state.getGrid();
        for(Cell[] row: grid)
            for(Cell cell: row)
                if(cell == Cell.ROCK)
                    count++;
        return this.isGreedy ? count : s.getCost() + count;
    }
}
