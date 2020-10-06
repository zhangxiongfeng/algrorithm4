package graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Graph {
    // 顶点数目
    private final int V;
    // 边的数目
    private int E;
    private List<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        adj = new LinkedList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new LinkedList<>();
        }
    }

    public Graph(Scanner sc) {
        this(sc.nextInt());
        int E = sc.nextInt();
        for (int i = 0; i < E; i++) {
            int v = sc.nextInt();
            int w = sc.nextInt();
            addEdge(v, w);
        }
    }

    public int V() {
        return this.V;
    }

    public int E() {
        return this.E;
    }

    public List<Integer> adj(int v) {
        return adj[v];
    }


    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public void show() {
        for (int i = 0; i < E; i++) {
            System.out.print(i + ":");
            for (int j = 0; j < adj[i].size(); j++) {
                System.out.print(adj[i].get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Graph graph=new Graph(sc);
        graph.show();
    }

}
