package pmf.spa3.graphs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Graph {

    private int v;
    private int e;

    // koristimo listu skupova suseda
    private List<Set<Integer>> neighbours;

    private List<Set<Integer>> components;

    private List<Boolean> colors;

    boolean bipartitiv = true;

    public Graph(int v) {
        this.v = v;
        neighbours = new ArrayList<>(v);
        components = new ArrayList<>();
        colors = new ArrayList<>(v);
        bipartitiv();
    }

    public Graph(String imef) {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(new File(imef)));
            v = Integer.parseInt(br.readLine());
            e = Integer.parseInt(br.readLine());

            neighbours = new ArrayList<>(v);

            // inicijalizacija svih skupova suseda
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

            components = new ArrayList<>();
            colors = new ArrayList<>(v);
            createComponents();
            bipartitiv();

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
            neighbours.get(b).add(a);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Set<Integer> getNeighbours(int vertex) {
        // moglo da se napravi da vraca Iterable<Integer>

        /*
         * ako je bitno da niko ne dira internu strukturu,
         * moze se koristiti Collections.unmodifiableSet, koji
         * ce napraviti omotac, i nece dati da se menja original
         */
        return neighbours.get(vertex);
    }

    public int getV() {
        return v;
    }

    public void printGraph() {
        System.out.println("Graph has " + v + " vertices.");
        for (int i = 0; i < v; i++) {
            Set<Integer> n = neighbours.get(i);
            for (int j : n) {
                System.out.println("edge: " + i + "-" + j);
            }
        }
    }

    public Set<Integer> component(int c) {
        Set<Integer> finish = new HashSet<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        component(c, finish, visited);
        return finish;
    }

    private void component(int c, Set<Integer> comp, Set<Integer> visited) {
        if (visited.contains(c))
            return;
        visited.add(c);
        comp.add(c);
        for (int s : neighbours.get(c)) {
            component(s, comp, visited);
        }
    }

    private void createComponents() {
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < this.v; i++) {
            if (!visited.contains(i)) {
                Set<Integer> result = this.component(i);
                visited.addAll(result);
                components.add(result);
            }
        }

        System.out.println("Components:");
        components.forEach((t) -> {
            System.out.print("< ");
            t.forEach((element) -> {
                System.out.print(element + " ");
            });
            System.out.print(">\n");
        });
    }

    public boolean pathExistsDFS(int start, int end) {
        List<Integer> path = new ArrayList<>();
        dfs(start, end, path);
        return path.size() > 0;

    }

    private void dfs(Integer start, Integer end, List<Integer> path) {
        if (path.contains(start)) {
            return;
        }
        path.add(start);
        if (neighbours.get(start).contains(end)) {
            return;
        }
        for (Integer i : neighbours.get(start)) {
            dfs(i, end, path);
        }
        path.remove(path.size() - 1);
        return;

    }

    public List<Integer> findPathDFS(int start, int end) {
        List<Integer> path = new LinkedList<>();
        dfs(start, end, path);
        return path;
    }

    public void iterativeDFS(Integer start) {
        Stack<Integer> stack = new Stack<Integer>();
        List<Integer> visited = new ArrayList<Integer>();
        stack.push(start);
        Integer s = null;
        while (!stack.isEmpty()) {
            // System.out.println(stack.toString());
            s = stack.pop();
            if (visited.contains(s)) {
                continue;
            }
            neighbours.get(s).forEach((Integer item) -> stack.push(item));
            visited.add(s);
        }

    }

    public List<Integer> distanceToPoint(Integer start) {
        List<Integer> distance = new ArrayList<>(this.v);
        List<Integer> visited = new ArrayList<>();
        for (int i = 0; i < this.v; i++) {
            distance.add(0);
        }

        Stack<Integer> stack = new Stack<Integer>();
        bfs(start, stack, distance, visited);
        return distance;
    }

    private void bfs(Integer start, Stack<Integer> stack, List<Integer> distance, List<Integer> visited) {
        stack.push(start);
        Integer value = null;
        while (!stack.isEmpty()) {
            value = stack.pop();
            for (Integer i : neighbours.get(value)) {
                if (!visited.contains(i)) {
                    if (distance.get(i) == 0) {
                        distance.set(i, distance.get(value) + 1);
                    }
                    stack.push(i);
                }
            }
            visited.add(value);
        }
        return;
    }

    public void bipartitiv() {
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < v; i++) {
            if (!colors.get(i)) {
                dfsB(i, visited);
            }
        }

    }

    private void dfsB(int index, Set<Integer> visited) {
        visited.add(index);
        for (int i : neighbours.get(index)) {
            if (!visited.contains(i)) {
                colors.set(i, !colors.get(index));
                dfsB(i, visited);
            } else {
                if (colors.get(index) == colors.get(i)) {
                    setBipartitiv(false);
                }
            }
        }
    }

    public Graph createThree(){
        Graph res = new Graph(v);
        List<Integer> visited = new ArrayList<>();
        dfsT(0, visited, res);
        if(visited.size() == v) return res;
        else return null;
    }

    private void dfsT(int index, List<Integer> visited, Graph res){
        if(visited.contains(index)) return;
        visited.add(index);
        for(int i: neighbours.get(index)){
            res.addEdge(index, i);
            dfsT(i, visited, res);
        }
    }

    public void setBipartitiv(boolean bipartitiv) {
        this.bipartitiv = bipartitiv;
    }

    public boolean isBipartitiv(){
        return this.bipartitiv;
    }

}
