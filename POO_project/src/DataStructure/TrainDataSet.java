package DataStructure;

import java.util.ArrayList;

// use the collection interface!!!
public class TrainDataSet {
	
	ArrayList<TrainEntry> trainSet;

	int[] maxValues;
	
	public TrainDataSet() {

		trainSet = new ArrayList<TrainEntry>();
	}
	
	public TrainDataSet(ArrayList<TrainEntry> entries) {
		this.trainSet = entries;
	}
	
	public int getRi(int i) {
		return maxValues[i]+1;
	}
	
	public int getS() {
		return maxValues[maxValues.length-1]+1;
	}
	
	public int getN() {
		return trainSet.size();
	}
	
	// number of attributes
	public int getNbrOfVariables() {
		return maxValues.length-1;
	}
	
	/**
	 * 
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
	 * Returns all train values of the random variable Xi.
	 * @param i
	 * @return 
	 */
	public int[] getXi(int i) {
		int[] returnValue = new int[trainSet.size()];
		
		for(int j = 0; j<trainSet.size(); j++) {
			returnValue[j]=trainSet.get(j).getXi(i);
		}
		
		return returnValue;
	}
	
	/**
	 * Returns all classifiers of the trainset.
	 * @return 
	 */
	public int[] getClassifiers() {
		int[] returnValue = new int[trainSet.size()];
		
		for(int j = 0; j<trainSet.size(); j++) {
			returnValue[j]=trainSet.get(j).getClassifier();
		}
		
		return returnValue;
	}
	
}
