package DataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class WeightedGraph {
	
	List<WeightedEdge> edges;
	
	double[][] weights;
	
	Set<Integer> nodes;
	List<Integer> nodesList;
	public WeightedGraph() {
		nodes = new HashSet<Integer>();
		edges = new ArrayList<WeightedEdge>();
	}
	
	public void addEdge(WeightedEdge edge) {
		nodes.add(edge.n1); nodes.add(edge.n2);
		edges.add(edge);
	}
	
	public void addEdge(int n1, int n2, double weight) {
		nodes.add(n1); nodes.add(n2);
		WeightedEdge we = new WeightedEdge(n1, n2, weight);
		edges.add(we);
	}
	
	public void passWeightMatrix(double[][] weights) {
		this.weights = weights;
	}
	
	List<WeightedEdge> K; 
	List<Integer> U;
	
	
	
	public List<WeightedEdge> RunPrim() {
		nodesList = new ArrayList<Integer>(nodes);
		Random rnd = new Random();
		
		// blue edges
		K = new ArrayList<WeightedEdge>();
		U = new ArrayList<Integer>();
		Close = new int[nodes.size()];
		ClosestWeight = new double[nodes.size()];
		
		int node = nodesList.get(rnd.nextInt(nodes.size()));
		// first element in U
		initializeClose(node);
				
		Set<Integer> tempSet = new HashSet<Integer>(U);
		
		while (!tempSet.containsAll(nodes)) {
			
			int maxIdx = selectMaximumFromClosest();
			
			int nodeInsideU = Close[maxIdx];
			
			for (int i = 0; i < edges.size(); i++) {
				WeightedEdge e = edges.get(i);
				if ((e.getN1()==nodeInsideU && e.getN2()==maxIdx) ||
					(e.getN1()==maxIdx && e.getN2()==nodeInsideU)) {
					K.add(e);
				}
			}
			
			U.add(maxIdx);
			
			update(maxIdx);
			
			tempSet=new HashSet<Integer>(U);
		}
		
		return K;
	}
	
	// the closest node in U for each node
	int[] Close;
	double[] ClosestWeight;
	
	
	/**
	 *  add the first node to the U and initialize the Close array
	 *  (all the nodes will point to the new node) 
	 */
	private void initializeClose(int node) {
		U.add(node);
		for (int i = 0; i < nodesList.size(); i++) {
			int n1 = nodesList.get(i);
			if (U.contains(n1)) {
				Close[n1] = -1;
				ClosestWeight[n1] = -1;
			}
			for (int j = 0; j < edges.size(); j++) {
				WeightedEdge edge = edges.get(j);
				if ((edge.getN1()==n1 && edge.getN2()==node) || (edge.getN2()==n1 && edge.getN1()==node)) {
					Close[n1]=node;
					ClosestWeight[n1] = edge.getWeight();
				}
			}
		}
	}
	
	private int selectMaximumFromClosest() {
		int maxIdx=0;
		for (int i = 1; i < ClosestWeight.length; i++) {
			if (ClosestWeight[i]>ClosestWeight[maxIdx]) {
				maxIdx=i;
			}
		}
		return maxIdx;
	}
	
	/*
	 * the edge between the U set and the "node" has the maximum -> add node to set
	 * and also add the edge to the K set.
	 */
	private void update(int node) {
		Close[node]=-1;
		ClosestWeight[node]=-1;
		
		for (int i = 0; i < Close.length; i++) {
			if (weights[i][node]>ClosestWeight[i]) {
				Close[i]=node;
				ClosestWeight[i]=weights[i][node];
			} else {
				
				if (weights[node][i]>ClosestWeight[i]) {
					Close[i]=node;
					ClosestWeight[i]=weights[node][i];
				}
			}
		}
	}
}
