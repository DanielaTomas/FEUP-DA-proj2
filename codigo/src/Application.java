

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import Graph.*;
import Utils.*;
import jdk.jshell.execution.Util;

public class Application {

    public static void main(String[] args) {

        secondScenery(5);
    }

    public static void firstScenery() {

        Graph graph = new Graph();
        ArrayList<Pair<ArrayList<Node>, Integer>> solutions = new ArrayList<>();
        ArrayList<Node> maxCpacityPath = new ArrayList<>();

        Utils.readFromFile(graph);

        int maxPeople = Utils.CaminhosCapacidadeMaxima(graph, maxCpacityPath);
        Pair<ArrayList<Node>, Integer> solution = Algorithms.BFS_N(graph);
        solutions.add(solution);
        while(true) {
            if (!(solution.getV1().size() < maxCpacityPath.size())) break;
            solution = Algorithms.BFS_N(graph);
            if(solution.getV1().size() <= 1) {
                break;
            }
            solutions.add(solution);
        }

        System.out.println(">>>Caminho que maximiza numero de amigos: " + maxCpacityPath + " => " + maxPeople);
        System.out.println(">Caminhos mais curtos: ");
        for(Pair<ArrayList<Node>, Integer> _solution : solutions) {
            System.out.println("\t->" + _solution.getV1() + " => " + _solution.getV2());
        }
    }

    public static void secondScenery(int numPersonsGroup) {

        Graph graph = new Graph();
        Graph rGraph = new Graph();

        Utils.readFromFile(graph);
        graph.createResidualGraph(rGraph);

        System.out.println(Algorithms.Edmonds_Karp(rGraph));

    }
}
