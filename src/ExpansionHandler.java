import java.util.ArrayList;

/**
 * Created by mostafa on 10/11/17.
 */
public class ExpansionHandler {
    void expand(SearchQueue queue, State currentState, ArrayList<Operator> operators) {
        for(Operator op: operators) {
            State nxtState = op.applyOperator(currentState);
            if(nxtState != null)
                queue.enqueue(nxtState);
        }
    }
}
