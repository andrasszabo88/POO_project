package DataStructure;

import java.util.Set;

public class Graph {
	Set<Node> Nodes;
	Set<Edge> Edges;
	
	
}

class Node {
	int index;
	
}

class Edge {
	int n1, n2;
	/**
	 * Direction: 
	 * 		1: n1->n2
	 * 		-1: n2->n1
	 * 		0: undirected
	 */
	int dir;
	float weight;
	
	public void setWeight(float w) {
		this.weight = w;
	}
	
	public void setDirection(int dir) {
		this.dir = dir;
	}
	
	public Edge(int n1, int n2, float weight) {
		this.n1 = n1;
		this.n2 = n2;
		this.weight = weight;
	}
	
	
}
