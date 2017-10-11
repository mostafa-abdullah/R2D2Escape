public class State {
    Cell[][] grid;
    int cost;
    State(Cell[][] grid, int cost) {
        this.grid = grid;
        this.cost = cost;
    }

    State cloneState() {
        Cell[][] newGrid = new Cell[grid.length][];
        for(int i = 0; i < grid.length; i++)
            newGrid[i] = grid[i].clone();

        return new State(newGrid, cost);
    }
}
