package PrisonSearch;

import GenericSearch.*;
import PrisonSearch.AStarHeuristics.FarthestRockHeuristic;
import PrisonSearch.AStarHeuristics.UnmatchedRocksHeuristic;
import SearchStrategies.*;

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
            {Cell.EMPTY, Cell.OBSTACLE, Cell.EMPTY_PRESSURE_PAD, Cell.EMPTY_PRESSURE_PAD, Cell.EMPTY_PRESSURE_PAD, Cell.EMPTY_PRESSURE_PAD, Cell.EMPTY_PRESSURE_PAD, Cell.EMPTY, Cell.EMPTY},
            {Cell.ME, Cell.OBSTACLE, Cell.EMPTY_PRESSURE_PAD, Cell.OBSTACLE, Cell.OBSTACLE, Cell.OBSTACLE, Cell.OBSTACLE, Cell.EMPTY, Cell.EMPTY},
            {Cell.EMPTY, Cell.ROCK, Cell.ROCK, Cell.OBSTACLE, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY},
            {Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY},
            {Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY},
            {Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.ROCK, Cell.EMPTY, Cell.ROCK, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY},
            {Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.ROCK, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY},
            {Cell.EMPTY, Cell.EMPTY, Cell.ROCK, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY},
            {Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.TELEPORT, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY},
        };

        grid = new Cell[][]{
                {Cell.EMPTY, Cell.OBSTACLE, Cell.EMPTY_PRESSURE_PAD, Cell.EMPTY_PRESSURE_PAD, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY},
                {Cell.ME, Cell.OBSTACLE, Cell.EMPTY_PRESSURE_PAD, Cell.OBSTACLE, Cell.OBSTACLE, Cell.OBSTACLE, Cell.OBSTACLE, Cell.EMPTY, Cell.EMPTY},
                {Cell.EMPTY, Cell.ROCK, Cell.ROCK, Cell.OBSTACLE, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY},
                {Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY},
                {Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.ROCK, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY},
                {Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.TELEPORT, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY, Cell.EMPTY},
        };

        return grid;
    }

    SearchResult search(SearchType searchType, boolean visualize) {
        PrisonState initialState = new PrisonState(genGrid(), 0, 0, null);
        PrisonCostEvaluator costFunction = new PrisonCostEvaluator();
        PrisonGoalTester goalTester = new PrisonGoalTester();

        SearchQueue queue;

        switch (searchType) {
            case BF: queue = new BFSQueue(); break;
            case DF: queue = new DFSQueue(); break;
            case ID: queue = new IterativeDeepeningQueue(initialState); break;
            case UC: queue = new UniformCostQueue(); break;
            case AS1: queue = new AStarQueue(new FarthestRockHeuristic()); break;
            case AS2: queue = new AStarQueue(new UnmatchedRocksHeuristic()); break;
            default: queue = new BFSQueue();
        }

        ArrayList<Operator> operators = new ArrayList<>();
        operators.add(new PrisonOperator(1, 0, costFunction));
        operators.add(new PrisonOperator(-1, 0, costFunction));
        operators.add(new PrisonOperator(0, 1, costFunction));
        operators.add(new PrisonOperator(0, -1, costFunction));

        Search search = new Search(operators, initialState, goalTester, queue, visualize, new PrintWriter(System.out));
        return search.startSearch();
    }

    public static void main(String[] args) {
        PrisonSearch ps = new PrisonSearch();
        SearchResult result = ps.search(SearchType.AS1, false);
        for(State s: result.getPath())
            System.out.println(s);

        System.out.printf("Total cost: %d\n\n", result.getCost());
        System.out.printf("Expanded nodes: %d\n\n", result.getExpandedNodes());
    }
}
