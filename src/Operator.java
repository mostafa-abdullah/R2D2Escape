public class Operator {
    static private final int normalMoveCost = 1;
    static private final int rockPushCost = 2;

    private  int vertical, horizontal;
    Operator(int ver, int hor) {
        this.vertical = ver;
        this.horizontal = hor;
    }

    boolean isInsideGrid(int x, int y, int gridLen, int gridWid) {
        return x >= 0 && x < gridLen && y >= 0 && y < gridWid;
    }

    State applyOperator(State currentState) {
        State newState = currentState.cloneState();

        Cell[][] grid = newState.grid;

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
                canEnterTeleport &= grid[i][j] != Cell.ROCK;
            }

        int newX = myX + vertical, newY = myY + horizontal;

        newState.cost += normalMoveCost;
        if(isInsideGrid(newX, newY, N, M)) {
            Cell myCell = grid[myX][myY], newCell = grid[newX][newY];
            if(newCell == Cell.EMPTY || newCell == Cell.EMPTY_PRESSURE_PAD) {
                grid[newX][newY] = newCell == Cell.EMPTY ? Cell.ME : Cell.PRESSURE_PAD_ME;
                grid[myX][myY] = myCell == Cell.ME ? Cell.EMPTY : Cell.EMPTY_PRESSURE_PAD;
            }

            else if(newCell == Cell.ROCK || newCell == Cell.PRESSURE_PAD_ROCK) {
                newState.cost += rockPushCost - normalMoveCost;
                int afterRockX = newX + horizontal, afterRockY = newY + vertical;
                if(isInsideGrid(afterRockX, afterRockY, N, M)) {
                    Cell afterRockCell = grid[afterRockX][afterRockY];
                    if(afterRockCell == Cell.EMPTY || afterRockCell == Cell.EMPTY_PRESSURE_PAD) {
                        grid[afterRockX][afterRockY] = afterRockCell == Cell.EMPTY ? Cell.ROCK : Cell.PRESSURE_PAD_ROCK;
                        grid[newX][newY] = newCell == Cell.ROCK ? Cell.ME : Cell.PRESSURE_PAD_ME;
                        grid[myX][myY] = myCell == Cell.ME ? Cell.EMPTY : Cell.EMPTY_PRESSURE_PAD;
                    }
                }
            }
            else if(newCell == Cell.TELEPORT && canEnterTeleport) {
                grid[myX][myY] = myCell == Cell.ME ? Cell.EMPTY : Cell.EMPTY_PRESSURE_PAD;
            }

            // do nothing if obstacle
        }

        return newState;
    }
}
