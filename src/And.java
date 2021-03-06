/*
 * And returns the intersection of the HashSets produced by one or more Query objects.
 */

import java.util.HashMap;
import java.util.HashSet;

public class And<T extends Comparable<T>> implements Query {
    private Query[] q;

    public And(Query... queries) {
        q = new Query[queries.length];
        for (int i = 0; i < queries.length; i++) {
            q[i] = queries[i];
        }
    }

    @Override
    public HashSet<Integer> execute(HashMap<String, MovieProperty> hm) {
        HashSet<Integer> s = q[0].execute(hm);
        for (int i = 1; i < q.length; i++) {
            s.retainAll(q[i].execute(hm));   //intersection
        }
        return s;
    }
}
