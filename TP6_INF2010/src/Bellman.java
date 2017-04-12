import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;


public class Bellman {
	private Graph graph;
	private Node sourceNode;
	private List<Vector<Double>> piTable;
	private List<Vector<Integer>> rTable;
	
	public Bellman (Graph g) {
		this.graph = g;
		List<Vector<Double>> piTable = new ArrayList<Vector<Double>>();
		List<Vector<Integer>> rTable = new ArrayList<Vector<Integer>>();
	}
	
	public void setSourceNode(Node source) {
		this.sourceNode = source;
	}
	
	public void shortestPath() {
		// completer
		int k=0;
		int n=graph.getNodes().size();
		Node s=this.sourceNode;
		//Première ligne du piTable et RTable
		piTable.get(0).add(0.0);
		for(int i=1;i<n;i++){
			piTable.get(0).add(Graph.inf);
			rTable.get(0).add(null);
		}
		int x=0;
		//Étape6
		for(k=1;k<n;k++){
			//Étape 3 À COMPLETER
			for(x=0;x<piTable.get(k).size();x++){
				double minPredeceur=0;
				
				double element = Math.min(piTable.get(k-1).get(x),0  );
				piTable.get(k).set(x, element); 
			}
			//Étape 4 À COMPLETER
			//Étape 5 À COMPLETER
		}
		//Étape 7
	}
	
	public void  diplayShortestPaths() {
		Stack<Node> path=new Stack<Node>();
		// A completer	
		
	}

	public void displayTables() {
		// A completer
		
	}
}
