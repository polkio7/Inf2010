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
		piTable = new ArrayList<Vector<Double>>();
		rTable = new ArrayList<Vector<Integer>>();
	}
	
	public void setSourceNode(Node source) {
		this.sourceNode = source;
	}
	
	public void shortestPath() {
		// completer
		int k=1;
		int n=graph.getNodes().size();
		//Première ligne du piTable et RTable
		piTable.add(0, new Vector<Double>());
		rTable.add(0, new Vector<Integer>());
		for(int i=0;i<n;i++){
			piTable.get(0).add(Graph.inf);
			rTable.get(0).add(null);
		}
		piTable.get(0).set(sourceNode.getId(),0.0);
		boolean allEqual = true;
		//Étape6
		do{
			piTable.add(k, new Vector<Double>());
			rTable.add(k, new Vector<Integer>());
			//Étape 3 À COMPLETER
			
			for(int x=0;x<n;x++){
				double minPredeceur=Graph.inf;
				List<Edge> nNeg = graph.getInEdges(graph.getNodes().get(x));
				Edge yMin = null;
				for(Edge y : nNeg){
					if(piTable.get(k-1).get(y.getSource().getId()) + y.getDistance() < minPredeceur){
						minPredeceur = piTable.get(k-1).get(y.getSource().getId())+y.getDistance();
						yMin = y;
					}
				}
				double element = Math.min(piTable.get(k-1).get(x), minPredeceur);
				piTable.get(k).add(element); 
				if(piTable.get(k-1).get(x) >= minPredeceur && yMin != null && piTable.get(k-1).get(x) != 0){
					rTable.get(k).add(yMin.getSource().getId());
				}else{
					rTable.get(k).add(null);
				}
			}
			allEqual = piTable.get(k).equals(piTable.get(k-1));
			if(k == n)
				return;//circuit negatif
			if(k < n)
			k++;
		}while(!allEqual);
		
		
	}
	
	public void  diplayShortestPaths() {
		Stack<Node> path=new Stack<Node>();
		// A completer	
		
	}

	public void displayTables() {
		// A completer
		//piTable
		System.out.println("<< PITable >>:");
		System.out.print("k :");
		for(Node node: graph.getNodes()){
			System.out.print("  "+node.getName()+"  ");
			
		}
		System.out.println();
		for(int k = 0; k < piTable.size(); k++){
			System.out.print(k+" :");
			for(int x = 0; x < piTable.get(k).size(); x++){
				if(piTable.get(k).get(x) != Graph.inf){
					System.out.print("  "+piTable.get(k).get(x));
				}else{
					System.out.print("  inf");
				}
			}
			System.out.println();
		}
		System.out.println();
		// rTable
		System.out.println("<< RTable >>:");
		System.out.print("k :");
		for (Node node : graph.getNodes()) {
			System.out.print("  " + node.getName() + "  ");

		}
		System.out.println();
		for (int k = 0; k < rTable.size(); k++) {
			System.out.print(k + " :");
			for (int x = 0; x < rTable.get(k).size(); x++) {
				if (rTable.get(k).get(x) != null) {
					System.out.print("  " + graph.getNodes().get(rTable.get(k).get(x)).getName()+"  ");
				} else {
					System.out.print("  -  ");
				}
			}
			System.out.println();
		}
	}
}
