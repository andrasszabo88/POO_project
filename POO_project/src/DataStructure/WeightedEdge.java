package DataStructure;

public class WeightedEdge extends Edge implements Comparable<WeightedEdge> {
	double weight;

	public double getWeight() {
		return weight;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public int getN1() {
		return n1;
	}

	public int getN2() {
		return n2;
	}
	
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
