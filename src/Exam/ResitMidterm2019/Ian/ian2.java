package Exam.ResitMidterm2019.Ian;

//import java.io.*;
import java.util.*;

class Citagazze {

  /**
   * You should implement this method.
   * @param n the number of inhabitants in the city.
   * @param k the number of sections the city needs to split into.
   * @param r 2D-array with all the risks between two people, r[i][j] represents the risk of the virus spreading from inhabitant \\(1 \leq i \leq n\\) to inhabitant \\(1 \leq j \leq n\\). You should ignore r[0][j] and r[i][0].
   * @param families a set of sets representing families. Every set in families contains the integer ids i (\\(1 \leq i \leq n\\)) of the members of the family.
   *                 Due to weird marital relations it is possible for people to be part of different families in which case they should not be split off from any of their families!
   * @return the costs of the connections that still need to be made.
   */
  public static int buildingWalls(int n, int k, int[][] r, Set<Set<Integer>> families) {
    List<Edge> graph = new ArrayList<>();
    for (int x = 1; x <= n; x++) {
      for (int y = x + 1; y <= n; y++) {
        graph.add(new Edge(x, y, r[x][y]));
      }
    }
    
    UnionFind nodes = new UnionFind(n+1);
    int clusters = n;
    
    for (Set<Integer> family: families) {
      Integer[] fam = family.toArray(new Integer[family.size()]);
      for (int i = 0; i < families.size(); i++) {
        for (int j = i + 1; j < family.size(); j++) {
          if (nodes.union(fam[i], fam[j])) clusters--;
        }
      }
    }
    
    Collections.sort(graph);
    int index = 0;
    while (clusters > k && index < graph.size()) {
      Edge e = graph.get(index);
      if (nodes.union(e.a, e.b)) {
        clusters--;
      }
      index++;
    }
    if (clusters > k) return 0;
    while (!(nodes.union(graph.get(index).a, graph.get(index).b))) index++;
    return graph.get(index).risk;
  }
}

//Class representing an edge from person to person and risk between them
class Edge implements Comparable<Edge> {
    int a, b, risk;

    public Edge(int from, int to, int risk) {
      a = from;
      b = to;
      this.risk = risk;
    }
    
    @Override
    public int compareTo(Edge e) {
      return Integer.compare(this.risk, e.risk);
    }
    
    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      if (other instanceof Edge) {
        Edge e = (Edge) other;
        return ((this.a == e.a && this.b == e.b) 
            || (this.a == e.b && this.b == e.a));
      }
      return false;
    }
  }

class UnionFind {
  private int[] set;
  private int[] rank;
  
  public UnionFind(int size) {
    set = new int[size];
    rank = new int[size];
    for (int i = 0; i < size; i++) set[i] = i;
  }
  
  public int find(int i) {
    if (set[i] != i) set[i] = find(set[i]);
    return set[i];
  }
  
  boolean union(int i, int j) {
    int x = find(i);
    int y = find(j);
    
    if (x == y) return false;
    if (rank[x] < rank[y]) {
      set[x] = y;
    } else {
      set[y] = x;
      if (rank[x] == rank[y]) rank[x]++;
    }
    return true;
  }
}
