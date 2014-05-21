package project.TAN;

import java.util.List;

import project.DataReader.CsvReader;
import DataStructure.TestEntry;
import DataStructure.TrainDataSet;

public class TanMain {

	
	
	static TrainDataSet trainSet;
	static List<TestEntry> testSet;
	static TAN tan;
	
	public static void main(String[] args) {
		if (args.length<3) {
			System.out.println("Arguments missing");
			System.out.println("3 parameters required: trainfile testfile score");
			return;
		}
		
		long startTime = System.currentTimeMillis();
		
		trainSet = CsvReader.Read(args[0]);
		
		tan = new TAN(trainSet, args[2]);
		long stopTime =System.currentTimeMillis();
		System.out.print("Building the classifier: ");
		System.out.print(stopTime-startTime);
		
		System.out.println();
		testSet = CsvReader.ReadTest(args[1]);
		
		for(int i=0; i<testSet.size(); i++) {
			
			System.out.print("->");
			System.out.print(" instance " + (i+1)+ ":");
			System.out.print("\t\t");
			System.out.print(classify(getProbabilties(testSet.get(i))));
			System.out.println();
		}
		
		//System.err.print("end.");
	}
	
	private static int classify(double[] probalities) {
		int maxIndex = 0;
		double maxValue = probalities[0];
		
		for (int i = 1; i < probalities.length; i++) {
			if (probalities[i]>maxValue) {
				maxIndex = i;
				maxValue = probalities[i]; 
			}
		}
		
		return maxIndex;
	}
	
	private static double[] getProbabilties(TestEntry entry) {
		double[] probalities = new double[trainSet.getS()];
		
		for (int c = 0; c < trainSet.getS(); c++) {
			double thetaC = tan.getThetaC(c);
			
			double multiplyPXi=0;
			
			for (int i = 0; i < entry.getLength(); i++) {
				int parentId = tan.getParent(i);
				
				int j = parentId==-1? -1 :entry.getXi(parentId);
				int k = entry.getXi(i);
				
				double P = tan.getThetaIJKC(i, parentId, j, k, c);
				if (i==0) multiplyPXi = P;
				else multiplyPXi = multiplyPXi*P;
				
			}
			
			probalities[c]=thetaC*multiplyPXi;
		}
		
		return probalities;
	}
	
}
