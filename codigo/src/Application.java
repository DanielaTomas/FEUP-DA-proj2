

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import Graph.*;

import Utils.Utils;

public class Application {

    public static void main(String[] args) {

        Graph graph = new Graph();
        ArrayList<Node> path = new ArrayList<>();

        System.out.println(graph.printGraph());
        Utils.CaminhosCapacidadeMaxima(graph, path);

        System.out.println("fewfewfew");
    }
}
