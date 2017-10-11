package SearchStrategies;

import GenericSearch.SearchQueue;
import GenericSearch.State;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by mostafa on 10/11/17.
 */
public class BFSQueue extends SearchQueue{
    public BFSQueue() {
        super(new LinkedList<>());
    }

    @Override
    protected void enqueue(State state) {
        this.queue.add(state);
    }

    @Override
    protected State dequeue() {
        Queue<State> q = (LinkedList<State>) this.queue;
        return q.remove();
    }


}
