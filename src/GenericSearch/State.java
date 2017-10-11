package GenericSearch;

/**
 * Created by mostafa on 10/11/17.
 */
public abstract class State {
    protected int cost;
    protected State parent;
    public abstract String toString();
    public int getCost() {
        return this.cost;
    }

    public void incCost(int val) {
        this.cost += val;
    }
}
