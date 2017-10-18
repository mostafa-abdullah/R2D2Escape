package GenericSearch;

public abstract class StateHeuristic {
    /**
     * Heuristic function used in A* and greedy search
     */

    public abstract int calculate(State s);
}
