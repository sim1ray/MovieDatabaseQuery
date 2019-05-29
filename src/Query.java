/*
 * Query interface for multiple queries such as Equal, And, Or, Not, NotEqual.
 */

import java.util.HashMap;
import java.util.HashSet;

public interface Query {
    // Execute the query and return a HashSet of movie Ids that satisfy the query
    HashSet<Integer> execute(HashMap<String, MovieProperty> hm);
}
