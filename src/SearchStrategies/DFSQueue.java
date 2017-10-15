package SearchStrategies;

import GenericSearch.SearchQueue;
import GenericSearch.State;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class DFSQueue extends SearchQueue{
    public DFSQueue() {
        super(new Stack<>());
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
        Stack<State> q = (Stack<State>) this.queue;
        return q.pop();
    }
}
