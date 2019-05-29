/*
 * Red Black Tree: balanced binary search tree.
 * Provides put/get functions, along with helper methods to balance the tree.
 */

import edu.princeton.cs.algs4.Queue;

public class RedBlackTree<Key extends  Comparable<Key>, Value> {

    private Node root;
    private static boolean RED = true;

    // Returns if tree contains given key
    public boolean contains(Key key) {
        if (key.equals(null)) throw new IllegalArgumentException();
        return get(key) != null;
    }

    // Get value of the given key
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return get(root, key);
    }

    private Value get(Node n, Key k) {
        if (n == null) {
            return null;
        }
        int compare = k.compareTo(n.key);
        if (compare < 0) {
            return get(n.left, k);
        } else if (compare > 0) {
            return get(n.right, k);
        }
        return n.value;
    }

    // Insert given key into the tree
    public void put(Key key, Value value) {
        if(key == null) throw new IllegalArgumentException();
        if (value == null) {
            return;
        }
        root = put(root, key, value);
        root.color = !RED;
    }

    private Node put(Node n, Key key, Value value) {
        if (n == null) {
            return new Node(key, value, RED);
        }
        int compare = key.compareTo(n.key);
        if (compare < 0) {
            n.left = put(n.left, key, value);
        } else if (compare > 0) {
            n.right = put(n.right, key, value);
        } else {
            n.value = value;
        }

        //Order of these if statements matter
        if (!isRed(n.left) && isRed(n.right)) {
            n = rotateLeft(n);
        }
        if (isRed(n.left) && isRed(n.left.left)) {
            n = rotateRight(n);
        }
        if (isRed(n.left) && isRed(n.right)) {
            flipColors(n);
        }

        return n;
    }

    // Flip the colors of the Node
    private void flipColors(Node n) {
        n.color = RED;
        n.left.color = !RED;
        n.right.color = !RED;
    }

    // Check if tree is a 2-3 tree
    public boolean is23() {
        return is23(root);
    }

    private boolean is23(Node n) {
        if (n == null) {
            return true;
        }
        if(isRed(n.right)) {
            return false;
        }
        if (isRed(n) && isRed(n.left)) {
            return false;
        }
        return is23(n.left) && is23(n.right);
    }

    // Check if tree is balanced
    public boolean isBalanced() {
        int blackHeight = 0;
        Node n = root;
        while(n != null) {
            if (!isRed(n)) {
                blackHeight++;
            }
            n = n.left;
        }
        return isBalanced(root, blackHeight);
    }

    private boolean isBalanced(Node n, int blackHeight) {
        if (n == null) {
            return blackHeight == 0;
        }
        if (!isRed(n)) {
            blackHeight--;
        }
        return isBalanced(n.left, blackHeight) && isBalanced(n.right, blackHeight);
    }

    // Generate a list of the keys in sorted order
    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        keys(root, queue);
        return queue;
    }

    private void keys(Node n, Queue<Key> q) {
        if (n == null) {
            return;
        }
        keys(n.left, q);
        q.enqueue(n.key);
        keys(n.right, q);
    }

    // Rotate Node to the right
    private Node rotateRight(Node n) {
        Node temp = n.left;
        n.left = temp.right;
        temp.right = n;
        temp.color = n.color;
        n.color = RED;
        temp.size = n.size;
        n.size = size(n.left) + size(n.right) + 1;
        return temp;
    }

    // Check if Node is red
    private boolean isRed(Node n) {
        if (n == null) {
            return false;
        }
        return n.color == RED;
    }

    // Rotate Node to the left
    private Node rotateLeft(Node n) {
        Node temp = n.right;
        n.right = temp.left;
        temp.left = n;
        temp.color = n.color;
        n.color = RED;
        temp.size = n.size;
        n.size = size(n.left) + size(n.right) + 1;
        return temp;
    }

    // Returns number of nodes in tree
    private int size(Node n) {
        if (n == null) {
            return 0;
        }
        return n.size;
    }

    // Tree is made up of Node objects
    private class Node {
        Key key;
        Value value;
        Node left, right;
        int size;
        boolean color;

        Node(Key key, Value value, boolean color){
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

}

