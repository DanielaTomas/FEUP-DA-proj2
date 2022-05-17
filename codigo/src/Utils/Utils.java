package Utils;

import Graph.*;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Utils {

    public static String[] parseLine(String line) {
        return line.split(" ");
    }

    //TODO: Ha uns problemas aqui.
    public static void CaminhosCapacidadeMaxima(Graph graph, ArrayList<Node> path) {

        PriorityQueue<Node> maxQueue = new PriorityQueue<>();

        maxQueue.add(graph.getNodes().get(0));
        graph.getNodes().get(0).setCapacity(Integer.MAX_VALUE);

        while(!maxQueue.isEmpty()) {
            Node currentNode = maxQueue.poll();
            for(Edge edge : currentNode.getOutgoingEdges()) {
                if(Math.min(currentNode.getCapacity(), edge.getCapacity()) > edge.getDest().getCapacity()) {
                    edge.getDest().setCapacity(Math.min(currentNode.getCapacity(), edge.getCapacity()));
                    edge.addFatherNodeToDestNode(currentNode);
                    maxQueue.add(edge.getDest());
                }
            }
        }
    }
}
