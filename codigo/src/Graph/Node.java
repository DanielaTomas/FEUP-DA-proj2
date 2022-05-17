package Graph;

import java.util.ArrayList;
import java.util.Objects;

public class Node {
    private int value;
    private ArrayList<Edge> outgoingEdges = new ArrayList<>();

    public Node(int value) {
        this.value = value;
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
}
