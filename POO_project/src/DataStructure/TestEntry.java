package DataStructure;

public class TestEntry {
	private int[] attributes;
	
	public TestEntry(int[] entry) {
		this.attributes = entry;
	}
	
	public int getLength() {
		return attributes.length;
	}
	
	public int getXi(int i) {
		return attributes[i];
	}
	
	
}
