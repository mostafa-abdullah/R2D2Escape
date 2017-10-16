package GenericSearch;

public abstract class StateHeuristic {
    /**
     * Heuristic function used in A* and greedy search
     */
    protected boolean isGreedy; // isGreedy == false iff admissible
    public StateHeuristic(boolean isGreedy) {
        this.isGreedy = isGreedy;
    }
    public abstract int calculate(State s);
}
