package Greedy.Level0;

import Greedy.Node;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Solution1 {

    public String solve() throws FileNotFoundException {

        //Read and create the graph from the IN file
        File in = new File("C:\\Users\\Bean\\Desktop\\example.in");
        Scanner scanner = new Scanner(in);
        int currentLine = 0;
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        Map<Integer, Node> nodes = new HashMap<>();

        while (scanner.hasNextLine()) {
            String elements = scanner.nextLine();
            String[] splitted = elements.split(" ");
            System.out.println(elements);
            if(currentLine == 0) {
                start = Integer.parseInt(splitted[2]);
                end = Integer.parseInt(splitted[3]);
                for(int i = 1; i <=  Integer.parseInt(splitted[0]); i++) {
                    nodes.put(i, new Node(i));
                }
            } else {
                int currentVertex = Integer.parseInt(splitted[0]);
                int outgoingVertex = Integer.parseInt(splitted[1]);
                nodes.get(currentVertex).outgoingEdges.add(nodes.get(outgoingVertex));
                System.out.println("currentVertex: "+ currentVertex +" outgoingVertex: " +outgoingVertex);
            }
            ++currentLine;
        }

        //Implement solution traversal algorithm
        Set<Node> known = new HashSet<>();
        depthFirst(nodes,nodes.get(start),known);

        if(nodes.get(start).equals(nodes.get(end))) return "yes";
        if(known.contains(nodes.get(end))){
            return "yes";
        } else {
            return "no";
        }

        /*
        for(int i = 1; i <= nodes.size(); i++) {
            System.out.println("Node " + i + ": ");
            for(Node node: nodes.get(i).outgoingEdges) {
                System.out.println(node.name);
            }
        }
        */

    }

    public void depthFirst(Map<Integer,Node> graph,  Node u, Set<Node> known) {
        known.add(u);
        for (Node node: u.outgoingEdges) {
            if(!known.contains(node)) {
                depthFirst(graph, node, known);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(new Solution1().solve());
    }
}


public class maze {
    public static void main(String[] args) throws FileNotFoundException {
        //Solution.
        /*
        File in = new File("C:\\Users\\Bean\\Desktop\\example.in");
        Scanner scanner = new Scanner(in);
        Map<Integer,String> nodes = new HashMap<>();
        int currentLine = 0;
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        while (scanner.hasNextLine()) {
            String elements = scanner.nextLine();
            String[] splitted = elements.split(" ");
            System.out.println(elements);

            if(currentLine == 0) {
                start = Integer.parseInt(splitted[2]);
                end = Integer.parseInt(splitted[3]);
                for(int i = 1; i <=  Integer.parseInt(splitted[0]); i++) {
                    nodes.put(i, String.valueOf(i));
                }
            } else {
                int currentVertex = Integer.parseInt(splitted[0]);
                int outgoingVertex = Integer.parseInt(splitted[1]);
                System.out.println("currentVertex: "+ currentVertex +" outgoingVertex: " +outgoingVertex );
            }
            ++currentLine;
        }

        System.out.println("Start: "+ start +" End: " +end );
*/
    }
}
