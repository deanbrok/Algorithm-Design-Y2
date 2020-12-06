package Exam.ResitMidterm2019.Dean;

import java.util.*;

public class Kclustering {
    /**
     * You should implement this method.
     * @param n the number of inhabitants in the city.
     * @param k the number of sections the city needs to split into.
     * @param r 2D-array with all the risks between two people, r[i][j] represents the risk of the
     *          virus spreading from inhabitant (1 <= i <= n) to inhabitant (1 <= j <= n).
     *          You should ignore r[0][j] and r[i][0].
     * @param families a set of sets representing families. Every set in families contains the integer
     *                 ids i (1 <= i <= n) of the members of the family.Due to weird marital
     *                 relations it is possible for people to be part of different families in which case they
     *                 should not be split off from any of their families!
     * @return the costs of the connections that still need to be made.
     */
    public static int buildingWalls(int n, int k, int[][] r, Set<Set<Integer>> families) {
        // TODO
        List<Inhabitant> inhabitants = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            inhabitants.add(new Inhabitant(i));
        }

        List<Risk> risks = new ArrayList<>();

        for(int i = 1; i <= n; i++)
        {
            for(int j = i + 1; j <= n; j++)
            {
                risks.add(new Risk(inhabitants.get(i - 1),inhabitants.get(j -1),r[i][j]));
            }
        }

        System.out.println("Before Sort:");
        for(Risk risk: risks)
        {
            System.out.println(risk);
        }

        UnionFind unionFind = new UnionFind(inhabitants);
        // TODO

        //1. Sort the distance
        Collections.sort(risks,Comparator.comparingLong(x -> x.risk));

        System.out.println();
        System.out.println("Sorted:");

        for(Risk risk: risks)
        {
            System.out.println(risk);
        }

        int edgeCount = 0;

        for(Risk risk: risks)
        {
            if(edgeCount == n - k)
            {
                break;
            }
            if(unionFind.join(risk.a, risk.b))
            {
                edgeCount += 1;
            }
        }

        for(List<Integer> inhabitantList: unionFind.clusters())
        {
            for(Set<Integer> family: families)
            {
                Set<Integer> currentFamily = new HashSet<>();
                for(Integer familyMember: family)
                {
                    if(inhabitantList.contains(familyMember))
                    {
                        currentFamily.add(familyMember);
                    }
                }
                if(currentFamily.size() > 0)
                {
                    if(currentFamily.equals(family));
                }
            }
        }

        System.out.println();
        System.out.println(unionFind.clusters());
        return risks.get(edgeCount).risk;
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 2;
        int[][] c = { { 0, 0, 0, 0, 0 }, { 0, 0, 2, 3, 4 }, { 0, 2, 0, 1, 100 }, { 0, 3, 1, 0, 1 }, { 0, 4, 100, 1, 0 } };
        Set<Set<Integer>> families = new HashSet<>();
        Set<Integer> family = new HashSet<>();
        family.add(2);
        family.add(3);
        families.add(family);
        System.out.println(buildingWalls(n, k, c, families));
    }
}

class Inhabitant {

    int id;

    public Inhabitant(int id) {
        this.id = id;

    }

    @Override
    public String toString() {
        return "Inhabitant{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inhabitant)) return false;
        Inhabitant that = (Inhabitant) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

class Risk {

    Inhabitant a, b;

    int risk;

    public Risk(Inhabitant a, Inhabitant b, int risk) {
        this.a = a;
        this.b = b;
        this.risk = risk;
    }

    @Override
    public String toString() {
        return "Risk{" +
                 a +
                ", " + b +
                ", risk=" + risk +
                '}';
    }
}

class UnionFind {

    private List<Inhabitant> inhabitants;

    private int[] parent;

    private int[] rank;

    UnionFind(List<Inhabitant> inhabitants) {
        this.inhabitants = inhabitants;
        int n = inhabitants.size();
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    /**
     * Joins two disjoint sets together, if they are not already joined.
     * @return false if x and y are in same set, true if the sets of x and y are now joined.
     */
    boolean join(Inhabitant x, Inhabitant y) {
        int xrt = find(x.id - 1);
        int yrt = find(y.id - 1);
        if (rank[xrt] > rank[yrt])
            parent[yrt] = xrt;
        else if (rank[xrt] < rank[yrt])
            parent[xrt] = yrt;
        else if (xrt != yrt)
            rank[parent[yrt] = xrt]++;
        return xrt != yrt;
    }

    /**
     * @return The house that is indicated as the "root" of the set of house h.
     */
    Inhabitant find(Inhabitant h) {
        return inhabitants.get(find(h.id));
    }

    private int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    /**
     * @return All clusters of houses
     */
    Collection<List<Integer>> clusters() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            int root = find(i);
            if (!map.containsKey(root))
                map.put(root, new ArrayList<>());
            map.get(root).add(inhabitants.get(i).id);
        }
        return map.values();
    }
}
