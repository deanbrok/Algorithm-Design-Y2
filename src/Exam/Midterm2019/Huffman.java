package Exam.Midterm2019;

import java.util.PriorityQueue;

/**
 * WARNING: The spec tests are not necessarily equal to your grade!
 * You can use them help you test for the correctness of your algorithm,
 * but the final grade is determined by a manual inspection of your implementation.
 */
public class Huffman {

    /**
     *  You should implement this method.
     *  @param node A Node in the Huffman encoding tree
     *  @return the encoded string representing the character in this node.
     */
    public static String encode(Node node) {
        // Implement this method for question 2a
        if(node.frequency == 1) return "";
        Node parent = node.getParent();
        if(parent.getLeftChild().equals(node))
        {
            return encode(parent)+"0";
        } else {
            return encode(parent)+"1";
        }
    }

    /**
     *  You should implement this method.
     *  @param n the number of characters that need to be encoded.
     *  @param characters The characters c_1 through c_n. Note you should use only characters[1] up to and including characters[n]!
     *  @param frequencies The frequencies f_1 through f_n. Note you should use only frequencies[1] up to and including frequencies[n]!
     *  @return The root node of an optimal Huffman tree that represents the encoding of the characters given.
     */
    public static Node buildHuffman(int n, char[] characters, double[] frequencies) {
        // Implement this method for question 2b
        PriorityQueue<Node> nodes = new PriorityQueue<>();
        for(int i = 1; i <= n ; i++) nodes.add(new Node(characters[i],frequencies[i]));

        helpBuildHuffMan(nodes);
        Node root = nodes.poll();
        return root;
    }

    public static Node helpBuildHuffMan(PriorityQueue<Node> nodes) {

        if(nodes.size() == 2)
        {
            Node root = new Node((char) 0, 1);
            Node leftChild = nodes.poll();
            Node rightChild = nodes.poll();

            root.setLeftChild(leftChild);
            root.setRightChild(rightChild);
            leftChild.setParent(root);
            rightChild.setParent(root);
            nodes.add(root);

            return root;
        }

        Node leftChild = nodes.poll();
        Node rightChild = nodes.poll();

        Node combinedNode = new Node((char) 0,leftChild.getFrequency() + rightChild.getFrequency());
        nodes.add(combinedNode);
        Node T = helpBuildHuffMan(nodes);

        leftChild.setParent(combinedNode);
        rightChild.setParent(combinedNode);
        combinedNode.setLeftChild(leftChild);
        combinedNode.setRightChild(rightChild);

        return T;
    }

    public static void main(String[] args) {
        int n = 5;
        char[] chars = { 0, 'a', 'b','c', 'd', 'e' };
        double[] freq = { 0, 0.32, 0.25, 0.2, 0.18, 0.05 };
        Node tree = Huffman.buildHuffman(n, chars, freq);
        System.out.println();
        System.out.println(tree);
        System.out.println(tree.getLeftChild());
        System.out.println(tree.getRightChild());

    }
}

/**
 * NOTE: You should ensure that if a Node is a part of a tree, then all nodes in the tree have their `parent`, `leftChild`, and `rightChild` set appropriately!
 * You may add methods to this class, provided you do not change the names or types of existing methods or fields!
 */
class Node implements Comparable<Node>{

    char symbol;

    double frequency;

    Node parent;

    Node leftChild;

    Node rightChild;

    public Node(char symbol, double frequency) {
        this.symbol = symbol;
        this.frequency = frequency;
    }

    public Node(char symbol, double frequency, Node parent) {
        this(symbol, frequency);
        this.parent = parent;
    }

    public Node(char symbol, double frequency, Node leftChild, Node rightChild) {
        this(symbol, frequency);
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public char getSymbol() {
        return symbol;
    }

    public double getFrequency() {
        return frequency;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public String toString() {
        return "Node{" +
                "symbol=" + symbol +
                ", frequency=" + frequency +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        if(this.frequency - o.frequency < 0) return -1;
        else return 1;
    }
}