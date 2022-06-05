package Utils;

import Graph.*;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import static Utils.Utils.calculateMaxFlowPath;
import static Utils.Utils.setVisitedEdges;

//! Class Algorithms
public class Algorithms {


    public static ArrayList<Node> getPathForSecondScenery(Graph graph) {

        Queue<Node> queue = new LinkedList<>();
        Node dest = graph.getNodes().get(graph.getNodes().size()-1);
        Node source = graph.getNodes().get(0);
        ArrayList<Node> path = new ArrayList<>();

        queue.add(source);

        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            path.add(curr);
            if(curr.equals(dest)) {
                return path;
            }
            for(Edge edge : curr.getOutgoingEdges()) {
                edge.setVisited(true);
                if(edge.getFlow() > 0
                        && !edge.isVisited()) {
                    queue.add(edge.getDest());
                }
            }
        }

        return null;
    }

    //! Breadth-first search (second scenery)
    //!
    //! \param graph
    //! \return pair
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
                        && edge.getCapacity() - edge.getFlow() > 0) {
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
    //! Breadth-first search (first scenery)
    //!
    //! \param graph
    //! \return pair
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

    //! Edmonds Karp algorithm
    //!
    //! \param residual graph
    //! \return max flow
    public static int Edmonds_Karp(Graph rGraph) {

        int maxFlow = 0;

        while(true) {

            Pair<ArrayList<Node>, Integer> pathSolution = BFS(rGraph);
            ArrayList<Node> path = pathSolution.getV1();
            Integer flowPath = pathSolution.getV2();

            if(flowPath == 0) {
                break;
            }
            //System.out.print("fluxo =>" + flowPath + " | " + rGraph.getNodes().get(0));
            maxFlow += flowPath;

            Node currentNode = path.get(0);
            for(int i=1; i<path.size(); i++) {
                Node prev = currentNode;
                currentNode = path.get(i);
                Edge prevCurr = rGraph.getEdge(prev, currentNode);
                prevCurr.increaseFlow(flowPath);
                Edge currPrev = rGraph.getEdge(currentNode, prev);
                currPrev.decreaseFlow(flowPath);
            }
        }

        return maxFlow;
    }

    public static int criticalPathMethod(Graph graph) {

        int[] degree = new int[9999];

        int i = 0;
        for(Node node : graph.getNodes()) {
            for(Edge edge : node.getOutgoingEdges()) {
                degree[i]++;
                i++;
            }
            i = 0;
        }
        Queue<Node> queue = new LinkedList<>();
        int[] distance = new int[9999];
        for(Node node : graph.getNodes()) {
            if(degree[i] == 0) {
                queue.add(node);
            }
            i++;
        }
        for(int j = 0; !queue.isEmpty(); j++) {
            Node cur = queue.poll();
            for(Edge edge : cur.getOutgoingEdges()) {
                distance[i] = Math.max(distance[i],distance[j]+edge.getDuration());
                degree[i]--;
                if(degree[i] == 0) {
                    queue.add(edge.getDest());
                }
                i++;
            }
        }

        int max = 0;
        for(i = 0; i < distance.length; i++) {
            if(distance[i] > max) max = distance[i];
        }

    /*    Para todo v ∈ G.V fazer ES[v] ← 0; Prec[v] ← Nenhum; GrauE[v] ← 0;
        Para todo (v, w) ∈ G.A fazer GrauE[w] ← GrauE[w] + 1;
        S ← {v ∈ G.V | GrauE[v] = 0}; // S deve ser suportado por uma fila ou uma pilha. //
        DurMin ← −1; vf ← Nenhum;
        Enquanto (S 6= ∅) fazer
        v ← um qualquer elemento de S; S ← S \ {v};
        Se DurMin < ES[v] ent˜ao DurMin ← ES[v]; vf ← v;
        Para todo w ∈ G.Adjs[v] fazer
        Se ES[w] < ES[v] + D[(v, w)] ent˜ao
        ES[w] ← ES[v] + D[(v, w)]; Prec[w] ← v;
        GrauE[w] ← GrauE[w] − 1;
        Se GrauE[w] = 0 ent˜ao S ← S ∪ {w};
        escreveCaminho(vf , Prec); escrever(DurMin);
        */

        return max;
    }
}
