package pmf.spa3.graphs.utils;

import java.util.HashSet;
import java.util.Set;

public class UnionFind<E> {

    private Set<Set<E>> sets;
    private Set<E> elements;

    public void add(E e){
        if(elements.contains(e)) return;
        elements.add(e);
        Set<E> s = new HashSet<>();
        s.add(e);
        sets.add(s);
    }

    private Set<E> find(E e){
        if(!elements.contains(e))
            return null;
        for(Set<E> s: sets){
            if(s.contains(e)){
                return s;
            }
        }
        return null;
    }

    public boolean sameSet(E e1, E e2){
        Set<E> set1 = find(e1);
        Set<E> set2 = find(e2);

        if(set1 != null && set2 != null){
            return set1.contains(e2);
        }
        return false;
    }

    public void mergeSets(E e1, E e2){
        Set<E> set1 = find(e1);
        Set<E> set2 = find(e2);

        if (set1 != null && set2 != null) {
            set1.addAll(set2);
            sets.remove(set2); 
        }
    }

}
