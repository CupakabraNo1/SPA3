package pmf.spa3.trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Integer get(String word){
        Integer occ = 0;
        occ = get(root, new StringBuilder(word));
        return occ;
    }

    private Integer get(TrieNode curr, StringBuilder sb){
        Character ch = sb.charAt(0);
        sb.deleteCharAt(0);
        TrieNode next = curr.getChildren().get(ch);
        if(next == null) return 0;
        else {
            if(sb.length() == 0) return next.getCounter();
            else return get(next, sb);
        }
    }

    public List<String> all(){
        List<String> list = new ArrayList<>();
        all(root, list, new StringBuilder());
        return list;
    }

    private void all(TrieNode curr, List<String> list, StringBuilder sb){
        if(curr.getCounter() > 0) {
            list.add(sb.toString());
        }
        for (Entry<Character, TrieNode> e : curr.getChildren().entrySet()) {
            sb.append(e.getKey());
            all(e.getValue(), list, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> startsWith(String pref){
        List<String> list = new ArrayList<>();
        TrieNode newRoot = getNode(root, new StringBuilder(pref));
        if(newRoot != null) {
            all(newRoot, list, new StringBuilder(pref));
        }
        return list;
    }

    private TrieNode getNode(TrieNode curr, StringBuilder sb){
        if(sb.length() == 0){
            return curr;
        }
        Character ch = sb.charAt(0);
        sb.deleteCharAt(0);
        TrieNode next = curr.getChildren().get(ch);
        if(next == null) return null;
        return getNode(next, sb);
    }

    public boolean remove(String s){
        return remove(root, new StringBuilder(s));
    }

    private boolean remove(TrieNode current, StringBuilder sb){
        if(sb.length() == 0){
            current.decrement();
            if(current.getCounter() == 0 && current.getChildren().isEmpty()){
                current = null;
            }
            return true;
        }
        Character ch = sb.charAt(0);
        sb.deleteCharAt(0);
        TrieNode next = current.getChildren().get(ch);
        if(next != null) 
            return remove(next, sb);
        return false;
    }

    public List<String> mostPopular(){
        Map<Integer, List<String>> popularity = new HashMap();
        mostPopular(root, popularity, new StringBuilder());
        return popularity.entrySet().stream().max((o1, o2) -> o1.getKey().compareTo(o2.getKey())).get().getValue();
    }

    private void mostPopular(TrieNode current, Map<Integer, List<String>> popularity, StringBuilder sb){
        if(current.getCounter() > 0) {
            List<String> pop = popularity.get(current.getCounter());
            if(pop == null)
                pop = new ArrayList<>();
            pop.add(sb.toString());
            popularity.put(current.getCounter(), pop);
        }
        for(Entry<Character, TrieNode> entry : current.getChildren().entrySet()){
            sb.append(entry.getKey());
            mostPopular(entry.getValue(), popularity, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }




    
}
