package GenericSearch;

import java.util.ArrayList;

public class SearchResult {
    /**
     * The result of the search problem:
     *      - A path of states from the initial state to the goal
     *      - The cost of this path
     *      - The number of expanded nodes during the search
     */
    private ArrayList<State> path;
    private int cost, expandedNodes;

    SearchResult(ArrayList<State> path, int cost, int expandedNodes) {
        this.path = path;
        this.cost = cost;
        this.expandedNodes = expandedNodes;
    }

    public ArrayList<State> getPath() {
        return path;
    }

    public int getCost() {
        return cost;
    }

    public int getExpandedNodes() {
        return expandedNodes;
    }
}
