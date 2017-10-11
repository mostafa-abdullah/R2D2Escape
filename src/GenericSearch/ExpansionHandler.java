package GenericSearch;

import PrisonSearch.PrisonState;

import java.util.ArrayList;

public class ExpansionHandler {
    static void expand(SearchQueue queue, State currentState, ArrayList<Operator> operators) {
        for(Operator op: operators) {
            State nxtState = op.applyOperator(currentState);
            if(nxtState != null)
                queue.enqueue(nxtState);
        }
    }
}
