/*
 * NotEqual returns the HashSet(of Movie IDs) excluding the values for the given key.
 */

import java.util.HashMap;
import java.util.HashSet;

public class NotEqual<T extends Comparable<T>> implements Query {
    private String key;
    private T rbtKey;

    public NotEqual(String key, T rbtKey) {
        this.key = key;
        this.rbtKey = rbtKey;
    }

    @Override
    public HashSet<Integer> execute(HashMap<String, MovieProperty> hm) {
        MovieProperty mp = hm.get(key);
        RedBlackTree<T, HashSet<Integer>> rbt = mp.getRBT();
        HashSet<Integer> unwanted = rbt.get(rbtKey);
        HashSet<Integer> all = new HashSet<>();

        for (T key : rbt.keys()) {
            all.addAll(rbt.get(key));
        }

        all.removeAll(unwanted);
        return all;
    }
}

