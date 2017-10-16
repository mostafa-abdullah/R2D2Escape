package GenericSearch;

public abstract class GoalTester {
    /**
     * Test wether a given state is the goal state or not.
     * @param state: The state to test against
     * @return true if `state` is the goal, false otherwise
     */
    public abstract boolean test(State state);
}
