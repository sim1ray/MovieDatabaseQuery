import java.util.HashSet;

public class MovieProperty<T extends Comparable<T>> {
    private RedBlackTree<T, HashSet<Integer>> rbt;

    public MovieProperty() {
        rbt = new RedBlackTree<>();
    }

    public void add(T key, int id) {
        HashSet<Integer> hs = new HashSet<>();
        if (this.rbt.contains(key)) {
            hs = this.rbt.get(key);
        }
        hs.add(id);
        this.rbt.put(key, hs);
    }

    public RedBlackTree<T, HashSet<Integer>> getRBT() {
        return rbt;
    }
}
