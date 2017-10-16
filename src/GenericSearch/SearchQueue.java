package GenericSearch;

import java.util.Collection;
import java.util.TreeSet;

public abstract class SearchQueue {
    /**
     * The data structure storing the expanded search nodes.
     * The queuing function is implemented depending on the search strategy.
     */
    protected Collection<State> queue;
    protected TreeSet<State> visitedStates;
    private int expanded = 0;

    public SearchQueue(Collection<State> queue) {
        this.visitedStates = new TreeSet<>();
        this.queue = queue;
    }

    protected abstract void enqueue(State state);
    protected abstract State dequeue() throws NoSolutionException;

    void incExpanded() {
        this.expanded++;
    }

    int getExpanded(){
        return this.expanded;
    }

}
