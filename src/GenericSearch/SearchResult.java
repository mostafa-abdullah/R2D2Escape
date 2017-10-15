package GenericSearch;

import java.util.ArrayList;

public class SearchResult {
    private ArrayList<State> path;
    private int cost, expandedNodes;

    public SearchResult(ArrayList<State> path, int cost, int expandedNodes) {
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
