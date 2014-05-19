package DataStructure;

import java.util.Set;

public class Graph {
	float[][] graph;
	
	public void setWeight(int i, int j, float w) {
		graph[i][j]=w;
	}
	
	public float getWeight(int i, int j) {
		return graph[i][j];
	}
	
	
}
