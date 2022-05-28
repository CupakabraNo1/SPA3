package pmf.spa3.graphs.utils;

public class WeightedEdge {

    private int start;
    private int stop;
    private int weight;

    public WeightedEdge(int start, int stop, int weight){
        this.start = start;
        this.stop = stop;
        this.weight = weight;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean contains(int element){
        return this.start == element || this.stop == element;
    }

    public Integer getOther(int element) {
        if(this.start == element) {
            return stop;
        }
        else if(this.stop == element) {
            return start;
        }
        else {
            return null;
        }
    }

}
