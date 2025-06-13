package application;

public class AVLNode {
	private Movie element;
	private AVLNode right;
	private AVLNode left;
	private int height;
	
	public AVLNode(Movie element) {
		this(element,null,null);
	}
	public AVLNode(Movie element, AVLNode right, AVLNode left) {
		this.element = element;
		this.right = right;
		this.left = left;
		this.height = 0;
	}
	public Movie getElement() {
		return element;
	}
	public void setElement(Movie element) {
		this.element = element;
	}
	public AVLNode getRight() {
		return right;
	}
	public void setRight(AVLNode right) {
		this.right = right;
	}
	public AVLNode getLeft() {
		return left;
	}
	public void setLeft(AVLNode left) {
		this.left = left;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
		
	}
	



}
