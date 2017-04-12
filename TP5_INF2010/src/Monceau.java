
import java.util.ArrayList;

public class Monceau {
    ArrayList<Node> arbres;
    
    public Monceau() {
        arbres = new ArrayList<Node>();
    }
    
    public void fusion(Monceau autre) {
        // à compléter
    	int max = 0;
    	for(Node arbre:arbres){
    		max = Math.max(max, arbre.ordre);
    	}
    	for(Node arbre:autre.arbres){
    		max = Math.max(max, arbre.ordre);
    	}
    	Node retenue = null;
    	ArrayList<Node> monceauFinal = new ArrayList<Node>();
    	
    	for(int j = 0; j <= max; j++){
    		ArrayList<Node> arbresTrouves = new ArrayList<Node>();
    		for(Node arbre:arbres){
    			if(arbre.ordre == j){
    				arbresTrouves.add(arbre);
    				arbres.remove(arbre);
    				break;
    			}
    		}
    		for(Node autreArbre : autre.arbres){
    			if(autreArbre.ordre == j){
    				arbresTrouves.add(autreArbre);
    				autre.arbres.remove(autreArbre);
    				break;
    			}
    		}
    		if(retenue != null && retenue.ordre == j){
    			arbresTrouves.add(retenue);
    		}
    		
    		switch(arbresTrouves.size()){
    		case 1:{
    			monceauFinal.add(arbresTrouves.get(0));
    			break;
    		}
    		case 2:{
    			try {
					retenue = arbresTrouves.get(0).fusion(arbresTrouves.get(1));
					max = Math.max(max, retenue.ordre);
				} catch (DifferentOrderTrees e) {
					e.printStackTrace();
				}
    			break;
    		}
    		case 3:{
    			try {
					retenue = arbresTrouves.get(0).fusion(arbresTrouves.get(1));
					max = Math.max(max, retenue.ordre);
				} catch (DifferentOrderTrees e) {
					e.printStackTrace();
				}
    			monceauFinal.add(arbresTrouves.get(2));
    			break;
    		}
    		default:
    			//do nothing
    			break;
    		}
    	
    		
    	}
    	this.arbres = monceauFinal;
    }
    
    public void insert(int val) {
        // à compléter
    	Monceau insertion = new Monceau();
    	insertion.arbres.add(new Node(val));
    	this.fusion(insertion);
    }
    
    public boolean delete (int val) {
        // à compléter
    	boolean wasDeleted = false;
    	
        Node nodeToDelete = null;
        Node rootToDelete = null;
        do{
        	for(Node arbre : arbres){
        		nodeToDelete = arbre.findValue(val);
        		if(nodeToDelete != null){
        			rootToDelete = arbre;
        			break;
        		}
        	}
        	if(nodeToDelete != null){
        		Monceau newTree = new Monceau();
        		newTree.arbres = nodeToDelete.delete();
        		arbres.remove(rootToDelete);
        		this.fusion(newTree);
        		wasDeleted = true;
        	}
        }while(nodeToDelete != null);
        return wasDeleted;
    }
    
    public void print() {
        // à compléter
    	System.out.println("Monceau : ");
    	for(int i = 0;i < arbres.size(); i++){
    		System.out.println("arbre d'ordre "+arbres.get(i).ordre + " : ");
    		arbres.get(i).print("");
    		System.out.println();
    	}
    }
    
}
