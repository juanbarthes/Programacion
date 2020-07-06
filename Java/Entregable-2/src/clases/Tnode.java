package clases;

public class Tnode {
	private int elem;
	private Tnode left;
	private Tnode right;
	public int getElem() {
		return elem;
	}
	public void setElem(int elem) {
		this.elem = elem;
	}
	public Tnode getLeft() {
		return left;
	}
	public void setLeft(Tnode left) {
		this.left = left;
	}
	public Tnode getRight() {
		return right;
	}
	public void setRight(Tnode right) {
		this.right = right;
	}
	public Tnode(int elem) {
		super();
		this.elem = elem;
		this.left = null;
		this.right = null;
	}
	public void print() {
		System.out.println(this.getElem());
	}
	
	protected boolean isLeaf() {
		if ((this.getLeft() == null)&&(this.getRight() == null))
			return true;
		else 
			return false;
	}
	
}
