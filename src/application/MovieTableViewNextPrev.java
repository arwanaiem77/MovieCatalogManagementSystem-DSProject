package application;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MovieTableViewNextPrev extends Pane {
	int j=0;
	public MovieTableViewNextPrev(Stage primaryStage,Scene oldScene) {
		Button back =new Button("Back");
		back.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
		back.setOnAction(a ->{
			primaryStage.setScene(oldScene);		
			primaryStage.setFullScreen(true);
		});
		Button next =new Button("Next");
		next.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
		Button prev =new Button("Previous");
		prev.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

		BorderPane bp =new BorderPane();

		HBox hb =new HBox(10);
		hb.getChildren().addAll(next,prev,back);
//		ArrayList <Movie> listMovie = new ArrayList<>();
//		for(int i=0;i<Movie.movieList.arr.length;i++) {
//			ArrayList <Movie> list = Movie.movieList.arr[i].inOrder();
//			for(int j=0;j<list.size();j++) {
//				listMovie.add(list.get(j));	
//			}
//		}	
		ArrayList<Movie> listMovieAscending = new ArrayList<>();
		for (int i = 0; i < Movie.movieList.arr.length; i++) {
		    ArrayList<Movie> list = Movie.movieList.arr[i].inOrder();
		    listMovieAscending.addAll(list);
		}
		System.out.println("Total movies collected: " + listMovieAscending.size());

		TableView<Movie> tv = new TableView<Movie>();
		ObservableList<Movie> ov = FXCollections.observableArrayList();

		TableColumn<Movie,String> tc1 =new TableColumn<>("Title");
		tc1.setCellValueFactory(new PropertyValueFactory<>("title"));
		TableColumn<Movie,String> tc2 =new TableColumn<>("Description");
		tc2.setCellValueFactory(new PropertyValueFactory<>("description"));
		TableColumn<Movie,Integer> tc3 =new TableColumn<>("Release Year");
		tc3.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
		TableColumn<Movie,Double> tc4 =new TableColumn<>("Rating");
		tc4.setCellValueFactory(new PropertyValueFactory<>("rating"));


		tv.getColumns().addAll(tc1, tc2, tc3, tc4);
		//tv.setItems(ov);

		bp.setCenter(tv);
		bp.setTop(hb);

		ObservableList<Movie> ov1 = FXCollections.observableArrayList();

		ArrayList<Movie> list = Movie.movieList.arr[0].inOrder();
		if (list != null) {
		    ov1.addAll(list);
		}		
		tv.setItems(ov1);

		next.setOnAction(a ->{
			try {
			if(j!=Movie.movieList.arr.length) {
				ObservableList<Movie> ov2 = FXCollections.observableArrayList();
				ArrayList<Movie> list1 = Movie.movieList.arr[++j].inOrder();
				if (list1 != null) {
				    ov2.addAll(list1);
				}
				tv.setItems(ov2);
			}
			}catch(Exception ex) {

			}

		});
		prev.setOnAction(a ->{
			try {

		
				if(j!=0) {
					ObservableList<Movie> ov2 = FXCollections.observableArrayList();
					ArrayList<Movie> list1 = Movie.movieList.arr[--j].inOrder();
					if (list1 != null) {
						ov2.addAll(list1);
					}
					tv.setItems(ov2);
				}
			}catch(Exception ex) {

			}
		});






		Scene scene =new Scene(bp,1100,700);
		primaryStage.setScene(scene);		
		primaryStage.setFullScreen(true);
		primaryStage.show();
	
	}
}