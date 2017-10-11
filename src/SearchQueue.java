import java.util.ArrayList;

/**
 * Created by mostafa on 10/11/17.
 */
public abstract class SearchQueue {
    private ArrayList<Operator> queue;
    SearchQueue() {
        this.queue = new ArrayList<>();
    }

    abstract void enqueue(State state);
    abstract State dequeue(State state);
}
