

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import Graph.*;
import Utils.*;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        //firstScenery();
        Graph graph = new Graph();
        Utils.readFromFile(graph);
        System.out.println(Algorithms.criticalPathMethod(graph));
        //secondScenery();
    }

    public static void firstScenery() {

        long startTime = System.nanoTime();
        
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

        long endTime = System.nanoTime();
        String st = String.format("Time: %3.3f seconds\n", (double) (endTime - startTime) / 1000000000);
        System.out.print(st);

        System.out.println(">>>Caminho que maximiza numero de amigos: " + maxCpacityPath + " => " + maxPeople);
        System.out.println(">Caminhos mais curtos: ");
        for(Pair<ArrayList<Node>, Integer> _solution : solutions) {
            System.out.println("\t->" + _solution.getV1() + " => " + _solution.getV2());
        }
    }

    public static void secondScenery() {

        Scanner reader = new Scanner(System.in);
        int userInput = reader.nextInt();

        switch (userInput) {
            case 1:
                System.out.println("Encaminhamento para um grupo dada a sua dimensão");
                System.out.println("=================================");
                calculateGroupRoute(3);
                break;
            case 2:
                System.out.println("Dimensão máxima do grupo possível");
                System.out.println("=================================");
                maxGroupDimention();
                break;
            default:
                break;
        }
    }

    public static void calculateGroupRoute(int groupDimention) {

        Graph graph = new Graph();
        Graph rGraph = new Graph();

        Node specialNode = new Node(0);
        graph.getNodes().add(specialNode);
        Utils.readFromFile(graph);

        graph.addEdge(specialNode, graph.getNodes().get(1), groupDimention, 0,false);
        graph.createResidualGraph(rGraph);
        Collections.sort(rGraph.getNodes(), Comparator.comparingInt((Node n) -> n.getValue()));

        System.out.println(Algorithms.Edmonds_Karp(rGraph));
    }

    public static void maxGroupDimention() {

        Graph graph = new Graph();
        Graph rGraph = new Graph();

        Utils.readFromFile(graph);
        graph.createResidualGraph(rGraph);

        int flow = Algorithms.Edmonds_Karp(rGraph);
        ArrayList<ArrayList<Node>> paths = new ArrayList<>();

        ArrayList<Node> path = Algorithms.getPathForSecondScenery(rGraph);
        paths.add(Algorithms.getPathForSecondScenery(rGraph));

        while(path != null) {
            path = Algorithms.getPathForSecondScenery(rGraph);
            System.out.println(path);
            paths.add(Algorithms.getPathForSecondScenery(rGraph));
        }

        System.out.println("Max number of people in the group: " + flow);
    }
}
