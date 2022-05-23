package Utils;

import Graph.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Utils {

    public static String[] parseLine(String line) {
        return line.split(" ");
    }

    public static void readFromFile(Graph graph) {

        try {
            File myObj = new File("../dataset/Tests/test.txt");
            Scanner myReader = new Scanner(myObj);
            String line = myReader.nextLine();

            String[] values = Utils.parseLine(line);
            int numNodes = Integer.parseInt(values[1]);

            for(int i=0; i<numNodes; i++) {

                line = myReader.nextLine();
                values = Utils.parseLine(line);

                Node source;
                Node destination;

                if((source = graph.searchNode(Integer.parseInt(values[0]))) == null) {
                    source = new Node(Integer.parseInt(values[0]));
                }
                if((destination = graph.searchNode(Integer.parseInt(values[1]))) == null) {
                    destination = new Node(Integer.parseInt(values[1]));
                }

                graph.addEdge(source, destination, Integer.parseInt(values[2]), Integer.parseInt(values[3]), false);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        for(Node node : graph.getGraph().keySet()) {
            graph.addNodeToArray(node);
        }
    }

    //TODO: Ha uns problemas aqui.
    public static int CaminhosCapacidadeMaxima(Graph graph, ArrayList<Node> path) {

        PriorityQueue<Node> maxQueue = new PriorityQueue<>();

        for(Node node : graph.getNodes()) {
            node.setCapacity(0);
        }

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

        ArrayList<Node> nodesAux = graph.getNodes();
        Collections.reverse(nodesAux);

        Node currentNode = nodesAux.get(0);
        Node lastNode;
        path.add(currentNode);

        int maxPeople = Integer.MAX_VALUE;
        while(currentNode.getFatherNode() != null) {
            lastNode = currentNode;
            currentNode = currentNode.getFatherNode();
            path.add(currentNode);
            for(Edge edge : currentNode.getOutgoingEdges()) {
                if(edge.getDest().equals(lastNode)
                        && maxPeople > edge.getCapacity()) {
                    maxPeople = edge.getCapacity();
                }
            }
        }

        Collections.reverse(path);
        return maxPeople;
    }

    public static int Edmonds_Karp(Graph graph) {

        Graph rGraph = new Graph();
        int maxFlow = 0;



        do {


        }while(false);



        return maxFlow;
    }

    public static ArrayList<Node> BFS(Graph graph) {

        ArrayList<Node> path = new ArrayList<>();
        LinkedList<Node> nodes = new LinkedList<>();

        nodes.add(graph.getNodes().get(0));
        graph.getNodes().get(0).setVisited(true);
        while(!nodes.isEmpty()) {
            Node currentNode = nodes.poll();
            for(Node children : graph.getGraph().get(currentNode)) {
                if(!children.isVisited()) {
                    children.setFatherNode(currentNode);
                    children.setVisited(true);
                    nodes.add(children);
                }
            }
        }

        Node currentNode = graph.getNodes().get(graph.getNodes().size()-1);
        while(currentNode != null){
            path.add(currentNode);
            currentNode = currentNode.getFatherNode();
        }

        Collections.reverse(path);

        return path;
    }
}
