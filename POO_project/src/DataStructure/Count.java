package DataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Count {
	public int Classifier;
	
	ArrayList<int[]> emptyConf; 
	ArrayList<int[][]> counts;
	
	TrainDataSet trainSet;
	
	public Count(int c, TrainDataSet trainSet) {
		this.Classifier=c;
		this.trainSet = trainSet;
		createAndFillEmptyConf();
		createCounts();
		fillCounts();
	}
	
	public int getCountFromEmptyConf(int i, int j) {
		return -1;
	}
	
	
	// 
	public int getTableNumber(int i, int j) {
		int n = trainSet.getNbrOfVariables();
		int tableCounter = 0;
		for (int l = 0; l < n - 1; l++) {
			for (int l2 = l+1; l2 < n; l2++) {
				if (l==i && l2==j) {
					return tableCounter;
				}
				tableCounter++;
			}
		}
		return -1;
	}
	
	// values for node i.
	public int getNijkc(int i, int ii, int j, int k) {
		int t = getTableNumber(i, ii);
		int[][] table = counts.get(t);
		return table[k][j];
		
	}
	
	public int getNKijc(int i, int ii, int j) {
		int t = getTableNumber(i, ii);
		int[][] table = counts.get(t);
		
		int sum=0;
		for (int l = 0; l < trainSet.getRi(i); l++) {
			sum+=table[l][j];
		}
		return sum;
	}
	
	
	private void createAndFillEmptyConf() {
		emptyConf=new ArrayList<int[]>();
		int[] classifiers = trainSet.getClassifiers();
		
		for(int i = 0; i<trainSet.getNbrOfVariables(); i++) {
			emptyConf.add(0, new int[trainSet.getRi(i)]);
		}
		
		for (int i = 0; i < classifiers.length; i++) {
			if (Classifier==classifiers[i]) {
				for(int j = 0; j<trainSet.getNbrOfVariables(); j++) {
					emptyConf.get(j)[trainSet.getXi(j)[i]]++;
				}
			}
		}
		
	}

	private void createCounts() { 
		counts = new ArrayList<int[][]>();
		int n = trainSet.getNbrOfVariables();

		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
					int[][] table = new int[trainSet.getRi(i)][trainSet.getRi(j)];
					for (int k = 0; k < trainSet.getRi(i); k++) {
						for (int k2 = 0; k2 < trainSet.getRi(j); k2++) {
							table[k][k2]=0;
						}
					}
					counts.add(table);
			}
		}
		
	}
	
	private void fillCounts() {
		for (int i = 0; i < trainSet.getN(); i++) {
			TrainEntry entry = trainSet.getTrainEntry(i);
			
			//int c = entry.getClassifier();
			//ArrayList<int[][]> tables = counts.get(c);
			
			int tableCounter = 0;
			
			int n = trainSet.getNbrOfVariables();
			for (int j = 0; j < n - 1; j++) {
				for (int k = j+1; k < n; k++) {
					
					/*((int[][])*/
					counts.get(tableCounter++)[entry.getXi(j)][entry.getXi(k)]++;
					
				}
			}
			//System.err.print("classvalue-> tables created");
		}
		
		//System.err.print("classvalue-> tables created");
		
//		int n = trainSet.getNbrOfVariables();
//		for (int c = 0; c < trainSet.getS(); c++) {
//			ArrayList<int[][]> tables = counts.get(c);
//			for (int i = 0; i < n; i++) {
//				for (int j = i; j < n; j++) {
//					if (i==j) continue;
//						
//					int[][] table = tables.get(i*n+j);
//					//table[trainSet.getTrainEntry().getXi(i)][trainSet.getXi(j)]++;
//					
//				}
//			}
//		}
	}
}
