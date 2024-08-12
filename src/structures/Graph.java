package structures;

import java.util.ArrayList;

public class Graph<T> {
  private int matrix[][];
  private ArrayList<T> vertexes;
  private boolean directed;
  private MyQueue<T> queue;

  public Graph(boolean directed) {
    this.matrix = new int[0][0];
    this.vertexes = new ArrayList<T>();
    this.directed = directed;
    this.queue = new MyQueue<T>();
  }

  public void addVertex(T vertex) {
    this.vertexes.add(vertex);
    int[][] newMatrix = new int[this.vertexes.size()][this.vertexes.size()];
    for (int i = 0; i < this.matrix.length; i++) {
      for (int j = 0; j < this.matrix[i].length; j++) {
        newMatrix[i][j] = this.matrix[i][j];
      }
    }
    this.matrix = newMatrix;
  }

  public void addEdge(T vertex1, T vertex2, int weight) {
    int index1 = this.vertexes.indexOf(vertex1);
    int index2 = this.vertexes.indexOf(vertex2);
    if (directed) {
      if (index1 != -1 && index2 != -1) {
        this.matrix[index1][index2] = weight;
      }
    } else {
      if (index1 != -1 && index2 != -1) {
        this.matrix[index1][index2] = weight;
        this.matrix[index2][index1] = weight;
      }
    }
  }

  public void addEdge(T vertex1, T vertex2) {
    int index1 = this.vertexes.indexOf(vertex1);
    int index2 = this.vertexes.indexOf(vertex2);
    if (directed) {
      if (index1 != -1 && index2 != -1) {
        this.matrix[index1][index2] = 1;
      }
    } else {
      if (index1 != -1 && index2 != -1) {
        this.matrix[index1][index2] = 1;
        this.matrix[index2][index1] = 1;
      }
    }
  }

  public void removeVertex(T vertex) {
    int index = this.vertexes.indexOf(vertex);
    if (index != -1) {
      this.vertexes.remove(index);
      int[][] newMatrix = new int[this.vertexes.size()][this.vertexes.size()];
      for (int i = 0; i < this.matrix.length; i++) {
        if (i != index) {
          for (int j = 0; j < this.matrix[i].length; j++) {
            if (j != index) {
              newMatrix[i][j] = this.matrix[i][j];
            }
          }
        }
      }
      this.matrix = newMatrix;
    }
  }

  public void removeEdge(T vertex1, T vertex2) {
    int index1 = this.vertexes.indexOf(vertex1);
    int index2 = this.vertexes.indexOf(vertex2);
    if (index1 != -1 && index2 != -1) {
      this.matrix[index1][index2] = 0;
      this.matrix[index2][index1] = 0;
    }
  }

  public boolean isAdjacent(T vertex1, T vertex2) {
    int index1 = this.vertexes.indexOf(vertex1);
    int index2 = this.vertexes.indexOf(vertex2);
    if (index1 != -1 && index2 != -1) {
      return this.matrix[index1][index2] != 0;
    }
    return false;
  }

  public void printMatrix() {
    for (int i = 0; i < this.matrix.length; i++) {
      System.out.print(this.vertexes.get(i) + ": ");
      for (int j = 0; j < this.matrix[i].length; j++) {
        System.out.print(this.matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  public String widthTravel(T startPoint) {
    String result = "";
    int index = this.vertexes.indexOf(startPoint);
    if (index != -1) {
      boolean visited[] = new boolean[this.vertexes.size()];
      for (int i = 0; i < visited.length; i++) {
        visited[i] = false;
      }
      visited[index] = true;
      this.queue.push(startPoint);
      while (!this.queue.isEmpty()) {
        T vertex = this.queue.poll();
        result += vertex + " ";
        int vertexIndex = this.vertexes.indexOf(vertex);
        for (int i = 0; i < this.matrix[vertexIndex].length; i++) {
          if (this.matrix[vertexIndex][i] != 0 && !visited[i]) {
            this.queue.push(this.vertexes.get(i));
            visited[i] = true;
          }
        }
      }
    }
    return result;
  }

}
