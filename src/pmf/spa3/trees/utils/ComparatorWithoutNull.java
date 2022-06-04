package pmf.spa3.trees.utils;

import java.util.Comparator;

public class ComparatorWithoutNull implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
    
}
