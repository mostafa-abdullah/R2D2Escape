package PrisonSearch;

import GenericSearch.*;
import PrisonSearch.AStarHeuristics.FarthestRockHeuristic;
import PrisonSearch.AStarHeuristics.UnmatchedRocksHeuristic;
import SearchStrategies.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class PrisonSearch {
    private static final int MAX_GRID_LEN = 6;
    private static final int MIN_GRID_LEN = 3;
    private static final int MAX_GRID_WIDTH = 6;
    private static final int MIN_GRID_WIDTH = 3;

    static PrintWriter printer = new PrintWriter(System.out);
    /**
     * Generates a random initial grid to be used in the search problem
     * @return The randomly created grid
     */
    private static Cell[][] genGrid() {
        Random rand = new Random();
        int len = rand.nextInt(MAX_GRID_LEN - MIN_GRID_LEN + 1) + MIN_GRID_LEN;
        int width = rand.nextInt(MAX_GRID_WIDTH - MIN_GRID_WIDTH + 1) + MIN_GRID_LEN;

        Cell[][] grid = new Cell[len][width];

        int rocks = rand.nextInt((len * width - 2) / 4 + 1);
        int obstacles = rand.nextInt((len * width - 2 - rocks * 2) / 4 + 1);
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

        return grid;
    }

    private static void writeKnowledgeBase() throws FileNotFoundException {
        Cell[][] grid = genGrid();
        final String path = "/home/mostafa/GUC/Semester 9/AI/Projects/2/src/kb.pl";
        PrintWriter writer = new PrintWriter(path);

        // print grid dimensions
        writer.printf("height(%d).\n", grid.length);
        writer.printf("width(%d).\n", grid[0].length);

        StringBuilder rocks = new StringBuilder(), obstacles = new StringBuilder(),
                pads = new StringBuilder(), teleport = new StringBuilder(),
                me = new StringBuilder();

        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[i].length; j++)
                if(grid[i][j] == Cell.ME)
                    me.append(String.format("robot(%d, %d, s0).\n", i + 1, j + 1));
                else if(grid[i][j] == Cell.ROCK)
                    rocks.append(String.format("rock(%d, %d, s0).\n", i + 1, j + 1));
                else if(grid[i][j] == Cell.EMPTY_PRESSURE_PAD)
                    pads.append(String.format("pad(%d, %d).\n", i + 1, j + 1));
                else if(grid[i][j] == Cell.OBSTACLE)
                    obstacles.append(String.format("obstacle(%d, %d).\n", i + 1, j + 1));
                else if(grid[i][j] == Cell.TELEPORT)
                    teleport.append(String.format("teleport(%d, %d).\n", i + 1, j + 1));

        writer.println(me);
        writer.println(rocks);
        writer.println(pads);
        writer.println(obstacles);
        writer.println(teleport);

        writer.flush();
        writer.close();
    }

    /**
     * search for a solution to some state of a prison
     * @param searchType: The search strategy to be used for finding the solution
     * @param visualize: boolean to indicate whether to show the intermediate expanded nodes during the search or not
     * @return The search result for the problem (the path from the initial state to the goal, the cost, and the number of the expanded nodes) or null in case there is no solution
     */
    private SearchResult search(SearchType searchType, boolean visualize) {
        PrisonState initialState = new PrisonState(genGrid(), 0, 0, null);
        PrisonCostEvaluator costFunction = new PrisonCostEvaluator();
        PrisonGoalTester goalTester = new PrisonGoalTester();

        SearchQueue queue;

        switch (searchType) {
            case BF: queue = new BFSQueue(); break;
            case DF: queue = new DFSQueue(); break;
            case ID: queue = new IterativeDeepeningQueue(initialState); break;
            case UC: queue = new UniformCostQueue(); break;
            case GR1: queue = new GreedyQueue(new FarthestRockHeuristic()); break;
            case GR2: queue = new GreedyQueue(new UnmatchedRocksHeuristic()); break;
            case AS1: queue = new AStarQueue(new FarthestRockHeuristic()); break;
            case AS2: queue = new AStarQueue(new UnmatchedRocksHeuristic()); break;
            default: queue = new BFSQueue();
        }

        ArrayList<Operator> operators = new ArrayList<>();
        operators.add(new PrisonOperator(0, -1, costFunction));
        operators.add(new PrisonOperator(1, 0, costFunction));
        operators.add(new PrisonOperator(-1, 0, costFunction));
        operators.add(new PrisonOperator(0, 1, costFunction));

        Search search = new Search(operators, initialState, goalTester, queue, visualize, printer);
        try {
            return search.startSearch();
        } catch (NoSolutionException e) {
            return null;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        writeKnowledgeBase();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the keyword for the search strategy:");
        System.out.println("BFS: BF");
        System.out.println("DFS: DF");
        System.out.println("Iterative Deepening: ID");
        System.out.println("Uniform Cost: UC");
        System.out.println("A* with heuristic 1: AS1");
        System.out.println("A* with heuristic 2: AS2");
        System.out.println("Greedy with heuristic 1: GR1");
        System.out.println("Greedy with heuristic 2: GR2");

        SearchType searchType;
        while(true) {
            try {
                searchType = SearchType.valueOf(sc.next());
                break;
            }
            catch (IllegalArgumentException e) {
                System.out.println("Unknown search strategy. Try again.");
                continue;
            }
        }

        System.out.println("Do you want to visualize expanded nodes? Y/N");
        boolean visualize;
        while(true) {
            String response = sc.next();
            if(response.equals("Y"))
                visualize = true;
            else if(response.equals("N"))
                visualize = false;
            else {
                System.out.println("Invalid response. Try Again (Y/N).");
                continue;
            }
            break;
        }

        PrisonSearch ps = new PrisonSearch();
        SearchResult result = ps.search(searchType, visualize);

        if(result == null)
            printer.println("NO SOLUTION");
        else {
            printer.println("---------- PATH ----------");
            for (State s : result.getPath())
                printer.println(s);

            printer.printf("Total cost: %d\n\n", result.getCost());
            printer.printf("Expanded nodes: %d\n\n", result.getExpandedNodes());
        }

        printer.flush();
        printer.close();
    }
}
