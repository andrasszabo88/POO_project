package DataStructure;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	
	public int Id;
	
	int Parent=-1;
	
	List<TreeNode> nodes = new ArrayList<TreeNode>();
	
	public TreeNode(int id) {
		this.Id = id;
	}
	
	public void AddNode(TreeNode node) {
		nodes.add(node);
	}
	
	public void setParent(int i) {
		Parent = i;
	}
	
	public int getParent() {
		return this.Parent;
	}
	
	public void VisitNodes() {
		//System.err.print(this.Id+"->");
		for(TreeNode node : nodes) {
			if (node.Id!=Parent) {
				
				node.setParent(this.Id);
				//System.err.println(this.Id+"->" + node.Id+";");
				node.VisitNodes();
			}
		}
		
	}
	
	
	
}
