package Greedy.Level3;

import java.io.InputStream;
import java.util.*;

/*
   Problem:
      You are the owner of a new store chain (called Amazing Donuts, or AD for short),
    and business is running well, so you decide to open up some more stores.
    You decide to do this in the town of Scatterville,
    where the houses of all residents are literally scattered around the village.

    Of course, you want to reach as many people with your donuts as possible.
    Since you want to place k
    new stores in Scatterville,
    you decide to divide the houses in k

    “clusters”,
    and each cluster will get its own store.
    Every house will belong to exactly one store cluster.

    Given the coordinates of the houses in Scatterville, and how many stores you will open,
    can you calculate where the new stores should be placed?


    We have already provided several classes that might be useful for you in the Library tab.
    You cannot edit these classes, but you can use them in your implementation.
    Input and output

    The first line of the input stream will contain two numbers:
    n
    (with 1≤n≤1000), which is the number of houses;
    and k (with 1≤k≤n), which is the number of stores you will open.
    Following this are n lines, each with two integers,
    which are the x and y

    coordinates of each house.

    You should return a string that contains k
    lines,
    each line containing two numbers,
    which are the x and y coordinates of each new store.
    The order of the stores does not matter,
    and per number you can have a maximum relative error of 10−6

    .

    The following example represents the image above:

    10 3
    4 5
    5 10
    7 7
    8 20
    11 6
    12 22
    22 15
    23 9
    25 12
    26 17

    The resulting output would be in this case:

    6.75 7
    10 21
    24 13.25

 */

public class DonutEmporium
{
    public String solve()
    {
        InputStream in = getClass().getResourceAsStream("donut_1.in");
        Scanner sc = new Scanner(in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        List<House> houses = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            houses.add(new House(i, sc.nextInt(), sc.nextInt()));
        }
        int m = n * (n - 1) / 2;
        List<Distance> distances = new ArrayList<>(m);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                distances.add(new Distance(houses.get(i), houses.get(j)));
            }
        }
        UnionFind unionFind = new UnionFind(houses);
        // TODO

        //1. Sort the distance
        Collections.sort(distances,Comparator.comparingLong(x -> x.distance));
        System.out.println(houses);
        System.out.println(distances);

        //2. Keep track of the number of edges added to the MST
        int edgeCount = 0;

        //3. Loop through the distances in increasing order as we have sorted
        //and each time join them. If this is possible as indicated by the join
        //method of UnionFind then increment the number of edges in the spanning tree.
        //The number of edges cannot exceed n - k so we break out of the loop there.
        for(Distance distance: distances)
        {
            if(edgeCount == n - k)
            {
                break;
            }
            if(unionFind.join(distance.a, distance.b))
            {
                edgeCount += 1;
            }

        }
        System.out.println(unionFind.clusters());

        String result = "";
        //4. Find the centre of each cluster and add it to the resulting string
        for(List<House> cluster: unionFind.clusters())
        {
            double xDonutCoordinate = 0;
            double yDonutCoordinate = 0;
            for (House house: cluster)
            {
                xDonutCoordinate += house.x;
                yDonutCoordinate += house.y;
            }
            xDonutCoordinate /= cluster.size();
            yDonutCoordinate /= cluster.size();

            result += xDonutCoordinate + " " + yDonutCoordinate + "\n";
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new DonutEmporium().solve());
    }
}


/**
 * Given Weblab library
 */

class House {

    int id, x, y;

    public House(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

class Distance {

    House a, b;

    long distance;

    public Distance(House a, House b) {
        this.a = a;
        this.b = b;
        // Square Euclidean distance, to avoid floating-point errors
        this.distance = (long) (a.x - b.x) * (a.x - b.x) + (long) (a.y - b.y) * (a.y - b.y);
    }

    @Override
    public String toString() {
        return "Distance{" +
                "a=" + a +
                ", b=" + b +
                ", distance=" + distance +
                '}';
    }
}

class UnionFind {

    private List<House> houses;

    private int[] parent;

    private int[] rank;

    UnionFind(List<House> houses) {
        this.houses = houses;
        int n = houses.size();
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    /**
     * Joins two disjoint sets together, if they are not already joined.
     * @return false if x and y are in same set, true if the sets of x and y are now joined.
     */
    boolean join(House x, House y) {
        int xrt = find(x.id);
        int yrt = find(y.id);
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
    House find(House h) {
        return houses.get(find(h.id));
    }

    private int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    /**
     * @return All clusters of houses
     */
    Collection<List<House>> clusters() {
        Map<Integer, List<House>> map = new HashMap<>();
        for (int i = 0; i < parent.length; i++) {
            int root = find(i);
            if (!map.containsKey(root))
                map.put(root, new ArrayList<>());
            map.get(root).add(houses.get(i));
        }
        return map.values();
    }
}
