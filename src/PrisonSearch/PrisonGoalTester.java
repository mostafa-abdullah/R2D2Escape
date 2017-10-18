package PrisonSearch;

import GenericSearch.GoalTester;
import GenericSearch.State;

public class PrisonGoalTester extends GoalTester{

    /**
     * @param state: The state to test against
     * @return true if the given state is a goal state and false otherwise
     */
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
