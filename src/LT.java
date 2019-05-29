/*
 * LT produces the HashSet of all values for the keys less than the given key
 */

import java.util.HashMap;
import java.util.HashSet;

public class LT<T extends Comparable<T>> implements Query {
    private String key;
    private T rbtKey;

    public LT(String key, T rbtKey) {
        this.key = key;
        this.rbtKey = rbtKey;
    }

    @Override
    public HashSet<Integer> execute(HashMap<String, MovieProperty> hm) {
        MovieProperty mp = hm.get(key);
        RedBlackTree<T, HashSet<Integer>> rbt = mp.getRBT();

        HashSet<Integer> result = new HashSet<>();
        for (T key : rbt.keys()) {
            if (lessThan(key, rbtKey)) {
                result.addAll(rbt.get(key));
            } else {
                break;
            }
        }

        return result;
    }

    private boolean lessThan(T object1, T object2) {
        return (object1.compareTo(object2) < 0);
    }
}