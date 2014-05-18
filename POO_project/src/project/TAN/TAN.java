package project.TAN;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import DataStructure.TrainDataSet;

public class TAN {
	TrainDataSet trainSet;
	
	public int RootIndex;
	
	public Map<Integer, HashSet<Integer>> ParentNodes = new HashMap<Integer, HashSet<Integer>>();
	
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
