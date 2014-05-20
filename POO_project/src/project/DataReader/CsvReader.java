package project.DataReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import DataStructure.TestEntry;
import DataStructure.TrainDataSet;
import DataStructure.TrainEntry;

public class CsvReader {
	
	public static TrainDataSet Read(String fileName) {
				
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
		
			String line;
			
			// Read the "header" to count attributes
			String firstLine = br.readLine();
			if (firstLine==null) return null;
			String[] header = firstLine.split(",");
			int N = header.length-1;
			
			int[] maximums = new int[N+1];
			
			TrainDataSet trainSet = new TrainDataSet();
			//ArrayList<int[]> trainTable = new ArrayList<int[]>();
			
			while ((line = br.readLine()) != null) {
			   String[] row = line.split(",");
			   
			   int[] intRow = new int[N+1];
			   			   
			   for(int i = 0; i < row.length; i++) {
				   int x = Integer.parseInt(row[i]);
				   
//				   if (x > maximums[i]) {
//					   maximums[i] = x;
//				   }
				  
				   intRow[i] = x;
			   }
			   			   
			   trainSet.addTrainEntry(new TrainEntry(intRow));		   
			}
			
			br.close();
			
			return trainSet;
			
//			int[][] trainArray = new int[trainTable.size()][N+1];
//			for(int i=0; i<trainTable.size();i++)
//				for (int j=0; j<N+1; j++) {
//					int[] row = trainTable.get(i);
//					trainArray[i][j] = trainTable.get(i)[j];
//				}
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			br = null;
		}
		return null;
		
		
	}
	
	public static List<TestEntry> ReadTest(String fileName) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
		
			String line;
			
			String firstLine = br.readLine();
			if (firstLine==null) return null;
			String[] header = firstLine.split(",");
			int N = header.length-1;
			
			List<TestEntry> testSet = new LinkedList<TestEntry>();
			//ArrayList<int[]> trainTable = new ArrayList<int[]>();
			
			while ((line = br.readLine()) != null) {
			   String[] row = line.split(",");
			   
			   int[] intRow = new int[N+1];
			   			   
			   for(int i = 0; i < row.length; i++) {
				   int x = Integer.parseInt(row[i]);
				   
//				   if (x > maximums[i]) {
//					   maximums[i] = x;
//				   }
				  
				   intRow[i] = x;
			   }
			   			   
			   testSet.add(new TestEntry(intRow));		   
			}
			
			br.close();
			
			return testSet;
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			br = null;
		}
		return null;
		
	}
}
