package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MovieTableView extends Pane {
	public MovieTableView(Stage primaryStage,Scene oldScene) {
		Button back =new Button("Back");
		back.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
		back.setOnAction(a ->{
			primaryStage.setScene(oldScene);		
			primaryStage.setFullScreen(true);
		});

		BorderPane bp =new BorderPane();
		ComboBox<String> Sorting =new ComboBox<>();

		HBox hb =new HBox(10);
		hb.getChildren().addAll(Sorting,back);
//		ArrayList <Movie> listMovie = new ArrayList<>();
//		for(int i=0;i<Movie.movieList.arr.length;i++) {
//			ArrayList <Movie> list = Movie.movieList.arr[i].inOrder();
//			for(int j=0;j<list.size();j++) {
//				listMovie.add(list.get(j));	
//			}
//		}	
		ArrayList<Movie> listMovie= new ArrayList<>();
		for (int i = 0; i < Movie.movieList.arr.length; i++) {
		    ArrayList<Movie> list = Movie.movieList.arr[i].inOrder();
		    listMovie.addAll(list);
		}

		TableView<Movie> tv = new TableView<Movie>();
		ObservableList<Movie> ov = FXCollections.observableArrayList(listMovie);

		TableColumn<Movie,String> tc1 =new TableColumn<>("Title");
		tc1.setCellValueFactory(new PropertyValueFactory<>("title"));
		TableColumn<Movie,String> tc2 =new TableColumn<>("Description");
		tc2.setCellValueFactory(new PropertyValueFactory<>("description"));
		TableColumn<Movie,Integer> tc3 =new TableColumn<>("Release Year");
		tc3.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
		TableColumn<Movie,Double> tc4 =new TableColumn<>("Rating");
		tc4.setCellValueFactory(new PropertyValueFactory<>("rating"));


		tv.getColumns().addAll(tc1, tc2, tc3, tc4);
		tv.setItems(ov);

		bp.setCenter(tv);
		bp.setTop(hb);

		

		Sorting.getItems().addAll("Ascending", "Descending");
		Sorting.setOnAction(e -> {
			String selected = Sorting.getSelectionModel().getSelectedItem();
			ov.clear();
			if (selected.equals("Ascending")) {
		        Collections.sort(listMovie, new Comparator<Movie>() {
		            @Override
		            public int compare(Movie m1, Movie m2) {
		                return m1.getTitle().compareTo(m2.getTitle());
		            }
		        });
		    } else if (selected.equals("Descending")) {
		        Collections.sort(listMovie, new Comparator<Movie>() {
		            @Override
		            public int compare(Movie m1, Movie m2) {
		                return m2.getTitle().compareTo(m1.getTitle());
		            }
		        });
		    }

		    ObservableList<Movie> sortedList = FXCollections.observableArrayList(listMovie);
		    tv.setItems(sortedList);
		});

		

		Scene scene =new Scene(bp,1100,700);
		primaryStage.setScene(scene);		
		primaryStage.setFullScreen(true);
		primaryStage.show();
	
	}

}