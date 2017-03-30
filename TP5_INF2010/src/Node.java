
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maitr
 */
public class Node {

    public int ordre;
    public Node parent;

    private int valeur;
    private ArrayList<Node> enfants;

    public Node(int valeur) {
        this.valeur = valeur;
        ordre = 0;
        this.parent = null;
        enfants = new ArrayList<Node>();
    }

    public Node(int valeur, Node parent) {
        ordre = 0;
        this.valeur = valeur;
        this.parent = parent;
        enfants = new ArrayList<Node>();
    }

    public int getVal() {
        return valeur;
    }

    public ArrayList<Node> getEnfants() {
        return enfants;
    }

    public void addEnfant(Node enfant) {
        enfants.add(enfant);
    }

    public boolean removeEnfant(Node enfant) {
        if (enfants.contains(enfant)) {
            enfants.remove(enfant);
            return true;
        }
        return false;
    }

    public void removeEnfants(ArrayList<Node> enfants) {
        this.enfants.removeAll(enfants);
    }

    public Node fusion(Node autre) throws DifferentOrderTrees {
    	// à compléter


    	if(this.ordre == autre.ordre){
    		if(this.parent == null && autre.parent == null){
    			if(this.valeur < autre.valeur){
    				this.addEnfant(autre);
    				this.ordre ++;
    				return this;
    			}	
    			else{
    				autre.addEnfant(this);
    				autre.ordre++;
    				return autre;
    			}
    		}
    	}else
    		throw new DifferentOrderTrees();


        return null;
    }

    private void moveUp() {
    	// à compléter
    	int temp;
    	temp = this.valeur;
    	this.valeur = parent.getVal();
    	parent.valeur = temp;
    }

    public ArrayList<Node> delete() {
        // à compléter
        while(parent != null){
        	moveUp();
        }
        for(int i=0;i<enfants.size();i++){
        	enfants.get(i).parent = null;
        }
    	return enfants;
    }

    public void print(String tabulation) {
        // à compléter
    	String retour="";
    	
    }
    
    public Node findValue(int valeur) {
        return findValue(this, valeur);
    }
    
    public Node findValue (Node n, int valeur) {
        // à compléter
    	if(!enfants.isEmpty())
    		for(int i=0;i<enfants.size();i++){
    			if(enfants.get(i).getVal() == valeur){
    				return enfants.get(i);
    			}else
    				if (enfants.get(i).getVal() > valeur){
    					return findValue(enfants.get(i),valeur);
    				}
    		}
    	return null;
    }
    
}
