package Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import Utils.Utils;

import java.util.*;

public class Graph {

    private final Map<Node, List<Node>> graph = new HashMap<>();
    private final ArrayList<Node> nodes = new ArrayList<>();

    public Graph() {


        this.nodes.addAll(this.graph.keySet());
    }

    public void addEdge(Node source, Node destination, int capacity, int duration, boolean biDirectional) {

        Edge edge = new Edge(source, destination, capacity, duration);
        source.addOutgoingEdge(edge);

        if (!graph.containsKey(source)) {
            addNode(source);
        }

        if (!graph.containsKey(destination)) {
            addNode(destination);
        }

        graph.get(source).add(destination);
        if(biDirectional) {
            graph.get(destination).add(source);
        }
    }

    public void hasVertex(Node vertex) {
        if(graph.containsKey(vertex)) {
            System.out.println("The Graph contains " + vertex + " as a vertex");
        }else {
            System.out.println("The Graph does not contain " + vertex + " as a vertex");
        }
    }

    public void hasEdge(Node source, Node destination) {
        if(graph.get(source).contains(destination)) {
            System.out.println("The Graph has an edge between " + source + " and " + destination);
        }else {
            System.out.println("The Graph has no edge between " + source + " and " + destination);
        }
    }

    public String printGraph() {

        System.out.println(this.graph.size());

        StringBuilder builder = new StringBuilder();

        for(Node node : graph.keySet()) {
            builder.append(node.getValue() + ": ");
            for(Node _node: graph.get(node)) {
                builder.append(_node.getValue() + " ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private void addNode(Node vertex) {
        graph.put(vertex, new LinkedList<>());
    }

    public void printEdges() {

        for(Node node : this.graph.keySet()) {
            System.out.println(">>" + node.getValue());
            for(int i=0; i<node.getOutgoingEdges().size(); i++) {
                System.out.println(node.getOutgoingEdges().get(i).getDest().getValue());
            }
            System.out.println("=============");
        }
    }

    public Node searchNode(int val) {

        for(Node node: this.graph.keySet()) {
            if(node.getValue() == val) {
                return node;
            }
        }
        return null;
    }

    public Map<Node, List<Node>> getGraph() {
        return graph;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void addNodeToArray(Node node) {
        this.nodes.add(node);
    }

    public Edge getEdge(Node source, Node dest) {

        for(Edge edge : source.getOutgoingEdges()) {
            if(edge.getDest().equals(dest)) {
                return edge;
            }
        }
        return null;
    }

    public void createResidualGraph(Graph residualGraph) {

        for(Node source: this.getGraph().keySet()) {
            for(Node dest : this.getGraph().get(source)) {
                Edge edge = this.getEdge(source, dest);
                if(edge != null) {

                    Node rSource;
                    Node rDest;

                    if((rDest = residualGraph.searchNode(source.getValue())) == null) {
                        rDest = new Node(source.getValue());
                    }
                    if((rSource = residualGraph.searchNode(dest.getValue())) == null) {
                        rSource = new Node(dest.getValue());
                    }

                    residualGraph.addEdge(rSource, rDest, edge.getCapacity(), edge.getDuration(), false);
                    Edge resisualEdge = residualGraph.getEdge(rSource, rDest);
                    resisualEdge.setFlow(resisualEdge.getCapacity());
                }
            }
        }

        for(Node node : this.getGraph().keySet()) {
            residualGraph.addNodeToArray(node);
        }
    }
}