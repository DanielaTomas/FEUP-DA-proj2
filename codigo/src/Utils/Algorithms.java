package Utils;

import Graph.*;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static Utils.Utils.calculateMaxFlowPath;
import static Utils.Utils.setVisitedEdges;

public class Algorithms {


    public static Pair<ArrayList<Node>, Integer> BFS(Graph graph) {

        Queue<Node> queue = new LinkedList<>();
        Node dest = graph.getNodes().get(graph.getNodes().size()-1);
        Node source = graph.getNodes().get(0);
        ArrayList<Node> path = new ArrayList<>();

        for(Node node : graph.getNodes()) {
            node.setFatherNode(null);
        }

        queue.add(source);

        while(!queue.isEmpty()) {
            Node curr = queue.poll();

            for(Node child : graph.getGraph().get(curr)) {
                Edge edge = graph.getEdge(curr, child);
                if(child.getFatherNode() == null
                        && edge.getFlow() > 0) {
                    child.setFatherNode(curr);
                    if(child.equals(dest)) {
                        int maxF = calculateMaxFlowPath(graph, path);
                        return new Pair<>(path, maxF);
                    }
                    queue.add(child);
               }
            }
        }


        return new Pair<ArrayList<Node>, Integer>(new ArrayList<>(), 0);
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

    public static int Edmonds_Karp(Graph rGraph) {

        int maxFlow = 0;

        while(true) {

            Pair<ArrayList<Node>, Integer> pathSolution = BFS(rGraph);
            ArrayList<Node> path = pathSolution.getV1();
            Integer flowPath = pathSolution.getV2();

            if(flowPath == 0) {
                break;
            }

            maxFlow += flowPath;

            Node currentNode = path.get(0);
            for(int i=1; i<path.size(); i++) {
                Node prev = currentNode;
                currentNode = path.get(i);
                Edge prevCurr = rGraph.getEdge(prev, currentNode);
                prevCurr.decreaseFlow(flowPath);
                Edge currPrev = rGraph.getEdge(currentNode, prev);
                currPrev.increaseFlow(flowPath);
            }
        }

        return maxFlow;
    }
}
