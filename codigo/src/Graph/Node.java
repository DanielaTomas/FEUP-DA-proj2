package Graph;

import java.util.ArrayList;
import java.util.Objects;

public class Node implements Comparable<Node> {
    private int value;
    private int capacity;
    private ArrayList<Edge> outgoingEdges = new ArrayList<>();
    private Node fatherNode;

    public Node(int value) {
        this.value = value;
        this.capacity = 0;
        this.fatherNode = null;
    }

    public void addOutgoingEdge(Edge edge)  {
        this.outgoingEdges.add(edge);
    }

    public int getValue() {
        return value;
    }

    public ArrayList<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setFatherNode(Node fatherNode) {
        this.fatherNode = fatherNode;
    }

    public int getCapacity() {
        return capacity;
    }

    public Node getFatherNode() {
        return fatherNode;
    }

    @Override
    public String toString() {
        return "Node(" + value + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value == node.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


    @Override
    public int compareTo(Node node) {
        if(this.getCapacity() < node.getCapacity()) {
            return 1;
        } else if (this.getCapacity() > node.getCapacity()) {
            return -1;
        } else {
            return 0;
        }
    }
}
