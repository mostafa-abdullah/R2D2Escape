public class GoalTester {
    boolean test(State currentSate) {
        Cell[][] grid = currentSate.grid;
        for(Cell[] row: grid)
            for(Cell cell: row) {
                if(!cell.isValidEnd())
                    return false;
            }
        return true;
    }
}
