//
// Created by berna on 12/05/2022.
//

#include <sstream>
#include "Utils.h"

void parseString(std::string& line, std::vector<std::string>& values) {

    std::stringstream ss(line);
    std::string aux;

    while(getline(ss, aux, ' ')) {
        values.push_back(aux);
    }
}

void parseFile(ifstream& file, Graph*& graph) {

    string line;
    int numNodes, numEdges;
    vector<string> values;

    getline(file, line);
    parseString(line, values);

    numNodes = stoi(values[0]);
    numEdges = stoi(values[1]);
    graph = new Graph(numNodes, true);

    while(numEdges > 0) {
        values.clear();
        getline(file, line);
        parseString(line, values);
        graph->addEdge(stoi(values[0]), stoi(values[1]), stoi(values[2]), stoi(values[3]));
        numEdges--;
    }

}

void readFiles(Graph*& graph, string& filepath) {

    ifstream file;
    file.open(filepath);

    if(!file.is_open()) {
        cout << "Error opening file!" << endl;
        exit(1);
    }

    parseFile(file, graph);

    file.close();
}








