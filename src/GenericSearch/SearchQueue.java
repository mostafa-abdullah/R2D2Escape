package GenericSearch;

import PrisonSearch.PrisonOperator;
import PrisonSearch.PrisonState;

import java.util.Collection;
import java.util.TreeSet;

public abstract class SearchQueue {
    protected Collection<State> queue;
    protected TreeSet<State> visitedStates;
    private int expanded = 0;

    public SearchQueue(Collection<State> queue) {
        this.visitedStates = new TreeSet<>();
        this.queue = queue;
    }

    protected abstract void enqueue(State state);
    protected abstract State dequeue() throws NoSolutionException;

    public void incExpanded() {
        this.expanded++;
    }

    public int getExpanded(){
        return this.expanded;
    }

}
