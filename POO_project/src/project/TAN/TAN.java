package project.TAN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import DataStructure.Count;
//import DataStructure.Graph;
import DataStructure.TrainDataSet;
import DataStructure.TreeNode;
import DataStructure.WeightedEdge;
import DataStructure.WeightedGraph;

public class TAN {
	TrainDataSet trainSet;
	
	double[][] weights;
	
	WeightedGraph graph;
	
	public int RootIndex;
	
	public Map<Integer, HashSet<Integer>> ParentNodes = new HashMap<Integer, HashSet<Integer>>();
	
	public Count[] counts;
	
	public Count getCountForClassifier(int c) {
		return counts[c];
	}
	
	public TAN(TrainDataSet trainSet, String score) {
		this.trainSet = trainSet;
		
		this.graph = new WeightedGraph();
		
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
				weights[i][j] = calcWeightsLL(i, j);
				WeightedEdge e=null;
				if (score.equalsIgnoreCase("ll")) {
					e = new WeightedEdge(i, j, calcWeightsLL(i, j));
				} else 
					if (score.equalsIgnoreCase("mdl")) {
						e = new WeightedEdge(i, j, calcWeightsMDL(i, j));
					}  
					
				if (e==null) {
					System.out.println("Please specify the score, it should be \"LL\" or \"MDL\".");
					return;
				}
				
				//WeightedEdge e = new WeightedEdge(i, j, calcWeightsLL(i, j));
				graph.addEdge(e);
				System.out.print(e.getWeight()+" ");
			}
			System.out.println();
		
		}
		
		((WeightedGraph)graph).passWeightMatrix(weights);
		//List<WeightedEdge> edges = graph.getSortedEdges();
		
		
		System.err.println("weights calculated");
		
		
		
		List<WeightedEdge> spanningTree = graph.RunPrim();
		for (WeightedEdge edge:spanningTree) {
			System.err.println("("+ edge.getN1()+ ";" +edge.getN2()+"): "+ edge.getWeight());
			
			
		}
		
		Random rnd = new Random();
		int r = rnd.nextInt(trainSet.getNbrOfVariables());
		
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		for(int i = 0; i<trainSet.getNbrOfVariables(); i++) {
			treeNodes.add(new TreeNode(i));
		}
		
		for(WeightedEdge e: spanningTree) {
			int n1 = e.getN1();
			int n2 = e.getN2();
			
			for(TreeNode node: treeNodes) {
				if (node.Id == n1) {
					for(TreeNode node2: treeNodes) {
						if (node2.Id==n2) {
							node.AddNode(node2);
						}
					}
				} else 
				if (node.Id == n2) {
					for(TreeNode node2: treeNodes) {
						if (node2.Id==n1) {
							node.AddNode(node2);
						}
					}
				}
			}
		}
		
		
		
		TreeNode treeNode = treeNodes.get(r);
		treeNode.VisitNodes();
		// direct graph
		
		for(TreeNode node : treeNodes) {
			System.err.print(node.getParent());
		}
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
