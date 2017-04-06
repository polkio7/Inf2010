import java.util.ArrayList;

public class NodeTest {

	public static void main(String[] args) throws DifferentOrderTrees {
		// TODO Auto-generated method stub
		
		Node a = new Node(3);
		Node b = new Node(4);
		Node c = new Node(5);
		Node e = new Node(9);
		Node d = new Node(7);
		Node f = new Node(8);
		Node g = new Node(12);
		Node h = new Node(13);
		
		a.fusion(b);
		c.fusion(e);
		a.fusion(c);
		
		d.fusion(f);
		g.fusion(h);
		d.fusion(g);
		
		a.fusion(d);
		
		a.print("");
		
		g.delete();
		
		a.print("");
		
	}

}
