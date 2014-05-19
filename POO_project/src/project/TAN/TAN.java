package project.TAN;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import DataStructure.Count;
import DataStructure.TrainDataSet;
import DataStructure.Graph;

public class TAN {
	TrainDataSet trainSet;
	
	double[][] weights;
	
	Graph graph;
	
	public int RootIndex;
	
	public Map<Integer, HashSet<Integer>> ParentNodes = new HashMap<Integer, HashSet<Integer>>();
	
	public Count[] counts;
	
	public Count getCountForClassifier(int c) {
		return counts[c];
	}
	
	public TAN(TrainDataSet trainSet) {
		this.trainSet = trainSet;
		
		this.graph = new Graph();
		
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
		
		/*
		 * Count the occurences of the possible values of variables 
		 * 	matching with the specified classifier.
		 */
		counts = new Count[trainSet.getS()];
		for (int c = 0; c < trainSet.getS(); c++) {
			Count cnt = new Count(c, trainSet);
			counts[c] = cnt;
		}
		
		weights = new double[n][n];
		for (int i = 0; i < n-1; i++) {
			for (int j = i+1; j < n; j++) {
				weights[i][j] = calcWeightsMDL(i, j);
				
				System.out.print(weights[i][j]+" ");
			}
			System.out.println();
			
		}
		System.err.print("weights calculated");
		
	}
	
	private double calcWeightsMDL(int a, int b) {
		double LLScore = calcWeightsLL(a, b);
		double numerador = trainSet.getS() * ((trainSet.getRi(a)-1)*(trainSet.getRi(b)-1));
		
		double MDLScore = LLScore - ((double)numerador / 2) * Math.log(trainSet.getN());
		return MDLScore;
	}
	/*
	 * Calculate the weight of the edge between node a and b 
	 */
	private double calcWeightsLL(int a, int b) {
		double sum=0;
		
		int ri=trainSet.getRi(a);
		int rii=trainSet.getRi(b);
		
		for (int j = 0; j < trainSet.getRi(b); j++) {
			for (int k = 0; k < trainSet.getRi(a); k++) {
				for (int c = 0; c < trainSet.getS(); c++) {
					int Nijkc = 0;
					int Nc = 0;
					int NJikc = 0;
					int NKijc = 0;
					
					Count countForC = getCountForClassifier(c);
					
					Nijkc = countForC.getNijkc(a, b, j, k);
					
					
					int[] classifiers = trainSet.getClassifiers();
					
					for (int i = 0; i < classifiers.length; i++) {
						if (classifiers[i]==c) {
							Nc++;
						}
					}
					
					
					
					int[] XI = trainSet.getXi(a);
					for (int i = 0; i < XI.length; i++) {
						if (XI[i]==k && classifiers[i]==c)
							NJikc++;
					}
					
//					for (int i = 0; i < rii; i++) {
//						NJikc+=countForC.getNijkc(a, b, i, k);
//					}
					
//					for (int i = 0; i < ri; i++) {
//						NKijc+=countForC.getNijkc(a, b, j, i);
//					}
					NKijc = countForC.getNKijc(a, b, j);
					
					double multiplier = (double)Nijkc / trainSet.getN();
					
					double numerador=Nijkc*Nc;
					double denominator = NJikc*NKijc;
					double multiplied = (double)(numerador)/(denominator);
					
					double logarithm = Math.log(multiplied)/Math.log(2);
					
					if (!(multiplier==0 || numerador==0 || denominator==0))
						sum+=multiplier*logarithm;
				}
			}
		}
		return sum;
		
	}
}
