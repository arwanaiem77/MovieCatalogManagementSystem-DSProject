package application;

import java.util.ArrayList;

public class AVLTree {
	    private AVLNode root;
	    private int count=0;

	    public AVLTree() {
	        root = null;
	    }
	    public int height() {
	        return height(root); 
	    }

	    public int height(AVLNode node) {
//	    	if(root == null)
//	    		return -1;
//	    	else 
//	    		return node.getHeight();
	        return node == null ? -1 : node.getHeight();

	    }

	    public boolean isEmpty() {
	    	if(root==null)
	    		return true;
	    	else 
	    		return false;
	    }
	    private AVLNode rotateWithLeftChild(AVLNode k2) {
	    	AVLNode k1 = k2.getLeft();
	        k2.setLeft(k1.getRight());
	        k1.setRight(k2);

	        k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
	        k1.setHeight(Math.max(height(k1.getLeft()), height(k1.getRight())) + 1);

	        return k1;
	    }

	    private AVLNode rotateWithRightChild(AVLNode k1) {
	    	AVLNode k2 = k1.getRight();
	        k1.setRight(k2.getLeft());
	        k2.setLeft(k1);

	        k1.setHeight(Math.max(height(k1.getLeft()), height(k1.getRight())) + 1);
	        k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);

	        return k2;
	    }
	    
//	    public int cmpMovies(Movie m1,Movie m2) {
//	    	int cmpInt = m1.getTitle().compareTo(m2.getTitle());
//	    	if(cmpInt!=0)
//	    		return cmpInt;
//	    	cmpInt=m1.getReleaseYear()-m2.getReleaseYear();
//	    	
//	    }
	    
	    
	    public void insert(Movie movie) {	
	    	root=insert(movie,root);
	    }
	    
	    private AVLNode insert(Movie movie , AVLNode node) {
	    	if(node == null)
	    		node = new AVLNode(movie);
	    	
	    	int cmpInt = movie.getTitle().compareToIgnoreCase(node.getElement().getTitle());
	    	
	    	if(cmpInt<0) {
	    		node.setLeft(insert(movie,node.getLeft()));
	    	}else if(cmpInt>0) {
	    		node.setRight(insert(movie,node.getRight()));
	    	}else {
	    		 if (!node.getElement().equals(movie)) {  // the object لا يتكرر كله
	    		        node.setRight(insert(movie, node.getRight()));
	    		    }	    	
	    		 }
	    	node.setHeight(Math.max(height(node.getLeft()), height(node.getRight()))+1);
	    	int balance = height(node.getLeft())-height(node.getRight());
	    	
	    	if (balance > 1 ) {
	    		if (movie.getTitle().compareTo(node.getLeft().getElement().getTitle())>0)
	    			return rotateWithLeftChild(node);
	    		else if (movie.getTitle().compareTo(node.getLeft().getElement().getTitle())>0) {
	    			node.setLeft(rotateWithRightChild(node));
	    			return rotateWithLeftChild(node);
	    		}
	    	} else if (balance < -1) {
	    		if (movie.getTitle().compareTo(node.getRight().getElement().getTitle())>0)
	    			return rotateWithRightChild(node);
	    		else if (movie.getTitle().compareTo(node.getRight().getElement().getTitle())>0) {
	    			node.setLeft(rotateWithLeftChild(node));
	    			return rotateWithRightChild(node);
	    		}
	    		
	    	}

	    	count++;
			return node;
	    	
	    }
	    
	    public ArrayList<Movie> get(String title) {
	    	ArrayList<Movie> listTitle = new ArrayList<>();
	    	get(title,root,listTitle);
	    	return listTitle;	    	
	    }
	    private void get(String title , AVLNode node,ArrayList<Movie>listTitle) {
	    	if(node == null)
	    		return ;
	    	
	    		 get(title,node.getLeft(),listTitle);
	    		 
	    		 if(node.getElement().getTitle().equals(title))
	    			 listTitle.add(node.getElement());
	    	
	    		 get(title,node.getRight(),listTitle);

	    }

	    public ArrayList<Movie> getYear(int year) {	  
	    	ArrayList<Movie> listYear = new ArrayList<>();
	    	getYear(year,root,listYear);
	    	return listYear;
	    }

	    private void getYear(int year , AVLNode node,ArrayList<Movie> listYear) {
	    	if(node == null)
	    		return ;
	    	
	    		 getYear(year,node.getLeft(),listYear);
	    		 
	    		 if(node.getElement().getReleaseYear()==year)
	    			 listYear.add(node.getElement());
	    	
	    		 getYear(year,node.getRight(),listYear);
	    }
	    
	    public void delete(String title) {
	        root = delete(title, root);
	    }

	    private AVLNode delete(String title, AVLNode node) {
	        if (node == null)
	            return null;

	        int cmp = title.compareToIgnoreCase(node.getElement().getTitle());

	        if (cmp < 0) {
	            node.setLeft(delete(title, node.getLeft()));
	        } else if (cmp > 0) {
	            node.setRight(delete(title, node.getRight()));
	        } else {
	            if (node.getLeft() == null) {
	                return node.getRight();
	            } else if (node.getRight() == null) {
	                return node.getLeft();
	            } else {
	                AVLNode minRight = findMin(node.getRight());
	                node.setElement(minRight.getElement());
	                node.setRight(delete(minRight.getElement().getTitle(), node.getRight()));
	            }
	        }

	        node.setHeight(1 + Math.max(height(node.getLeft()), height(node.getRight())));

	        int balance = getBalance(node);

	        if (balance > 1) {
	            if (getBalance(node.getLeft()) >= 0) {
	                return rotateWithLeftChild(node);
	            } else {
	                node.setLeft(rotateWithRightChild(node.getLeft())); 
	                return rotateWithLeftChild(node);
	            }
	        }

	        if (balance < -1) {
	            if (getBalance(node.getRight()) <= 0) {
	                return rotateWithRightChild(node); 
	            } else {
	                node.setRight(rotateWithLeftChild(node.getRight()));
	                return rotateWithRightChild(node);
	            }
	        }
	        count--;
      	        return node;
	    }
	    
	    public int size() {
	    	return count;
	    }

	    private AVLNode findMin(AVLNode node) {
	        while (node.getLeft() != null)
	            node = node.getLeft();
	        return node;
	    }

	    private int getBalance(AVLNode node) {
	        if (node == null) 
	        	return 0;
	        return height(node.getLeft()) - height(node.getRight());
	    }
	
	    public ArrayList<Movie> inOrder() {
	    	ArrayList<Movie> list = new ArrayList<>();
	    	inOrder(root,list);
			return list;
	    }
	    
	    private void inOrder(AVLNode curr,ArrayList<Movie>list) {
	    	if(curr!=null) {
	    		inOrder(curr.getLeft(),list);
	    		list.add(curr.getElement());
	    		inOrder(curr.getRight(),list);
	    	}
	    	
	    }

	    
//	    public ArrayList<Movie> getTree(){
//	    	ArrayList<Movie> list =new ArrayList<>();
//	    	getTree(root,list);
//
//			return list;	
//	    }
//	    private void getTree( AVLNode node,ArrayList<Movie>listTitle) {
//	    	if(node == null)
//	    		return ;
//	    	
//	    		 getTree(node.getLeft(),listTitle);
//	    		 
//	    		 listTitle.add(node.getElement());
//	    	
//	    		 getTree(node.getRight(),listTitle);
//
//	    }
	    public void clear() {
	    	root=null;
	    }

}
