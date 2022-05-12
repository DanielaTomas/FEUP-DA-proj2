//
// Created by berna on 12/05/2022.
//

#ifndef CODIGO_UTILS_H
#define CODIGO_UTILS_H

#include <fstream>
#include <vector>
#include <iostream>
#include "graph.h"
using namespace std;

void parseFile(ifstream& file, Graph*& graph);
void readFiles(Graph*& graph, string& filepath);


#endif //CODIGO_UTILS_H
