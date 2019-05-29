/*
 * Equal returns the HashSet(of Movie IDs) for the given key.
 */

import java.util.HashMap;
import java.util.HashSet;

public class Equal<T extends Comparable<T>> implements Query {
    private String key;
    private T rbtKey;

    public Equal(String key, T rbtKey) {
        this.key = key;
        this.rbtKey = rbtKey;
    }

    @Override
    public HashSet<Integer> execute(HashMap<String, MovieProperty> hm) {
        MovieProperty mp = hm.get(key);
        RedBlackTree<T, HashSet<Integer>> rbt = mp.getRBT();
        return rbt.get(rbtKey);
    }

}
