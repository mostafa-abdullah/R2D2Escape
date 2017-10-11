package GenericSearch;

import PrisonSearch.PrisonGoalTester;
import PrisonSearch.PrisonState;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Stack;

/**
 * Created by mostafa on 10/11/17.
 */
public class Search {
    private ArrayList<Operator> operators;
    private PrisonState initialState;
    private GoalTester goalTester;
    private SearchQueue queue;
    private boolean visualize;
    private PrintWriter printer;

    public Search(ArrayList<Operator> operators, PrisonState initialState, GoalTester goalTester,
           SearchQueue queue, boolean visualize, PrintWriter printer) {
        this.operators = operators;
        this.initialState = initialState;
        this.goalTester = goalTester;
        this.queue = queue;
        this.visualize = visualize;
        this.printer = printer;
    }

    public ArrayList<State> startSearch() {
        this.queue.enqueue(initialState);
        while(true) {
            State curState = this.queue.dequeue();
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
                return path;
            }
            ExpansionHandler.expand(this.queue, curState, this.operators);
        }
    }
}
