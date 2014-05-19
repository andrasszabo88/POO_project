package project.TAN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import project.DataReader.CsvReader;
import DataStructure.TrainDataSet;
import DataStructure.TrainEntry;

public class TanMain {

	
	
	static TrainDataSet trainSet;
	
	public static void main(String[] args) {
		trainSet = CsvReader.Read(args[0]);
		
		TAN tan = new TAN(trainSet);
		
		
//		System.err.print("end.");
	}
	
	
}
