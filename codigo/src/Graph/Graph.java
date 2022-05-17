package Graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import Utils.Utils;

import java.util.*;

class Graph {

    private Map<Node, List<Node>> graph = new HashMap<>();

    public void addEdge(Node source, Node destination, boolean biDirectional) {

        //TODO: Criar edge
        Edge edge = null;

        if (!graph.containsKey(source)) {
            source.addOutgoingEdge(edge);
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
        StringBuilder builder = new StringBuilder();

        for(Node vertex : graph.keySet()) {
            builder.append(vertex.toString() + ": ");
            for(Node node: graph.get(vertex)) {
                builder.append(node.toString() + " ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private void addNode(Node vertex) {
        graph.put(vertex, new LinkedList<Node>());
    }
}