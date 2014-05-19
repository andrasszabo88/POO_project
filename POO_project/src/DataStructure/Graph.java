package DataStructure;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	//float[][] graph;
	
	List<Edge> edges;
	
	public Graph() {
		edges = new ArrayList<Edge>();
	}
	
	public void addEdge(int n1, int n2) {
		for (Edge e : edges) {
			if ((e.n1==n1 && e.n2==n2) || (e.n1==n2 && e.n2==n1)) {
				return;
			}
		}
		edges.add(new Edge(n1, n2));
	}
	
	public Edge getEdge(int n1, int n2) {
		for (Edge e : edges) {
			if ((e.n1==n1 && e.n2==n2) || (e.n2==n1 && e.n1==n2)) {
				return e;
			}
		}
		return null;
	}
	
	public Comparable<?>[] getSortedEdges() {
		Comparable<?>[] res = new Comparable<?>[edges.size()]; 
		System.arraycopy(edges.toArray(), 0, res, 0, edges.size()); 
		java.util.Arrays.sort(res, 0, res.length);
		return res;
	}
	
}

class Edge {
	int n1, n2;
	
	public Edge(int n1, int n2) {
		this.n1 = n1;
		this.n2 = n2;	
	}
}

class WeightedEdge extends Edge implements Comparable<WeightedEdge> {
	double weight;

	// 1  - blue
	// 0  - neutral
	// -1 - red
	int color;
	
	// 1  - n1 -> n2
	// 0  - undirected
	// -1 - n2 -> n1 
	int direction;
	
	

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public WeightedEdge(int n1, int n2, double weight) {
		super(n1, n2);
		this.weight=weight;
	}

	public int compareTo(WeightedEdge edge) {
		if (weight > edge.weight) return 1;
		else if (weight == edge.weight) return 0;
		else return -1;
	}
}
