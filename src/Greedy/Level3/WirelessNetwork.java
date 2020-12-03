package Greedy.Level3;

/*
    Problem:
    To set up internet connections in a large area in Cittagazze,
    stations are planned at locations with a high population density.
    These n stations are connected through pairs of quite expensive directional antennas.
    The price of (a pair of) antennas depends on the required range,
    and for each pair of locations (u,v) these costs are given by c(u,v)>0.

    The main question of this exercise is which (undirected) connections to set up
    such that all locations are connected and the installation costs are as low as possible.

    Unfortunately, there is insufficient budget
    for setting up the connected network computed above at once.
    The government decides to make as many connections of the optimal network as possible,
    given the available budget B
    (and to complete the network in upcoming years).
    For example, if the network looks like this,
    a budget of 3 would select (a,b) and (d,e) and (f,g).

         1       2       3       1       2       1
    (a)-----(b)-----(c)-----(d)-----(e)-----(f)-----(g)

    Your task in this exercise is to read the cost of all possible connections
    from the input Scanner in the following format.

    First read the number of locations n
    (2≤n≤10000),
    the number of connections m (1≤m≤100000),
    and the budget B (1≤B≤10^9),
    followed by a new line character.

    Then read m
    lines with three values:
    the identifier of the first location u (0≤u<n),
    the identifier of the second location v (0≤v<n),
    and the costs of connecting these through a pair of antennas
    c(u,v) (1≤c(u,v)≤100000),
    followed by a new line.

    For any pair of locations that is not listed,
    the costs of connecting can be assumed to be infinite.
    It is guaranteed that the network can be set up using finite costs.

    Your output should be a String that has two numbers,
    separated by a space.
    The first number is the minimum total costs to connect all locations.
    The second number is the number of connections that can be built using the budget.

    Example input:

    4 5 8
    0 1 6
    1 2 9
    0 2 7
    1 3 2
    0 3 8

    Example output:

    15 2

 */

import Greedy.Tuple;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class WirelessNetwork {

    public String solve(){
        InputStream input = getClass().getResourceAsStream("budget_example.in");
        Scanner sc = new Scanner(input);

        //Parse the input
        int n = sc.nextInt();
        int m = sc.nextInt();
        int budget = sc.nextInt();

        ArrayList<HashMap<Integer, Integer>> nodes = new ArrayList<>();

        for (int i = 0; i < n; i++)
        {
            nodes.add(new HashMap<>());
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int cost = sc.nextInt();
            //this is an undirected graph but they don't specify it
            //explicitly in the .in file so we have to add them
            //manually
            nodes.get(u).put(v, cost);
            nodes.get(v).put(u,cost);
        }

        //1. Create variables to keep track of explored nodes and the current costs of adding a node to the MST.
        //A priority queue is also used for easy access to the next node with the lowest cost
        boolean[] exploredNodes = new boolean[n];
        int[] costs = new int[n];
        PriorityQueue<NetworkTuple> pq = new PriorityQueue<>();


        //Initialize the costs and priority queue
        for (int i = 0; i < nodes.size(); i++)
        {
            costs[i] = Integer.MAX_VALUE/2;
        }
        pq.add(new NetworkTuple(0,0));
        costs[0] = 0;

        //3. While there are still unexplored nodes, we choose the one which gives the lowest cost using the priority queue
        //and add that to the cluster of known node. Then we update all of the unknown nodes that it connects to.
        while(!pq.isEmpty())
        {
            //Dequeue and get the tuple with the lowest cost and the nodes that it connect to
            NetworkTuple currentTuple = pq.poll();
            HashMap<Integer,Integer> outGoingNodes = nodes.get(currentTuple.id);

            //Loop through each of the outgoing nodes that the current node connects to and check if the node is
            //explored or not. If it isn't explored, then we calculate the current cost to the node which is simply
            //the cost to it from our current node and compare it with the cost that we previously found.
            //If this new cost is smaller than the old cost then we set it as the actual cost, and update the priority
            //queue accordingly.
            for (Map.Entry<Integer, Integer> outGoingNode : outGoingNodes.entrySet())
            {
                if(!exploredNodes[outGoingNode.getKey()])
                {
                    int currentCost = outGoingNode.getValue();
                    int oldCost = costs[outGoingNode.getKey()];
                    if(currentCost < oldCost)
                    {
                        costs[outGoingNode.getKey()] = currentCost;
                        //Update the entry by removing and re-adding the tuple since
                        //we don't have an updateEntry method
                        pq.remove(new NetworkTuple(outGoingNode.getKey(),oldCost));
                        pq.add(new NetworkTuple(outGoingNode.getKey(),currentCost));
                    }
                }
            }
            exploredNodes[currentTuple.id] = true;
        }

        //Sort the cost since we want the optimal
        //network with the lowest cost
        Arrays.sort(costs);

        int totalCost = 0;
        int currentCost = 0;
        int countForBudget = -1;

        //Calculate the totalCost of the MST and the count of the number of connections
        //that fit in the budget, this can be done in a single loop as the costs
        //are sorted.
        for(int i = 0; i < costs.length ; i++)
        {
            if(currentCost + costs[i] <= budget) {
                currentCost += costs[i];
                countForBudget += 1;
            }
            totalCost += costs[i];
        }

        return totalCost + " "  + countForBudget;
    }

    public static void main(String[] args) {
        System.out.println(new WirelessNetwork().solve());
    }
}

class NetworkTuple implements Comparable<NetworkTuple>{
    public int id;
    public int cost;

    public NetworkTuple(int id, int cost) {
        this.id = id;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NetworkTuple)) return false;
        NetworkTuple that = (NetworkTuple) o;
        return id == that.id &&
                cost == that.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(NetworkTuple o) {
        int res = Integer.signum(this.cost - o.cost);
        if (res == 0) {
            return Integer.signum(this.id - o.id);
        }
        return res;
    }
}