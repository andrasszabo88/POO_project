package project.TAN;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import DataStructure.TrainDataSet;
import DataStructure.TrainEntry;

public class TAN {
	TrainDataSet trainSet;
	
	public int RootIndex;
	
	public Map<Integer, HashSet<Integer>> ParentNodes = new HashMap<Integer, HashSet<Integer>>();
	
	// parentConfigurations
	private void firstParentConfiguration() {
		for (int i = 0; i < trainSet.getNbrOfVariables(); i++) {
			
		}
	}
	
	/**
	 * Returns with the number of instances (occurrences) in the data T where the variable Xi
	 * takes its k-th value x-ik and the class variable takes its c-th value.
	 * @param i The index of the random variable: X(i);
	 * @param k The specified value what should be taken by the variable in the entry..
	 * @param c The specified value of the class variable.
	 */
	private int getNijkcForFirstParent(int i, int k, int c) {
		int retVal = 0;
		for(int m = 0; m<trainSet.getN(); m++) {
			TrainEntry entry = trainSet.getTrainEntry(m); 
			if (entry.getXi(i)==k && entry.getClassifier()==c) {
				retVal++;
			}
		}
		
		return retVal;
	}
	
	
	public TAN(TrainDataSet trainSet) {
		this.trainSet = trainSet;
		
//		Random random= new Random();
		int n = trainSet.getNbrOfVariables();
//		RootIndex = random.nextInt(n);
		
		for (int i = 0; i < n; i++) {
//			if (i!=this.RootIndex) {
				ParentNodes.put(i, new HashSet<Integer>());
				for (int j = 0; j < n; j++) {
					if (j!=i)
						ParentNodes.get(i).add(j);
				}
	//		}
		}
	}
	
	
}
