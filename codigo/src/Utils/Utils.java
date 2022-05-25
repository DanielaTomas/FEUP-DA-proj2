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

    public static int calculateMaxFlowPath(Graph graph, ArrayList<Node> path) {

        Node currentNode = graph.getNodes().get(graph.getNodes().size()-1);
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

        return calculateMaxFlowPath(graph, path);
    }

    public static int Edmonds_Karp(Graph graph) {

        Graph rGraph = new Graph();
        int maxFlow = 0;
        ArrayList<Node> path;

        /*
        while((path = BFS(graph)).get(path.size()-1).equals(graph.getNodes().get(graph.getNodes().size()-1))) {

            for(Node node : path) {

            }

        }
        */
        return maxFlow;
    }

    public static void setVisitedEdges(Graph graph, ArrayList<Node> path) {

        Node lastNode = path.get(0);
        for(int i=1; i<path.size(); i++) {
            Edge edge = graph.getEdge(lastNode, path.get(i));
            edge.setVisited(true);
            lastNode = path.get(i);
        }

    }

    public static boolean isNotVisited(Node node, ArrayList<Node> path) {

        for(Node _node: path) {
            if(_node.equals(node)) {
                return false;
            }
        }

        return true;
    }
    /*
    public static void findPaths(Graph graph) {

        ArrayList<ArrayList<Node>> paths = new ArrayList<>();

        Queue<ArrayList<Node>> queue_path = new LinkedList<>();
        ArrayList<Node> path = new ArrayList<>();

        Node src = graph.getNodes().get(0);
        Node dest = graph.getNodes().get(graph.getNodes().size()-1);
        path.add(src);
        queue_path.offer(path);

        while(!queue_path.isEmpty()) {
            path = queue_path.poll();
            Node last = path.get(path.size()-1);

            if(last.equals(dest)) {
                System.out.println(path);
            }

            List<Node> lastNode = graph.getGraph().get(last);
            for(Node node: lastNode) {
                if(isNotVisited(node, path)) {
                    ArrayList<Node> newpath = new ArrayList<>(path);
                    newpath.add(node);
                    queue_path.offer(newpath);
                }
            }
        }
    }
    */


    public static Pair<ArrayList<Node>, Integer> BFS(Graph graph) {

        ArrayList<Node> path = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();

        queue.add(graph.getNodes().get(0));
        graph.getNodes().get(0).setVisited(true);
        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            for(Node child : graph.getGraph().get(currentNode)) {
                if(!child.isVisited()) {
                    child.setFatherNode(currentNode);
                    child.setVisited(true);
                    queue.add(child);
                }
            }
        }

        int maxP = calculateMaxFlowPath(graph, path);

        return new Pair<>(path, maxP);
    }

    public static Pair<ArrayList<Node>, Integer> BFS_N(Graph graph) {

        ArrayList<Node> path = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();

        for(Node node : graph.getNodes()) {
            node.setVisited(false);
            node.setFatherNode(null);
        }

        queue.add(graph.getNodes().get(0));
        graph.getNodes().get(0).setVisited(true);
        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            //System.out.println(currentNode);
            for(Node child : graph.getGraph().get(currentNode)) {
                if(graph.getEdge(currentNode, child).isVisited()) {
                    continue;
                }
                if(!child.isVisited()) {
                    child.setFatherNode(currentNode);
                    child.setVisited(true);
                    queue.add(child);
                }
            }
        }

        int maxP = calculateMaxFlowPath(graph, path);
        setVisitedEdges(graph, path);

        return new Pair<>(path, maxP);
    }
}
