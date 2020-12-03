package Greedy.Level0;

import Greedy.Node;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;


class TrainRouting {
    public String solve() throws FileNotFoundException {

        //Extract information out of the document
        InputStream input = getClass().getResourceAsStream("example.in");
        Scanner scanner = new Scanner(input);
        int currentLine = 0;
        int central = Integer.MAX_VALUE;

        Map<Integer, Node> nodes = new HashMap<>();

        while (scanner.hasNextLine()) {
            String elements = scanner.nextLine();
            String[] splitted = elements.split(" ");

            if (currentLine == 0) {
                central = Integer.parseInt(splitted[2]);
                for (int i = 1; i <= Integer.parseInt(splitted[0]); i++) {
                    nodes.put(i, new Node(i));
                }
            } else {
                int currentVertex = Integer.parseInt(splitted[0]);
                int outgoingVertex = Integer.parseInt(splitted[1]);
                nodes.get(currentVertex).outgoingEdges.add(nodes.get(outgoingVertex));
            }
            ++currentLine;
        }
        scanner.close();

        //Implement traversal
        Set<Node> known = new HashSet<>();

        if (findCycle(nodes.get(central))) {
            return "yes";
        } else {
            return "no";
        }
    }

    public boolean findCycle(Node s) {

        Stack<Node> stack = new Stack<>();
        s.marked = true;
        stack.push(s);


        while (!stack.isEmpty()) {
            //System.out.println(stack);

            Node top = stack.peek();

            for (Node node : top.outgoingEdges) {

                if (node.marked == false) {
                    stack.push(node);
                    node.marked = true;
                    break;

                } else if (stack.contains(node)) {
                    return true;
                }

            }

            if (top.equals(stack.peek())) {
                stack.pop();
            }
        }
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(new TrainRouting().solve());
    }
}
