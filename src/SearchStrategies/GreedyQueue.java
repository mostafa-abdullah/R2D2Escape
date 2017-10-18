package SearchStrategies;

import GenericSearch.StateHeuristic;

import java.util.PriorityQueue;

public class GreedyQueue extends UniformCostQueue {
    public GreedyQueue(StateHeuristic heuristic) {
        this.queue = new PriorityQueue<>((s1, s2) -> heuristic.calculate(s1) - heuristic.calculate(s2));
    }
}