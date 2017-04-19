import java.util.ArrayList;
import java.util.List;
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
		// etape 1
		int k=1;
		int n=graph.getNodes().size();
		//Première ligne du piTable et RTable
		piTable.add(0, new Vector<Double>());
		rTable.add(0, new Vector<Integer>());
		//etape 2
		for(int i=0;i<n;i++){
			piTable.get(0).add(Graph.inf);
			rTable.get(0).add(null);
		}
		piTable.get(0).set(sourceNode.getId(),0.0);
		boolean allEqual = true;
		do{
			piTable.add(k, new Vector<Double>());
			rTable.add(k, new Vector<Integer>());

			for(int x=0;x<n;x++){
				//etape 3
				double minPredeceur=Graph.inf;
				List<Edge> nNeg = graph.getInEdges(graph.getNodes().get(x));
				Edge yMin = null;
				for(Edge y : nNeg){
					if(piTable.get(k-1).get(y.getSource().getId()) != Graph.inf && piTable.get(k-1).get(y.getSource().getId()) + y.getDistance() < minPredeceur){
						minPredeceur = piTable.get(k-1).get(y.getSource().getId())+y.getDistance();
						yMin = y;
					}
				}
				double element = Math.min(piTable.get(k-1).get(x), minPredeceur);
				piTable.get(k).add(element); 
				//etape 4
				if(piTable.get(k-1).get(x) >= minPredeceur && yMin != null && x != sourceNode.getId()){
					rTable.get(k).add(yMin.getSource().getId());
				}else{
					rTable.get(k).add(null);
				}
			}
			//etape 5
			allEqual = piTable.get(k).equals(piTable.get(k-1));
			//etape 7
			if(k == n)
				return;//circuit negatif
			//etape 6
			if(k < n)
			k++;
		}while(!allEqual);
		
		
	}
	
	public void  diplayShortestPaths() {
		Stack<Node> path=new Stack<Node>();
		// A completer	
		
		int n =graph.getNodes().size();
		//Regarder s'il y a des chemins négatifs
		if(piTable.size() == n+1){//negatif (k = n)

			System.out.println("==> le graphe contient un circuit de coût négatif :\n");
			boolean circuit = false;
			for(int x = 0; x < piTable.get(n).size(); x++){
				if(piTable.get(n).get(x) < 0){
					path.push(graph.getNodes().get(x));
					while(path.peek() != sourceNode){
						path.push(graph.getNodes().get(rTable.get(n).get(path.peek().getId())));
						if(path.peek() == path.firstElement()){//tester si on a un circuit
							circuit = true;
							break;
						}
					}
					if(!circuit)
						path.clear();//vider le path pour recommencer si on a pas trouve le circuit
				}
				if(circuit)//Stop si on a un circuit
					break;
			}
			System.out.print("["+path.peek().getName()+" - "+path.peek().getName()+"] :");
			System.out.print(" "+path.pop().getName());
			while(!path.isEmpty()){
				System.out.print(" -> "+path.pop().getName());
			}
		}else{
			n=piTable.size()-1;
			System.out.println("=> les chemins sont :\n");
			for(int x=0;x<piTable.get(n).size();x++){
				if(x != sourceNode.getId()){
					path.push(graph.getNodes().get(x));
					while(path.peek() != sourceNode){
						path.push(graph.getNodes().get(rTable.get(n).get(path.peek().getId())));
					}
					System.out.print("["+path.peek().getName()+" - "+path.firstElement().getName()+"] "+piTable.get(n).get(x)+ " :");
					System.out.print(" "+path.pop().getName());
					while(!path.isEmpty()){
						System.out.print(" -> "+path.pop().getName());
					}
					System.out.println();
				}
				
			}
		}
		
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
		System.out.println();
	}
}
