package SearchStrategies;

import GenericSearch.StateHeuristic;

import java.util.PriorityQueue;

public class AStarQueue extends UniformCostQueue {
    public AStarQueue(StateHeuristic heuristic) {
        this.queue = new PriorityQueue<>((s1, s2) -> (s1.getCost() + heuristic.calculate(s1)) - (s2.getCost() + heuristic.calculate(s2)));
    }
}
