#include <iostream>
#include "graph.h"
#include "Utils.h"
using namespace std;

void firstScenery(Graph& graph) {



}

void secondScenery(Graph& graph) {



}


int main() {

    Graph* graph = nullptr;
    string filepath = "../../dataset/Tests/in04.txt";

    readFiles(graph, filepath);

    graph->dfs(1);

    delete graph;
}
