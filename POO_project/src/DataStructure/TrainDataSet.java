package DataStructure;

import java.util.ArrayList;

/**
 * Provides methods to retrieve information from the TrainDataSet
 *
 */
public class TrainDataSet {
	
	ArrayList<TrainEntry> trainSet;

	int[] maxValues;
	
	/**
	 * constructor
	 */
	public TrainDataSet() {

		trainSet = new ArrayList<TrainEntry>();
	}
	
	/**
	 * Returns the whole entries of the train file in a TraindataSet object
	 * @param entries
	 */
	public TrainDataSet(ArrayList<TrainEntry> entries) {
		this.trainSet = entries;
	}
	
	/**
	 * Returns the number of possible values of the attribute Xi
	 * @param i
	 * @return value of ri
	 */
	public int getRi(int i) {
		return maxValues[i]+1;
	}
	
	/**
	 * Returns the number of possible values of the class attribute
	 * @return value of s
	 */
	public int getS() {
		return maxValues[maxValues.length-1]+1;
	}
	
	/**
	 * Returns the total number of train entries
	 * @return value of N
	 */
	public int getN() {
		return trainSet.size();
	}
	
	/**
	 * Returns the total number of attributes
	 * @return value of n
	 */
	public int getNbrOfVariables() {
		return maxValues.length-1;
	}
	
	/**
	 * Adds a train entry read from the file to the ArrayList that contains all the entries
	 * @param trainEntry
	 */
	public void addTrainEntry(TrainEntry trainEntry) {
		trainSet.add(trainEntry);
		if (getN() > 1) {
			for(int i = 0; i<maxValues.length-1;i++) {
				if (trainEntry.getXi(i) > maxValues[i]) {
					maxValues[i] = trainEntry.getXi(i);
				}
			}
			if (trainEntry.getClassifier()> maxValues[maxValues.length-1]) {
				maxValues[maxValues.length-1] = trainEntry.getClassifier();
			}
				
		} else {
			maxValues = trainEntry.getEntry();
		}
	}
		
	public TrainEntry getTrainEntry(int i) {
		return trainSet.get(i);
	}
	
	/**
	 * Returns all train values of the random variable Xi
	 * @param i
	 * @return Xi vector
	 */
	public int[] getXi(int i) {
		int[] returnValue = new int[trainSet.size()];
		
		for(int j = 0; j<trainSet.size(); j++) {
			returnValue[j]=trainSet.get(j).getXi(i);
		}
		
		return returnValue;
	}
	
	/**
	 * Returns all classifiers of the trainset
	 * @return C vector
	 */
	public int[] getClassifiers() {
		int[] returnValue = new int[trainSet.size()];
		
		for(int j = 0; j<trainSet.size(); j++) {
			returnValue[j]=trainSet.get(j).getClassifier();
		}
		
		return returnValue;
	}
	
}
