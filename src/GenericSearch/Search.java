package GenericSearch;

import java.io.PrintWriter;
import java.util.*;


public class Search {
    /**
     * The main generic search problem
     * Consists of the following main properties:
     *      - List of applicable operators
     *      - The initial state
     *      - The goal tester
     *      - The search queue
     */
    private ArrayList<Operator> operators;
    private State initialState;
    private GoalTester goalTester;
    private SearchQueue queue;
    private boolean visualize;
    private PrintWriter printer;

    public Search(ArrayList<Operator> operators, State initialState, GoalTester goalTester,
           SearchQueue queue, boolean visualize, PrintWriter printer) {
        this.operators = operators;
        this.initialState = initialState;
        this.goalTester = goalTester;
        this.queue = queue;
        this.visualize = visualize;
        this.printer = printer;
    }

    /**
     * Start search with the appropriate search strategy that is defined by the search queue
     * @return Path from initlal state to goal state, cost of the path, number of expanded nodes
     * @throws NoSolutionException
     */
    public SearchResult startSearch() throws NoSolutionException {
        this.queue.enqueue(initialState);
        while(true) {
            State curState = this.queue.dequeue();
            this.queue.incExpanded(); // Increment number of expanded nodes
            if(this.visualize)
                printer.println(curState);

            if(this.goalTester.test(curState)) {
                // reached the goal: build the path
                ArrayList<State> path = new ArrayList<>();
                State cur = curState;
                while(cur != null) {
                    path.add(cur);
                    cur = cur.parent;
                }
                Collections.reverse(path);
                return new SearchResult(path, curState.cost, this.queue.getExpanded());
            }

            // Expand this state
            ExpansionHandler.expand(this.queue, curState, this.operators);
        }
    }
}
