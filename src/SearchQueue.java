import java.util.Collection;

public abstract class SearchQueue {
    private Collection<Operator> queue;
    abstract void enqueue(State state);
    abstract State dequeue(State state);
}
