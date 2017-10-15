package SearchStrategies;

import GenericSearch.NoSolutionException;
import GenericSearch.SearchQueue;
import GenericSearch.State;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UniformCostQueue extends SearchQueue{
    public UniformCostQueue() {
        super(new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State s1, State s2) {
                return s1.getCost() - s2.getCost();
            }
        }));
    }

    @Override
    protected void enqueue(State state) {
        if(!this.visitedStates.contains(state)) {
            this.visitedStates.add(state);
            this.queue.add(state);
        }
        else if(this.visitedStates.ceiling(state).getCost() > state.getCost()){
            this.visitedStates.ceiling(state).setCost(state.getCost());
            this.queue.add(state);
        }
    }

    @Override
    protected State dequeue() throws NoSolutionException {
        if(this.queue.size() == 0)
            throw new NoSolutionException();
        PriorityQueue<State> q = (PriorityQueue<State>) this.queue;
        State nxt = q.remove();
        if(this.visitedStates.ceiling(nxt).getCost() == nxt.getCost())
            return nxt;
        return this.dequeue();
    }
}
