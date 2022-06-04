package pmf.spa3;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import pmf.spa3.graphs.DirectionalGraph;
import pmf.spa3.graphs.Graph;
import pmf.spa3.graphs.WeightedGraph;

public class Main {

    public static void main(String[] args) {
        initWeightedGraph();
    }

    public static void initGraph() {
        Graph graph1 = new Graph("src/pmf/spa3/assets/tinyG.txt");
        graph1.printGraph();
        Set<Integer> result = graph1.component(0);
        System.out.print("Component <");
        result.forEach(System.out::print);
        System.out.print(">\n");
        result = graph1.component(3);

        boolean exists = graph1.pathExistsDFS(0, 3);
        System.out.println(exists);

        List<Integer> path = graph1.findPathDFS(0, 3);
        System.out.print("Path between 0 and 3 is: < ");
        Iterator<Integer> i = path.iterator();
        while (i.hasNext()) {
            Integer value = i.next();
            System.out.print(value + " ");
        }
        System.out.println(" >\n");

        graph1.iterativeDFS(0);

        System.out.println("Distance to point 3: ");
        System.out.println(graph1.distanceToPoint(3).toString());

        System.out.println("Graph " + (graph1.isBipartitiv() ? "is" : "is not") + " bipartitiv");
    }

    public static void initDirectionalGraph(){
        DirectionalGraph graph2 = new DirectionalGraph("src/pmf/spa3/assets/tinyG.txt");
        System.out.printf("Number of components is %d \n", graph2.getNumberOfComponents());
        
        graph2.printDegrees();
        System.out.print("sources are: ");
        for (Integer i : graph2.findSources()) {
            System.out.print("(" + i +") ");
        }
        System.out.print("\n");

        System.out.print("confluences are: ");
        for (Integer i : graph2.findConfluences()) {
            System.out.print("(" + i + ") ");
        }
        System.out.print("\n");

        System.out.printf("From 0 every vertex %s be reached.\n", graph2.canAccessAllOthers(0) ? "can": "can't");
    }

    public static void initWeightedGraph(){
        WeightedGraph graph3 = new WeightedGraph(5);
        graph3.addEdge(0, 1, 1);
        graph3.addEdge(0, 2, 5);
        graph3.addEdge(1, 2, 10);
        graph3.addEdge(2, 3, 1);
        graph3.addEdge(1, 4, 8);
        graph3.addEdge(2, 4, 1);


        System.out.printf("Heaviest neigbours have edge %d\n", graph3.heaviestNeighbour());
        System.out.printf("Lightest neigbours have edge %d\n", graph3.lightestNeigbours());

        List<Integer> path = graph3.lightestPathBetween(0, 4);
        System.out.println("Path between 0 and 3 is:");
        path.forEach(x -> System.out.print("( "+x+" ) "));


    }
}
