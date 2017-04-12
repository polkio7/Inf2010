import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Graph {

	private List<Node> nodes; // Noeuds
	private List<Edge> edges; // Les arcs
	static final double inf = 99999;
	public Graph() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
	}
	
	public void readFromFile(String filePath,String separtor) throws IOException{
		// A completer
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(filePath) );
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String line="";
		line = br.readLine();

		//Creating the nodes by their names
		line = line.replaceAll(",", "");
		for(int i=0;i<line.length();i++){
				nodes.add(new Node(i,line.charAt(i)+""));
		}
		int lineNumber=0;
		int nodeNumber=0;
		while(line !=null){
			line = br.readLine();
			if(line!=null){
				for(int i=0;i<line.length();i++){
					if(line.charAt(i) != ','){
						if(line.charAt(i)=='i'){
							edges.add(new Edge(nodes.get(lineNumber),nodes.get(nodeNumber),inf) );
							i+=2;
						}else{
							edges.add(new Edge(nodes.get(lineNumber),nodes.get(nodeNumber),Integer.parseInt(line.charAt(i)+"")) );
						}
					}else
						nodeNumber++;
				}
				lineNumber++;
				nodeNumber=0;
			}
		}
	}
	public void displayGraph(){
		String name=edges.get(0).getSource().getName();
		for(int i=0;i<edges.size();i++){
			if(name.equals(edges.get(i).getSource().getName()) ) {
				name = edges.get(i).getSource().getName();
				System.out.print(edges.get(i).toString() + " | ");
			}
			else
				name = edges.get(i+1).getSource().getName();
				System.out.println();
		}
	}
	public List<Edge> getOutEdges(Node source) {
		List<Edge> outEdges = new LinkedList<Edge>(); 
		// A completer
		for(int i=0;i<edges.size();i++){
			if(edges.get(i).getSource().getName().equals(source.getName())){
				outEdges.add(edges.get(i));
			}
		}
		return outEdges;	
	}
	
	public List<Edge> getInEdges(Node dest) {
		List<Edge> inEdges = new LinkedList<Edge>(); 
		// A completer 
		for(int i=0;i<edges.size();i++){
			if(edges.get(i).getDestination().getName().equals(dest.getName())){
				inEdges.add(edges.get(i));
			}
		}
		return inEdges;		
	}
	// Accesseurs 
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public List<Edge> getEdges() {
		return edges;
	}
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	public Node getNodeByName(String name){
		for (Node node : nodes) {
			if(node.getName().equals(name)){
				return node;
			}
		}
		return null;
	}
}
