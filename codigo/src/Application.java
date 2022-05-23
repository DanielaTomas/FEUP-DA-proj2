

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import Graph.*;

import Utils.Utils;

public class Application {

    public static void main(String[] args) {

        secondScenery(5);
    }

    public static void firstScenery() {

        Graph graph = new Graph();
        ArrayList<Node> path = new ArrayList<>();

        Utils.readFromFile(graph);

        int maxPeople = Utils.CaminhosCapacidadeMaxima(graph, path);

        System.out.println("Máximo número de pessoas que podem fazer a viagem: " + maxPeople);
        System.out.println("Caminho que amigos fazem para chegar ao destino: " + path);
    }

    public static void secondScenery(int numPersonsGroup) {

        Graph graph = new Graph();
        Graph rGraph = new Graph();
        ArrayList<Node> path = new ArrayList<>();

        Utils.readFromFile(graph);

        System.out.println(Utils.BFS(graph));

        graph.createResidualGraph(rGraph);
    }
}
