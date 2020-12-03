package Greedy;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int name;
    public List<Node> outgoingEdges;

    public boolean marked;

    public Node(int i) {
        this.name = i;
        this.outgoingEdges = new ArrayList<>();
        this.marked = false;
    }
}
