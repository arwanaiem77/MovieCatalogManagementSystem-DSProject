package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DeleteMovie extends Pane{

	public DeleteMovie(Stage primaryStage,Scene oldScene) {
		Button back =new Button("Back");
		back.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

		back.setOnAction(a ->{
			primaryStage.setScene(oldScene);		
			primaryStage.setFullScreen(true);
		});
		
		BorderPane bp =new BorderPane();
		Label l =new Label(" Title : ");
		l.setFont(new Font(20));
		TextField tf =new TextField();
		Button delete =new Button("Delete");
		delete.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

		HBox hb =new HBox(10);
		hb.getChildren().addAll(l,tf,delete,back);
		
		ArrayList<Movie> listMovieAscending = new ArrayList<>();
		for (int i = 0; i < Movie.movieList.arr.length; i++) {
		    ArrayList<Movie> list = Movie.movieList.arr[i].inOrder();
		    listMovieAscending.addAll(list);
		}
		System.out.println("Total movies collected: " + listMovieAscending.size());

		TableView<Movie> tv = new TableView<Movie>();
		ObservableList<Movie> ov = FXCollections.observableArrayList(listMovieAscending);

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
		bp.setStyle("-fx-background-color: #F8EED9;");

		delete.setOnAction(a ->{
			try {
			ArrayList<Movie> list = new ArrayList<>();
			Movie movie=new Movie();
			list = Movie.movieList.get(tf.getText());
//			System.out.println("LIST***********");
//			for(int i=0;i<list.size();i++) {
//				System.out.println(list.get(i));
//			}
			movie=list.get(0);
				if(movie!=null) {
				Movie.movieList.delete(movie);
				ov.remove(movie);
				   Alert alert = new Alert(Alert.AlertType.INFORMATION);
			        alert.setTitle("Deleted");
			        alert.setContentText("Movie deleted successfully.");
			        alert.showAndWait();
				}else {
				    Alert alert = new Alert(Alert.AlertType.WARNING);
			        alert.setTitle("Not Found");
			        alert.setContentText("No movie found with the given title.");
			        alert.showAndWait();
				}

			
			tv.setItems(ov);
			tv.refresh();
			}catch(Exception ex) {
				
			}
			});
		
		Scene scene =new Scene(bp,1100,700);
		primaryStage.setScene(scene);		
		primaryStage.setFullScreen(true);
		primaryStage.show();
		
	}
	

}
