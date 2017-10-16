package SearchStrategies;

import GenericSearch.SearchQueue;
import GenericSearch.State;

import java.util.Stack;

public class IterativeDeepeningQueue extends SearchQueue{
    private int level;
    private State initialState;
    public IterativeDeepeningQueue(State initialState) {
        super(new Stack<>());
        this.initialState = initialState;
    }

    @Override
    protected void enqueue(State state) {
        if(state.getLevel() <= this.level)
            if(!this.visitedStates.contains(state)) {
                this.visitedStates.add(state);
                this.queue.add(state);
            }
    }

    @Override
    protected State dequeue() {
        if(this.queue.size() == 0) {
            this.level++;
            this.visitedStates.clear();
            return this.initialState;
        }
        Stack<State> q = (Stack<State>) this.queue;
        return q.pop();
    }
}
