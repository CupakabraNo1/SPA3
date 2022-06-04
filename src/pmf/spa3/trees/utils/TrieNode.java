package pmf.spa3.trees.utils;

import java.util.Map;
import java.util.TreeMap;

public class TrieNode {
    
    private Map<Character, TrieNode> children = new TreeMap<>();
    private int counter;
    private boolean wordEnd;
    
    public TrieNode() {
        this.children = new TreeMap<>();
        this.counter = 0;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, TrieNode> children) {
        this.children = children;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void increment(){
        this.counter++;
    }
    public void decrement(){
        this.counter--;
    }

}
