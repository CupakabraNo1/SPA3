package pmf.spa3.graphs.utils;

public class Distance implements Comparable<Distance>{
    int c;
    int distance;

    public int compareTo(Distance that) {
        if(this.distance > that.distance){
            return 1;
        }else{
            return -1;
        }
    }
    
}
