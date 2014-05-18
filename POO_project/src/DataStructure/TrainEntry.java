package DataStructure;

import project.TAN.TAN;

public class TrainEntry {
	private int[] attributes;
	private int classifier;
	
	public TrainEntry(int[] attributes, int classifier) {
		this.attributes = attributes;
		this.classifier=classifier;
	}
	
	public TrainEntry(int[] entry) {
		this.attributes = new int[entry.length-1];
		this.classifier=entry[entry.length-1];
		System.arraycopy(entry, 0, this.attributes, 0, entry.length-1);
	}
	
	public int getClassifier() {
		return classifier;
	}
	
	public int getXi(int i) {
		return attributes[i];
	}
	
	public int[] getX() {
		return attributes.clone();
	}
	
	public int[] getEntry() {
		int[] entry = new int[attributes.length+1];
		System.arraycopy(entry, 0, this.attributes, 0, this.attributes.length);
		entry[entry.length-1] = this.classifier;
		
		return entry;
	}
	
}
