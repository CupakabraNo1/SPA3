package pmf.spa3.trees.utils;

public class Node<K, V>  {

    private K key;
    private V value;
    private Node<K,V> left, right;

    public Node() {
        left = null;
        right = null;
    }

    public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Node<K,V> getLeft() {
        return left;
    }

    public Node<K,V> getRight() {
        return right;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}
