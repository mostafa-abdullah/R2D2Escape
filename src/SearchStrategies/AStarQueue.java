package SearchStrategies;

import GenericSearch.State;
import GenericSearch.StateHeuristic;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarQueue extends UniformCostQueue {
    public AStarQueue(StateHeuristic heuristic) {
        this.queue = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State s1, State s2) {
                return heuristic.calculate(s1) - heuristic.calculate(s2);
            }
        });
    }
}
