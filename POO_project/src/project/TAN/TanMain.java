package project.TAN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import project.DataReader.CsvReader;
import DataStructure.TrainDataSet;
import DataStructure.TrainEntry;

public class TanMain {

	static Map<Integer, ArrayList<int[][]>> counts;
	
	static TrainDataSet trainSet;
	
	public static void main(String[] args) {
		trainSet = CsvReader.Read(args[0]);
		
		TAN tan = new TAN(trainSet);
		
		createCounts();
		fillCounts();
		System.err.print("end.");
	}
	
	private static void createCounts() { 
		counts = new HashMap<Integer, ArrayList<int[][]>>();
		int n = trainSet.getNbrOfVariables();
		for (int c = 0; c < trainSet.getS(); c++) {
			ArrayList<int[][]> temp = new ArrayList<int[][]>();
			for (int i = 0; i < n - 1; i++) {
				for (int j = i + 1; j < n; j++) {
						int[][] table = new int[trainSet.getRi(i)][trainSet.getRi(j)];
						for (int k = 0; k < trainSet.getRi(i); k++) {
							for (int k2 = 0; k2 < trainSet.getRi(j); k2++) {
								table[k][k2]=0;
							}
						}
						temp.add(table);
				}
			}
			
			counts.put(c, temp);
		}
	}
	
	private static void fillCounts() {
		for (int i = 0; i < trainSet.getN(); i++) {
			TrainEntry entry = trainSet.getTrainEntry(i);
			
			int c = entry.getClassifier();
			ArrayList<int[][]> tables = counts.get(c);
			
			int tableCounter = 0;
			
			int n = trainSet.getNbrOfVariables();
			for (int j = 0; j < n - 1; j++) {
				for (int k = j+1; k < n; k++) {
					try {
					((int[][])tables.get(tableCounter++))[entry.getXi(j)][entry.getXi(k)]++;
					}
					catch (Exception e) {
						System.err.println(tableCounter + " "+i+" "+j + " "+k);
					}
				}
			}
			System.err.print("classvalue-> tables created");
		}
		
		System.err.print("classvalue-> tables created");
		
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
