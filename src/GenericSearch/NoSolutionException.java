package GenericSearch;

public class NoSolutionException extends Exception{
    public NoSolutionException() {
        super("No solution exists for this search problem.");
    }
}
