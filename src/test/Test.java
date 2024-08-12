package test;

import structures.Graph;

public class Test {

  public static void main(String[] args) {
    Graph<String> graph = new Graph<String>(true);
    graph.addVertex("4");
    graph.addVertex("0");
    graph.addVertex("2");
    graph.addVertex("3");
    graph.addVertex("1");

    graph.addEdge("1", "0");
    graph.addEdge("2", "3");
    graph.addEdge("1", "4");
    graph.addEdge("0", "3");
    graph.addEdge("0", "2");

    graph.printMatrix();

    System.out.println("METODO DE ANCHURA: " + graph.widthTravel("1"));

  }

}
