
public class BinaryTree<AnyType extends Comparable<? super AnyType>> {
	private Node<AnyType> root = null; // Racine de l'arbre

	// insert element in arbre 
	public void insert (AnyType elem) {
	        if(root==null){
	        	this.root= new Node<AnyType>(elem);
	        }else	
	        	insert(root, elem);      
	}
	
	
	
	@SuppressWarnings("unchecked")
	private void insert(Node<AnyType> node, AnyType elem) {
		// A completer
		if(elem.compareTo(node.val) > 0) {
			if(node.right == null)
				node.right = new Node<AnyType>(elem);
			else
				insert(node.right,elem);
		}else if (elem.compareTo(node.val) < 0){
			if(node.left == null)
				node.left = new Node<AnyType>(elem);
			else
				insert(node.left,elem);
		}
	}
    
	
	public int getHauteur () {
		return this.getHauteur(this.root);
	}
 
	public String printPrefixe() {
		return "{ " + this.printPrefixe(this.root) + " }";
	}
	public String printInFixe() {
		return "{ " + this.printInfixe(this.root) + " }";
	}
	
	public String printPostFixe() {
		return "{ " + this.printPostfixe(this.root) + " }";
	}
	
	private int getHauteur(Node<AnyType> tree) {
		// A completer 
		if(tree == null)
			return -1;
		else
			return 1+ Math.max(getHauteur(tree.left), getHauteur(tree.right));
	}	
	
	@SuppressWarnings("unchecked")
	private String printPrefixe(Node<AnyType> node) {
		// COMPLETER
		String s="";
		if(node != null)
		{
			s += node.val.toString() + ", ";
			s += printPrefixe(node.left);
			s += printPrefixe(node.right);
		}
		return s;
	}

	@SuppressWarnings("unchecked")
	private String printInfixe(Node<AnyType> node) {
		// COMPLETER
		String s="";
		if(node != null)
		{
			s += printInfixe(node.left);
			s += node.val.toString() + ", ";
			s += printInfixe(node.right);
		}
		return s;
	}
	
	@SuppressWarnings("unchecked")
	private String printPostfixe(Node<AnyType> node) {
		// COMPLETER
		String s="";
		if(node != null)
		{
			s += printPostfixe(node.left);
			s += printPostfixe(node.right);
			s += node.val.toString() + ", ";
		}
		return s;
	}
	
	private static class Node<AnyType> implements Comparable{
		AnyType val; // Valeur du noeud
		Node right; // fils droite
		Node left; // fils gauche

		public Node (AnyType val) {
			this.val = val;
			right = null;
			left = null;
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			return 0;
		}
	}


}

