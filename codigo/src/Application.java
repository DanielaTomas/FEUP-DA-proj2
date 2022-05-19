

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import Graph.*;

import Utils.Utils;

public class Application {

    public static void main(String[] args) {

        firstScenery();
    }

    public static void firstScenery() {

        Graph graph = new Graph();
        ArrayList<Node> path = new ArrayList<>();

        Utils.CaminhosCapacidadeMaxima(graph, path);

        int maxPeople = 0;
        for(Node node : path) {
            for(Edge edge: node.getOutgoingEdges()) {
                if(edge.getDest().getFatherNode().equals(node)
                && node.getCapacity() > maxPeople) {
                    maxPeople = edge.getCapacity();
                }
            }
        }

        System.out.println("Máximo número de pessoas que podem fazer a viagem: " + maxPeople);
        System.out.println("Caminho que amigos fazem para chegar ao destino: " + path);

    }
}
