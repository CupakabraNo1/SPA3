package pmf.spa3.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pmf.spa3.graphs.utils.WeightedEdge;

public class WeightedGraph {

    private int v;
    private List<WeightedEdge> edges;

    public WeightedGraph(int v) {
        this.v = v;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int start, int stop, int weight) {
        this.edges.add(new WeightedEdge(start, stop, weight));
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public List<WeightedEdge> getEdges() {
        return edges;
    }

    public void setEdges(List<WeightedEdge> edges) {
        this.edges = edges;
    }

    public List<Integer> getNeighbors(int i) {
        return this.edges.stream().filter(edge -> edge.contains(i)).map(edge -> edge.getOther(i))
                .collect(Collectors.toList());
    }

    public List<WeightedEdge> getEdgesFrom(int i) {
        return this.edges.stream().filter(edge -> edge.contains(i)).collect(Collectors.toList());
    }

    public Integer heaviestNeighbour() {
        int position = -1;
        Integer weight = null;
        for (int i = 0; i < v; i++) {
            int newWeight = getEdgesFrom(i).stream().map(edge -> edge.getWeight()).reduce(0, Integer::sum);
            if (i == 0 || newWeight > weight) {
                position = i;
                weight = newWeight;
            }
        }
        return position;
    }

    public Integer lightestNeigbours() {
        int position = -1;
        Integer weight = null;
        for (int i = 0; i < v; i++) {
            int newWeight = getEdgesFrom(i).stream().map(edge -> edge.getWeight()).reduce(0, Integer::sum);
            if (i == 0 || newWeight < weight) {
                position = i;
                weight = newWeight;
            }
        }
        return position;
    }

}
