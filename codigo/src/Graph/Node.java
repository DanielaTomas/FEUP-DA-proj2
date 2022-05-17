package Graph;

import java.util.ArrayList;

public class Node {
    private int value;
    private ArrayList<Edge> outgoingEdges = new ArrayList<>();

    public Node(int value) {
        this.value = value;
    }

    public void addOutgoingEdge(Edge edge)  {
        this.outgoingEdges.add(edge);
    }

}
