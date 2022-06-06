import java.util.*;

import Graph.*;
import Utils.*;

public class Application {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);
        int userInput = reader.nextInt();

        switch (userInput) {
            case 1:
                System.out.println("Grupo não se pode separar.");
                System.out.println("==========================");
                firstScenery();
                break;
            case 2:
                System.out.println("Grupo pode-se separar.");
                System.out.println("======================");
                secondScenery();
                break;
            default:
                break;
        }
    }

    public static void firstScenery() {

        Graph graph = new Graph();
        ArrayList<Pair<ArrayList<Node>, Integer>> solutions = new ArrayList<>();
        ArrayList<Node> maxCapacityPath = new ArrayList<>();

        Utils.readFromFile(graph);

        int maxPeople = Algorithms.CaminhosCapacidadeMaxima(graph, maxCapacityPath);
        Pair<ArrayList<Node>, Integer> solution = Algorithms.BFS_N(graph);
        solutions.add(solution);
        while(true) {
            if (!(solution.getV1().size() < maxCapacityPath.size())) break;
            solution = Algorithms.BFS_N(graph);
            if(solution.getV1().size() <= 1) {
                break;
            }
            solutions.add(solution);
        }

        System.out.println(">>>Caminho que maximiza numero de amigos: " + maxCapacityPath + " => " + maxPeople);
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
                calculateGroupRoute(4);
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

        System.out.println(Algorithms.Edmonds_Karp(rGraph));
    }
}
