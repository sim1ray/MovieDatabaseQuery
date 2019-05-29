/*
 * MovieProperty creates a red black tree for a field, and adds key/values to that red black tree.
 */

import java.util.HashSet;

public class MovieProperty<T extends Comparable<T>> {
    private RedBlackTree<T, HashSet<Integer>> rbt;

    public MovieProperty() {
        rbt = new RedBlackTree<>();
    }

    // add keys or values(append to HashSet if key already exists)
    public void add(T key, int id) {
        if (key == null) {
            return;
        }
        HashSet<Integer> hs;
        if (this.rbt.contains(key)) {
            hs = this.rbt.get(key);
        } else {
            hs = new HashSet<>();
        }
        hs.add(id);
        this.rbt.put(key, hs);
    }

    // Get the Red black tree
    public RedBlackTree<T, HashSet<Integer>> getRBT() {
        return rbt;
    }
}
