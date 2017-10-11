package PrisonSearch;

import GenericSearch.Operator;
import GenericSearch.Search;
import GenericSearch.SearchQueue;
import GenericSearch.State;
import SearchStrategies.BFSQueue;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PrisonSearch {
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
        int obstacles = rand.nextInt(len * width - 2 - rocks * 2 + 1);
        ArrayList<Cell> allCells = new ArrayList<>();
        for (int i = 0; i < rocks; i++) {
            allCells.add(Cell.ROCK);
            allCells.add(Cell.EMPTY_PRESSURE_PAD);
        }
        for (int i = 0; i < obstacles; i++)
            allCells.add(Cell.OBSTACLE);
        allCells.add(Cell.ME);
        allCells.add(Cell.TELEPORT);
        while (allCells.size() < len * width)
            allCells.add(Cell.EMPTY);
        Collections.shuffle(allCells);

        for (int i = 0; i < len; i++)
            for (int j = 0; j < width; j++)
                grid[i][j] = allCells.get(i * width + j);

        grid = new Cell[][]{
            {Cell.EMPTY, Cell.OBSTACLE, Cell.EMPTY_PRESSURE_PAD, Cell.OBSTACLE},
            {Cell.ME, Cell.OBSTACLE, Cell.EMPTY_PRESSURE_PAD, Cell.OBSTACLE},
            {Cell.EMPTY, Cell.ROCK, Cell.ROCK, Cell.OBSTACLE},
            {Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.TELEPORT},
        };

        grid = new Cell[][]{
                {Cell.ME, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY},
                {Cell.ME, Cell.EMPTY, Cell.EMPTY, Cell.TELEPORT},
        };

        return grid;
    }

    void search(Search problem, int searchType, boolean visualize) {
        SearchQueue q = new BFSQueue();
        PrisonCostEvaluator ce = new PrisonCostEvaluator();
        PrisonGoalTester gt = new PrisonGoalTester();
        ArrayList<Operator> ops = new ArrayList<>();
        ops.add(new PrisonOperator(1, 0, ce));
        ops.add(new PrisonOperator(-1, 0, ce));
        ops.add(new PrisonOperator(0, 1, ce));
        ops.add(new PrisonOperator(0, -1, ce));
        PrisonState ps = new PrisonState(genGrid(), 0, null);
        Search search = new Search(ops, ps, gt, q, visualize, new PrintWriter(System.out));
        ArrayList<State> path = search.startSearch();
        System.out.println(path);
    }

    public static void main(String[] args) {
        PrisonSearch pr = new PrisonSearch();
        pr.search(null, 0, false);
    }
}
