package pmf.spa3.graphs.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pmf.spa3.graphs.WeightedGraph;

// Shortest path weighted (Dijkstra)
public class SPW {

    private WeightedGraph graph;
    private Set<Integer> visited;
    private Set<Integer> notVisited;
    private Integer source;
    private List<Integer> distance;
    private List<List<Integer>> shortestPaths;

    private static final Integer INFINITE_DISTANCE = Integer.MAX_VALUE;

    public SPW(WeightedGraph graph, int source) {
        this.graph = graph;
        System.out.println(this.graph);
        this.source = source;
        this.visited = new HashSet<>();
        this.notVisited = new HashSet<>();
        this.distance = new ArrayList<Integer>();
        this.shortestPaths = new ArrayList<>();
        for (Integer i = 0; i < graph.getV(); i++) {
            if (i == source) {
                distance.add(0);
            } else {
                distance.add(INFINITE_DISTANCE);
            }
            shortestPaths.add(new ArrayList<>());
        }
        notVisited.add(source);
        this.dijkstra();
    }

    private void dijkstra() {
        while (!notVisited.isEmpty()) {
            System.out.println("NV "+notVisited.toString());
            Integer current = getLowestDistanceNode();
            notVisited.remove(current);
            for (WeightedEdge neig : this.graph.getEdgesFrom(current)) {
                // System.out.println("SPTN ( "+neig.getOther(current)+" ) "+shortestPaths.get(neig.getOther(current)).toString());
                if (neig.getOther(current) == source){
                    continue;
                }
                if (calculateMinimumDistance(current, neig.getOther(current), neig.getWeight()))
                    if (!notVisited.contains(neig.getOther(current)))
                        notVisited.add(neig.getOther(current));

            }
            System.out.println("----------------------------------");
            visited.add(current);
        }
    }

    private boolean calculateMinimumDistance(int curr, int neig, int weight) {
        Integer distanceToSource = distance.get(curr);
        if (distanceToSource + weight < distance.get(neig)) {
            distance.set(neig, distanceToSource + weight);
            List<Integer> shortestToCurrent = new ArrayList<Integer>();
            shortestToCurrent.addAll(shortestPaths.get(curr));
            shortestToCurrent.add(neig);
            shortestPaths.set(neig, shortestToCurrent);
            System.out.println(shortestPaths.toString());
            return true;
        }
        return false;
    }

    private Integer getLowestDistanceNode() {
        Integer ldElement = null;
        Integer ld = INFINITE_DISTANCE;
        for (Integer i : notVisited) {
            Integer distance = this.distance.get(i);
            if (distance <= ld) {
                ldElement = i;
                ld = distance;
            }
        }
        System.out.println(ldElement);
        return ldElement;
    }

    public List<Integer> shortestPathTo(Integer node) {
        return shortestPaths.get(node);
    }

}
