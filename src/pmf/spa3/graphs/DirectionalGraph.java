package pmf.spa3.graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DirectionalGraph {

    private int v;
    private int e;
    private List<Set<Integer>> neighbours;
    private List<Set<Integer>> components;
    private List<Integer> inDegrees;
    private List<Integer> outDegrees;

    private int currentComponent;

    public DirectionalGraph(int v) {
        this.v = v;
        neighbours = new ArrayList<>(v);
        components = new ArrayList<>();
        inDegrees = new ArrayList<>(v);
        outDegrees = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            inDegrees.add(0);
            outDegrees.add(0);
        }
    }

    public DirectionalGraph(String imef) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(new File(imef)));
            v = Integer.parseInt(br.readLine());
            e = Integer.parseInt(br.readLine());

            neighbours = new ArrayList<>(v);
            components = new ArrayList<>();

            inDegrees = new ArrayList<>(v);
            outDegrees = new ArrayList<>(v);
            for (int i = 0; i < v; i++) {
                inDegrees.add(0);
                outDegrees.add(0);
            }

            for (int i = 0; i < v; i++) {
                neighbours.add(new HashSet<>());
            }

            String pom;

            while ((pom = br.readLine()) != null) {
                String[] par = pom.split(" ");
                int v1 = Integer.parseInt(par[0]);
                int v2 = Integer.parseInt(par[1]);

                addEdge(v1, v2);
            }

            components();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean addEdge(int a, int b) {

        try {
            neighbours.get(a).add(b);
            inDegrees.set(b, inDegrees.get(b) + 1);
            outDegrees.set(a, outDegrees.get(a) + 1);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public void components() {
        Set<Integer> visited = new HashSet<>();
        currentComponent = -1;
        for (int i = 0; i < v; i++) {
            if (!visited.contains(i))
                currentComponent++;
            components.add(new HashSet<>());
            dfs(i, visited);
        }
    }

    private void dfs(int index, Set<Integer> visited) {
        visited.add(index);
        components.get(currentComponent).add(index);
        for (Integer i : neighbours.get(index)) {
            if (!visited.contains(i)) {
                dfs(i, visited);
            } else {
                int componentNeighbour = component(i);
                if (componentNeighbour != currentComponent) {
                    components.get(currentComponent).addAll(components.get(componentNeighbour));
                    components.remove(componentNeighbour);
                    currentComponent--;
                }
            }
        }

    }

    private int component(int i) {
        for (int j = 0; j < components.size(); j++) {
            if (components.get(j).contains(i)) {
                return j;
            }
        }
        return -1;
    }

    public void cycle() {
        boolean[] marked = new boolean[v];
        boolean[] onStack = new boolean[v];
        int[] edgeTo = new int[v];
        Stack<Integer> cycle = null;
        for (int i = 0; i < v; i++) {
            if (!marked[i] && cycle == null)
                dfsC(i, marked, onStack, edgeTo, cycle);
        }
    }

    private void dfsC(int index, boolean[] marked, boolean[] onStack, int[] edgeTo, Stack<Integer> cycle) {
        onStack[index] = true;
        marked[index] = true;
        for (int i : neighbours.get(index)) {
            if (cycle != null)
                return;
            if (!marked[i]) {
                edgeTo[i] = index;
                dfsC(i, marked, onStack, edgeTo, cycle);
            } else if (onStack[i]) {
                cycle = new Stack<>();
                for (int j = index; j != i; j = edgeTo[j]) {
                    cycle.push(j);
                }
                cycle.push(i);
                cycle.push(index);
            }
        }
        onStack[index] = false;
    }

    public Set<Integer> findSources() {
        Set<Integer> sources = new HashSet<>();

        for (int i = 0; i < v; i++) {
            if (getInDegree(i) == 0 && getOutDegree(i) > 0) {
                sources.add(i);
            }
        }
        return sources;

    }

    public Set<Integer> findConfluences() {
        Set<Integer> conf = new HashSet<>();

        for (int i = 0; i < v; i++) {
            if (getInDegree(i) > 0 && getOutDegree(i) == 0) {
                conf.add(i);
            }
        }
        return conf;

    }

    public boolean canBeAccessedFromAll() {
        for (int i = 0; i < v; i++) {
            return !(getInDegree(i) == 0);
        }
        return true;
    }

    public boolean canAccessAllOthers(int i) {
        boolean result = false;
        List<Integer> visited = new ArrayList<>();
        dfsA(i, visited);
        if (visited.size() == v)
            result = true;

        return result;

    }

    private void dfsA(int index, List<Integer> visited) {
        if (visited.contains(index))
            return;
        visited.add(index);
        for (Integer i : neighbours.get(index)) {
            dfsA(i, visited);
        }
    }

    public DirectionalGraph transitveClousure() {
        DirectionalGraph newGraph = new DirectionalGraph(v);
        List<Integer> visited = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            dfsT(i, visited);
            newGraph.neighbours.get(i).addAll(visited);
            visited = new ArrayList<>();
        }
        return newGraph;
    }

    private void dfsT(int index, List<Integer> visited) {
        if (visited.contains(index)) {
            return;
        }

        visited.add(index);
        for (Integer i : neighbours.get(index)) {
            dfsT(i, visited);
        }
    }

    public Integer findOneAccessingAll() {
        DirectionalGraph transitive = transitveClousure();
        for (int i = 0; i < v; i++) {
            if (transitive.neighbours.get(i).size() == v) {
                return i;
            }
        }
        return null;

    }

    public Integer findOneAccessedByAll() {
        Set<Integer> vertices = new HashSet<>();
        DirectionalGraph transitive = transitveClousure();
        for (int i = 0; i < v; i++) {
            final int position = i;
            if (vertices.isEmpty()) {
                vertices.addAll(transitive.neighbours.get(i));
            } else {
                vertices.removeIf((Integer x) -> !transitive.neighbours.get(position).contains(x));
            }
        }

        for (Integer i : vertices) {
            return i;
        }
        return null;

    }

    public List<Set<Integer>> getComponents() {
        return components;
    }

    public void setComponents(List<Set<Integer>> components) {
        this.components = components;
    }

    public int getCurrentComponent() {
        return currentComponent;
    }

    public void setCurrentComponent(int currentComponent) {
        this.currentComponent = currentComponent;
    }

    public int getNumberOfComponents() {
        return getCurrentComponent() + 1;
    }

    private int getInDegree(int x) {
        return inDegrees.get(x);
    }

    private int getOutDegree(int x) {
        return outDegrees.get(x);
    }

    public void printDegrees() {
        for (int i = 0; i < v; i++) {
            System.out.printf("V %d -> <%d, %d>\n", i, getInDegree(i), getOutDegree(i));
        }
    }

}
