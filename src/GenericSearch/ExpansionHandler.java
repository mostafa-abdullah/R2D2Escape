package GenericSearch;

import java.util.ArrayList;

class ExpansionHandler {
    /**
     * Generic expansion of a given state
     * Apply the operators in the search problems to get the new states
     * The new states are added to the search queue with the appropriate search strategy
     * @param queue: The search queue
     * @param currentState: The state to expand
     * @param operators: A list of operators applicable to the given state
     */
    static void expand(SearchQueue queue, State currentState, ArrayList<Operator> operators) {
        for(Operator op: operators) {
            State nxtState = op.applyOperator(currentState);
            if(nxtState != null) {
                queue.enqueue(nxtState);
            }
        }
    }
}
