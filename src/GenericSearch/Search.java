package GenericSearch;

import java.io.PrintWriter;
import java.util.*;


public class Search {
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

    public SearchResult startSearch() throws NoSolutionException {
        this.queue.enqueue(initialState);
        while(true) {
            State curState = this.queue.dequeue();
            this.queue.incExpanded();
            if(this.visualize)
                printer.println(curState);
            if(this.goalTester.test(curState)) {
                ArrayList<State> path = new ArrayList<>();
                State cur = curState;
                while(cur != null) {
                    path.add(cur);
                    cur = cur.parent;
                }
                Collections.reverse(path);
                return new SearchResult(path, curState.cost, this.queue.getExpanded());
            }
            ExpansionHandler.expand(this.queue, curState, this.operators);
        }
    }
}
