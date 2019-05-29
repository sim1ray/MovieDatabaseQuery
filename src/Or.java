import java.util.HashMap;
import java.util.HashSet;

public class Or<T extends Comparable<T>> implements Query {
    private Query[] q;

    public Or(Query... queries) {
        q = new Query[queries.length];
        for (int i = 0; i < queries.length; i++) {
            q[i] = queries[i];
        }
    }

    @Override
    public HashSet<Integer> execute(HashMap<String, MovieProperty> hm) {
        HashSet<Integer> s = new HashSet<>();
        for (int i = 0; i < q.length; i++) {
            s.addAll(q[i].execute(hm));   //intersection
        }
        return s;
    }
}