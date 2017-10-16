package GenericSearch;

public class NoSolutionException extends Exception{
    /**
     * An exception raised if no solution to the search problem exists.
     */
    public NoSolutionException() {
        super("No solution exists for this search problem.");
    }
}
