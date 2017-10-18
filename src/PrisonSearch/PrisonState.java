package PrisonSearch;

import GenericSearch.State;

public class PrisonState extends State {
    /**
     * the data structure that represents the search node holding the state of the prison, the cost from the initial state, the level of the node in the search and the parent state
     */
    Cell[][] grid;
    PrisonState(Cell[][] grid, int cost, int level, PrisonState parent) {
        this.grid = grid;
        this.cost = cost;
        this.level = level;
        this.parent = parent;
    }

    /**
     * @return A new state with similar prison state but as a child of this state
     */
    PrisonState nextState() {
        Cell[][] newGrid = new Cell[grid.length][];
        for(int i = 0; i < grid.length; i++)
            newGrid[i] = grid[i].clone();

        return new PrisonState(newGrid, cost, this.level + 1, this);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(Cell[] row: grid) {
            for(Cell cell: row)
                result.append(cell);
            result.append('\n');
        }
        result.append(cost).append("\n");
        return result.toString();
    }

    @Override
    public int compareTo(State s) {
        PrisonState state = (PrisonState) s;
        for(int i = 0; i < this.grid.length; i++)
            for(int j = 0; j < this.grid[i].length; j++)
                if(this.grid[i][j] != state.grid[i][j])
                    return this.grid[i][j].ordinal() - state.grid[i][j].ordinal();
        return 0;
    }

    public Cell[][] getGrid() {
        return this.grid;
    }
}
