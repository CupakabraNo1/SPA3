package pmf.spa3.trees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import pmf.spa3.trees.utils.Node;

public class Tree<K extends Comparable<K>, V> {

    private Node<K, V> root;
    private Comparator<K> comparator;

    public Tree() {
        this(Comparator.naturalOrder());
    }

    public Tree(Comparator<K> comparator) {
        this.root = null;
        this.comparator = comparator;
    }

    public Tree(Node<K, V> root, Comparator<K> comparator) {
        this.root = root;
        this.comparator = comparator;
    }

    public void put(K key, V value) {
        if (root == null) {
            // to check if key is null
            comparator.compare(key, key);
            root = new Node<K, V>(key, value, null, null);
        }
        put(key, value, this.root);
    }

    private void put(K key, V value, Node<K, V> node) {
        // if(node == null ) return;
        int compare = this.comparator.compare(node.getKey(), key);
        if (compare == 0)
            node.setValue(value);
        else if (compare < 0)
            if (node.getRight() == null)
                node.setRight(new Node<K, V>(key, value, null, null));
            else
                put(key, value, node.getRight());
        else if (node.getLeft() == null)
            node.setLeft(new Node<K, V>(key, value, null, null));
        else
            put(key, value, node.getLeft());
    }

    public boolean containsKey(K key) {
        return containsKey(key, root);

    }

    private boolean containsKey(K key, Node<K, V> current) {
        if (current == null) {
            return false;
        }
        if (key == current.getKey()) {
            return true;
        }
        if (comparator.compare(key, current.getKey()) < 0)
            return containsKey(key, current.getLeft());
        else
            return containsKey(key, current.getRight());

    }

    public V get(K key) {
        return get(key, root);
    }

    private V get(K key, Node<K, V> current) {
        if (current == null) {
            return null;
        }
        if (current.getKey() == key) {
            return current.getValue();
        }
        if (comparator.compare(key, current.getKey()) < 0)
            return get(key, current.getLeft());
        else
            return get(key, current.getRight());
    }

    public K minKey() {
        Node<K, V> current = root;
        while (current.getLeft() != null) {
            current = current.getLeft();
        }
        return current.getKey();
    }

    public K maxKey() {
        Node<K, V> current = root;
        while (current.getRight() != null) {
            current = current.getRight();
        }
        return current.getKey();
    }

    public List<K> keysInRange(K a, K b) {
        List<K> list = new ArrayList<K>();
        keysInRange(a, b, root, list);

        return list;
    }

    private void keysInRange(K a, K b, Node<K, V> current, List<K> list) {
        if (current == null)
            return;
        if (comparator.compare(a, current.getKey()) <= 0 && comparator.compare(b, current.getKey()) >= 0) {
            list.add(current.getKey());
        }
        keysInRange(a, b, current.getLeft(), list);
        keysInRange(a, b, current.getRight(), list);
    }

    public int height() {
        int height = 0;
        height += height(root);
        return height;
    }

    private int height(Node<K, V> current) {
        if (current == null)
            return 0;
        else {
            int left = height(current.getLeft());
            int right = height(current.getRight());
            return left > right ? left + 1 : right + 1;
        }
    }

    public void printSideways() {
        printSideways(root, 0);
    }

    private static final String STEP = "\t";

    private void printSideways(Node<K, V> current, int level) {
        if (current == null)
            return;

        printSideways(current.getRight(), level + 1);
        for (int i = 0; i < level; i++) {
            System.out.print(STEP);
        }
        System.out.println("( " + current.getKey() + " | " + current.getValue() + " ) <");
        printSideways(current.getLeft(), level + 1);
    }

    public void balanceIfNeeded() {

    }

}
