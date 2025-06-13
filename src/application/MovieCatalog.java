package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MovieCatalog {
	AVLTree[] arr ;

	public MovieCatalog(int hashSize) {
		arr =new AVLTree[hashSize];
		for(int i=0;i<arr.length;i++){
			arr[i]=new AVLTree();
		}
	}
	
	public long hash(String key,int hashSize) {
		long hashValue=0;
		int i=0;
		
		while(i<key.length()) {
			hashValue = (hashValue << 5)+key.charAt(i++);
		}

	    int index = (int)(hashValue % hashSize);
	    if (index < 0) index += hashSize;
	    return index;	
	    }
	
	
	
	public void insert(Movie movie) {
		double totalHeight = 0;
		int nonEmptyTrees = 0;

		for (int i = 0; i < arr.length; i++) {
		    if (!arr[i].isEmpty()) {
		        totalHeight += arr[i].height();
		        nonEmptyTrees++;
		    }
		}

		double avgHeight = totalHeight / nonEmptyTrees;

		if (avgHeight > 3) {
		    rehash();
		}
		int index=(int)(hash(movie.getTitle(),arr.length));
		arr[index].insert(movie);
		
		
		
	}
	
	public void delete(Movie movie) {
		int index=(int)(hash(movie.getTitle(),arr.length));
		arr[index].delete(movie.getTitle());
	}
	
	public ArrayList<Movie> get(String title) {
		ArrayList<Movie> listTitle = new ArrayList<>();
		for(int i=0;i<arr.length;i++) {
			ArrayList<Movie> listy = arr[i].get(title);
			for(int j=0;j<listy.size();j++) {
				listTitle.add(listy.get(j));
			}
			
		}
		return listTitle;
//		int index = (int)(hash(title, arr.length));
//		return arr[index].get(title);
	}
	
	public ArrayList<Movie> getYear(int year) {
		ArrayList<Movie> listYear = new ArrayList<>();
		for(int i=0;i<arr.length;i++) {
			ArrayList<Movie> listy = arr[i].getYear(year);
			for(int j=0;j<listy.size();j++) {
				listYear.add(listy.get(j));
			}
			
		}
		return listYear;
		
	}
	
	
	
	public int allocate() {
	    int total = 0;
	    for (AVLTree tree : arr) {
	        total += tree.size();
	    }
	    return total;
	}
	
	public void saveMovieToFile() {
		File file =new File("movieFile");
		try {
			PrintWriter print = new PrintWriter(file);
			for(int i=0;i<arr.length;i++) {
				ArrayList <Movie> list = arr[i].inOrder();
				for(int j=0;j<list.size();j++) {
					print.println(list.get(j).toString());
				}
			}
			print.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMoviesFromFile() {
		File file =new File("moviee");
		try {
			Scanner scan = new Scanner(file);
			while(scan.hasNextLine()) {
				String[] str = scan.nextLine().split(",");
				Movie movie =new Movie(str[0].trim(),str[1].trim(),Integer.parseInt(str[2]),Double.parseDouble(str[3]));
				Movie.movieList.insert(movie);
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("SUCCESS!");
			alert.setContentText("The Movie added successfully");
			alert.showAndWait();
		} catch (FileNotFoundException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();		}
	}
	
	public void  deallocate() {
		for(int i=0;i<arr.length;i++) {
			arr[i].clear();
		}
		
	}
	
	public int heightTest() {
		int totalHeight=0;
		int count=0;
		for(int i=0;i<arr.length;i++) {
			totalHeight+=arr[i].height();
			count++;
		}
		return totalHeight/count;
	}
	
	public void rehash() {
		System.out.println("*************");
		ArrayList <Movie> listMovie = new ArrayList<>();
		for(int i=0;i<arr.length;i++) {
			ArrayList <Movie> list = arr[i].inOrder();
			for(int j=0;j<list.size();j++) {
				listMovie.add(list.get(j));	
			}
		}
		int oldSize=arr.length;
		boolean found=false;
		int count = oldSize*2;
		int newSizee=0;
		while(found==false) {
			newSizee=count;
			found=isPrime(++count);	
		}
		arr=new AVLTree[newSizee];
		
		for (int i = 0; i < arr.length; i++) {
		    arr[i] = new AVLTree();
		}
		for (int i =0;i<listMovie.size();i++) {
			insert(listMovie.get(i));
		}
		System.out.println("After rehashing:");
		for (int i = 0; i < arr.length; i++) {
		    System.out.println("Index " + i + " contains " + arr[i].inOrder().size() + " movies.");
		}

	
		
	}
	private boolean isPrime(int num) {
	    if (num <= 1) return false;
	    if (num == 2) return true;
	    if (num % 2 == 0) return false;
	    for (int i = 3; i <= Math.sqrt(num); i += 2) {
	        if (num % i == 0)
	            return false;
	    }
	    return true;
	}
//	 public ArrayList<Movie> getTreeAt(int index) {
//		 ArrayList<Movie> list =new ArrayList<>();
//	        if (index >= 0 && index < arr.length) {
//	            for(int i=0;i<arr[index].size();i++) {
//	            	list=arr[index].getTree();
//	            }
//	        }
//	        return null;
//	    }
	 
//	 public void top() {
//		 
//	 public ArrayList<Movie> getTreeAt(int index) {
//		 ArrayList<Movie> list =new ArrayList<>();
//	        if (index >= 0 && index < arr.length) {
//	            for(int i=0;i<arr[index].size();i++) {
//	            	list=arr[index].getTree();
//	            }
//	        }
//	        return null;
//	    }
	 
//	 public void top() {
//		 
//	 }

	//
//	public void reHash() {
//        Node[] oldHash = hash;
//        int oldSize = size;
//
//        size = getReHashPrime();
//        hash = new Node[size];
//
//        for (int i = 0; i < size; i++) {
//            hash[i] = new Node();
//            hash[i].setStatus(empty);
//        }
//
//        for (int i = 0; i < oldSize; i++) {
//            if (oldHash[i].getStatus() == visited) {
//                insert(oldHash[i].getKey(), oldHash[i].getData());
//            }
//        }
//    }
//
//
//private int getReHashPrime() {
//        int p = size * 2;
//
//        while (!isPrime(p))
//            p++;
//
//        return p;
//    }
//
//    private boolean isPrime(int n) {
//        if (n <= 1)
//            return false;
//
//        for (int i = 2; i < n; i++)
//            if (n % i == 0)
//                return false;
//
//        return true;
//    }

}
