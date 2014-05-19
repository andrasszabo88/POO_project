package DataStructure;

import java.util.ArrayList;
import java.util.List;

//public class Graph {
//	//float[][] graph;
//	
//	List<Edge> edges;
//	
//	public Graph() {
//		edges = new ArrayList<Edge>();
//	}
//	
//	public void addEdge(Edge edge) {
//		for (Edge e : edges) {
//			if ((e.n1==edge.n1 && e.n2==edge.n2) || (e.n1==edge.n2 && e.n2==edge.n1)) {
//				return;
//			}
//		}
//		
//		edges.add(edge);
//	}
//	
//	public void addEdge(int n1, int n2) {
//		for (Edge e : edges) {
//			if ((e.n1==n1 && e.n2==n2) || (e.n1==n2 && e.n2==n1)) {
//				return;
//			}
//		}
//		edges.add(new Edge(n1, n2));
//	}
//	
//	public Edge getEdge(int n1, int n2) {
//		for (Edge e : edges) {
//			if ((e.n1==n1 && e.n2==n2) || (e.n2==n1 && e.n1==n2)) {
//				return e;
//			}
//		}
//		return null;
//	}
//	
//	public List<WeightedEdge> getSortedEdges() {
//		Comparable<?>[] res = new Comparable<?>[edges.size()]; 
//		System.arraycopy(edges.toArray(), 0, res, 0, edges.size()); 
//		java.util.Arrays.sort(res, 0, res.length);
//		
//		List<WeightedEdge> sortedEdges = new ArrayList<WeightedEdge>();
//		sortedEdges = new ArrayList<WeightedEdge>();
//		for (int i = 0; i < res.length; i++) {
//			sortedEdges.add((WeightedEdge)res[i]);
//		}
//		
//		return sortedEdges;
//	}
//	
//}



