package DataStructure;

import java.util.List;


public class WeightedGraph extends Graph {
	List<WeightedEdge> edges;
	
	public void addEdge(int n1, int n2, double weight) {
		WeightedEdge we = new WeightedEdge(n1, n2, weight);
		edges.add(we);
	}
	
	
}
