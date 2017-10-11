import java.util.*;

/**
 * Created by mostafa on 10/11/17.
 */
public class Search {
    private final int MAX_GRID_LEN = 5;
    private final int MIN_GRID_LEN = 3;
    private final int MAX_GRID_WIDTH = 5;
    private final int MIN_GRID_WIDTH = 3;

    Cell[][] genGrid() {
        Random rand = new Random();
        int len = rand.nextInt(MAX_GRID_LEN - MIN_GRID_LEN + 1) + MIN_GRID_LEN;
        int width = rand.nextInt(MAX_GRID_WIDTH - MIN_GRID_WIDTH + 1) + MIN_GRID_LEN;

        Cell[][] grid = new Cell[len][width];

        int rocks = rand.nextInt((len * width - 2) / 2 + 1);
        int obstacles = rand.nextInt(len * width - 2 - rocks * 2 - 1);
        ArrayList<Cell> allCells = new ArrayList<>();
        for(int i = 0; i < rocks; i++) {
            allCells.add(Cell.ROCK);
            allCells.add(Cell.EMPTY_PRESSURE_PAD);
        }
        for(int i = 0; i < obstacles; i++)
            allCells.add(Cell.OBSTACLE);
        allCells.add(Cell.ME);
        allCells.add(Cell.TELEPORT);
        while(allCells.size() < len * width)
            allCells.add(Cell.EMPTY);
        Collections.shuffle(allCells);

        for(int i = 0; i < len; i++)
            for(int j = 0; j < width; j++)
                grid[i][j] = allCells.get(i * width + j);

        return grid;
    }


    public static void main(String[] args) {
        Cell[][] g = new Search().genGrid();
        for(int i = 0; i < g.length; i++)
            System.out.println(Arrays.toString(g[i]));
    }
}
