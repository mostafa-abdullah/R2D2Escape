package SearchStrategies;

import GenericSearch.State;
import GenericSearch.StateHeuristic;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStarGreedyQueue extends UniformCostQueue {
    public AStarGreedyQueue(StateHeuristic heuristic) {
//        this.queue = new PriorityQueue<>((s1, s2) -> heuristic.calculate(s1) - heuristic.calculate(s2));
        this.queue = new PriorityQueue<>(new Comparator<State>() {
            @Override
            public int compare(State s1, State s2) {
                return heuristic.calculate(s1) - heuristic.calculate(s2);
            }
        });
    }
}
