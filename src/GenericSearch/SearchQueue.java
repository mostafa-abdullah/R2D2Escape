package GenericSearch;

import PrisonSearch.PrisonOperator;
import PrisonSearch.PrisonState;

import java.util.Collection;

public abstract class SearchQueue {
    protected Collection<State> queue;

    public SearchQueue(Collection<State> queue) {
        this.queue = queue;
    }

    protected abstract void enqueue(State state);
    protected abstract State dequeue();
}
