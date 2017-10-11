package PrisonSearch;

import GenericSearch.State;

public class PrisonState extends State {
    Cell[][] grid;
    PrisonState(Cell[][] grid, int cost, PrisonState parent) {
        this.grid = grid;
        this.cost = cost;
        this.parent = parent;
    }

    PrisonState cloneState() {
        Cell[][] newGrid = new Cell[grid.length][];
        for(int i = 0; i < grid.length; i++)
            newGrid[i] = grid[i].clone();

        return new PrisonState(newGrid, cost, this);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Cell[] row: grid) {
            for(Cell cell: row)
                result.append(cell);
            result.append('\n');
        }
        result.append(cost + "\n");
        return result.toString();
    }
}
