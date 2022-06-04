package pmf.spa3.trees;

import java.util.Map.Entry;

import pmf.spa3.trees.utils.TrieNode;

public class Trie {
    
    private TrieNode root;

    public Trie(){
        this.root = new TrieNode();
    }
    public Trie(TrieNode root){
        this.root = root;
    }

    public void put(String s){
        put(root, new StringBuilder(s));
    }

    private void put(TrieNode current, StringBuilder sb){
        if(sb.length() == 0) return;
        Character ch = sb.charAt(0);
        TrieNode end = current.getChildren().get(ch);
        if(end == null) {
            end = new TrieNode();
            current.getChildren().put(ch, end);
        }
        
        sb.deleteCharAt(0);
        if(sb.length() == 0){
            end.increment();
        }else{
            put(end, sb);
        }
    }
    
    public void printAll(){
        printAll(root, new StringBuilder());
    }

    private void printAll(TrieNode current, StringBuilder sb) {
        if(current.getCounter() > 0) System.out.println(sb.toString());
        for (Entry<Character, TrieNode> e : current.getChildren().entrySet()) {
            sb.append(e.getKey());
            printAll(e.getValue(), sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
}
