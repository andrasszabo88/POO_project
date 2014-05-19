package DataStructure;

import project.TAN.TanMain;

/**
 * Provides methods to retrieve information from a train entry
 *
 */
public class TrainEntry {
	private int[] attributes;
	private int classifier;
	
	/**
	 * Constructor for a train entry without a classifier value in the array "entry"
	 * @param attributes
	 * @param classifier
	 */
	public TrainEntry(int[] attributes, int classifier) {
		this.attributes = attributes;
		this.classifier=classifier;
	}
	
	/**
	 * Constructor for a train entry with a classifier value in the array "entry"
	 * @param entry
	 */
	public TrainEntry(int[] entry) {
		this.attributes = new int[entry.length-1];
		this.classifier=entry[entry.length-1];
		System.arraycopy(entry, 0, this.attributes, 0, entry.length-1);
	}
	
	/**
	 * Returns the classifier of this train entry
	 * @return value of c
	 */
	public int getClassifier() {
		return classifier;
	}
	
	/**
	 * Returns the attribute xi from this train entry
	 * @param i
	 * @return value of xi
	 */
	public int getXi(int i) {
		return attributes[i];
	}
	
	/**
	 * Returns a cloned array of the values of the attributes of this train entry
	 * @return a cloned array of the values of the attributes
	 */
	public int[] getX() {
		return attributes.clone();
	}
	
	/**
	 * Returns the whole entry array
	 * @return entry
	 */
	public int[] getEntry() {
		int[] entry = new int[attributes.length+1];
		System.arraycopy(entry, 0, this.attributes, 0, this.attributes.length);
		entry[entry.length-1] = this.classifier;
		
		return entry;
	}
	
}
