/*
 * Not returns the HashSet(of Movie IDs) excluding the values produced from the given Query.
 */

import java.util.HashMap;
import java.util.HashSet;

public class Not<T extends Comparable<T>> implements Query {
    private Query q;

    public Not(Query q) {
        this.q = q;
    }

    @Override
    public HashSet<Integer> execute(HashMap<String, MovieProperty> hm) {
        HashSet<Integer> s = q.execute(hm);
        MovieProperty mp = hm.get("year");  //Any RBT will contain all the movie ids
        RedBlackTree<T, HashSet<Integer>> rbt = mp.getRBT();
        HashSet<Integer> all = new HashSet<>();
        for (T key : rbt.keys()) {
            all.addAll(rbt.get(key));
        }
        all.removeAll(s);
        return all;
    }
}
