import java.util.HashMap;
import java.util.HashSet;

public class GTE<T extends Comparable<T>> implements Query {
    private String key;
    private T rbtKey;

    public GTE(String key, T rbtKey) {
        this.key = key;
        this.rbtKey = rbtKey;
    }

    @Override
    public HashSet<Integer> execute(HashMap<String, MovieProperty> hm) {
        MovieProperty mp = hm.get(key);
        RedBlackTree<T, HashSet<Integer>> rbt = mp.getRBT();

        HashSet<Integer> result = new HashSet<>();
        for (T key : rbt.keys()) {
            if (greaterThanEqual(key, rbtKey)) {
                result.addAll(rbt.get(key));
            }
        }

        return result;
    }

    private boolean greaterThanEqual(T object1, T object2) {
        return (object1.compareTo(object2) >= 0);
    }
}
