package SearchStrategies;

import GenericSearch.SearchQueue;
import GenericSearch.State;

import java.util.LinkedList;
import java.util.Queue;

public class BFSQueue extends SearchQueue{
    public BFSQueue() {
        super(new LinkedList<>());
    }

    @Override
    protected void enqueue(State state) {
        if(!this.visitedStates.contains(state)) {
            this.visitedStates.add(state);
            this.queue.add(state);
        }
    }

    @Override
    protected State dequeue() {
        Queue<State> q = (LinkedList<State>) this.queue;
        return q.remove();
    }
}
