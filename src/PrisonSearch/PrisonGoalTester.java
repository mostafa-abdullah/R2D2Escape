package PrisonSearch;

import GenericSearch.GoalTester;
import GenericSearch.State;

public class PrisonGoalTester extends GoalTester{
    public boolean test(State state) {
        PrisonState currentState = (PrisonState) state;
        Cell[][] grid = currentState.grid;
        for(Cell[] row: grid)
            for(Cell cell: row) {
                if(!cell.isValidEnd())
                    return false;
            }
        return true;
    }
}
