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
        HashSet<Integer> s = new HashSet<>();
        for (int i = 0; i < q.length; i++) {
            s.retainAll(q[i].execute(hm));   //intersection
        }
        return s;
    }
}
