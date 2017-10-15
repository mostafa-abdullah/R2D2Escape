package GenericSearch;

/**
 * Created by mostafa on 10/11/17.
 */
public abstract class State implements Comparable<State>{
    protected int cost;
    protected State parent;
    protected int level;
    public abstract String toString();
    public int getCost() {
        return this.cost;
    }
    public int getLevel() {
        return this.level;
    }
    public void incCost(int val) {
        this.cost += val;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
