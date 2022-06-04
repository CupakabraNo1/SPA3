package pmf.spa3.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pmf.spa3.graphs.utils.SPW;
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

    public List<Integer> lightestPathBetween(int start, int end) {
        // 1)
        //  *dokle god imamo neke popravke tezina
        //      *za svaku granu
        //          *proveriti da li je kraci put preko nje do cvorova kojima pripada
        //          *osveziti duzine ako treba
        //          *postaviti da smo osvezili nesto


        // 2)
        //  *neka vrst Dijkstre

        SPW paths = new SPW(this, start);
        return paths.shortestPathTo(end);
    }
    
    public boolean pathBetween(int a, int b, int n){
        // ili modifikovati graf da nema te grane ili modifikovati algoritam da ih
        // ne posmatra

        WeightedGraph safeGraph = new WeightedGraph(v);
        for (WeightedEdge weightedEdge : edges) {
            if(weightedEdge.getWeight() <= n) {
                safeGraph.addEdge(weightedEdge.getStart(), weightedEdge.getStop(), weightedEdge.getWeight());
            }   
        }

        SPW paths = new SPW(safeGraph, a);
        return !paths.shortestPathTo(b).isEmpty();
    }

    public boolean pathBetweenEven(int a, int b, int n) {
        // ili modifikovati graf da spaja grane uz sabiranje tezina (par -(5)-> nepar -(2)-> par = par -(7)-> par)
        // ili modifikovati algoritam da pamti koliko je daha upotrebljeno pri prelasku na neparni cvo
        return true;
    }

    public String toString(){
        return "Weihted graph with "+v+" nodes and "+edges.size()+" edges";
    }

    

}
