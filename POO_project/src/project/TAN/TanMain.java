package project.TAN;

import java.util.List;

import project.DataReader.CsvReader;
import DataStructure.TestEntry;
import DataStructure.TrainDataSet;

public class TanMain {

	
	
	static TrainDataSet trainSet;
	static List<TestEntry> testSet;
	
	public static void main(String[] args) {
		if (args.length<3) {
			System.out.println("Arguments missing");
			System.out.println("3 parameters required: trainfile testfile score");
			return;
		}
			
		
		trainSet = CsvReader.Read(args[0]);
		
		TAN tan = new TAN(trainSet, args[2]);
		
		testSet = CsvReader.ReadTest(args[1]);
		
		System.err.print("end.");
	}
	
	
}
