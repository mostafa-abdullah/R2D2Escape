/**
 * Created by mostafa on 10/11/17.
 */
public class GoalTester {
    boolean test(State currentSate) {
        Cell[][] grid = currentSate.grid;
        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[i].length; j++) {
                if(!grid[i][j].isValidEnd())
                    return false;
            }
        return true;
    }
}
