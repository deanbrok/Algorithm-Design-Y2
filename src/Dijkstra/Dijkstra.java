package Dijkstra;

import Greedy.Tuple;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Dijkstra {


    public String solve() throws FileNotFoundException {

        InputStream input = getClass().getResourceAsStream("dataset7.in");
        Scanner sc = new Scanner(input);
        /*
         * We already parse the input for you and should not need to make changes to this part of the code.
         * You are free to change this input format however.
         */
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        int t = sc.nextInt();
        ArrayList<HashMap<Integer, Integer>> nodes = new ArrayList<>();
        for (int i = 0; i <= n; i++)
        {
            nodes.add(new HashMap<>());
        }
        for (int i = 0; i < m; i++)
        {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int cost = sc.nextInt();
            nodes.get(u).put(v, cost);
        }


        // TODO
        boolean[] known = new boolean[nodes.size()];
        int[] distances = new int[nodes.size()];
        int[] previousVertices = new int[nodes.size()];

        for (int i = 0; i < nodes.size(); i++)
        {
            distances[i] = Integer.MAX_VALUE/2;
            previousVertices[i] =  Integer.MAX_VALUE/2;
        }

        PriorityQueue<Tuple> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Tuple(s,0));
        distances[s] = 0;

        while (!priorityQueue.isEmpty())
        {
            Tuple currentTuple = priorityQueue.remove();
            if(currentTuple.id == t) return String.valueOf(distances[t]);
            HashMap<Integer,Integer> outGoingEdges = nodes.get(currentTuple.id);
            for (Map.Entry<Integer, Integer> entry : outGoingEdges.entrySet())
            {
                if(!known[entry.getKey()])
                {
                    int currentDistance = distances[currentTuple.id] + entry.getValue() + outGoingEdges.size();
                    int oldDistance = distances[entry.getKey()];
                    if(currentDistance < oldDistance)
                    {
                        distances[entry.getKey()] = currentDistance;
                        previousVertices[entry.getKey()] = currentTuple.id;
                        priorityQueue.remove(new Tuple(entry.getKey(),oldDistance));
                        priorityQueue.add(new Tuple(entry.getKey(),currentDistance));
                    }
                }
            }
            known[currentTuple.id] = true;
        }
        int node = 0;
        System.out.println();
        System.out.println("Distances");
        for(int i: distances) {
            System.out.print(node++ + ": ");
            System.out.println(i);
        }
        if(!known[t]) return String.valueOf(-1);
        return String.valueOf(distances[t]);
    }



    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(new Dijkstra().solve());
    }


}
