package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UpdateMovie extends Pane{

	public UpdateMovie(Stage primaryStage,Scene oldScene) {
	
		
		GridPane gp =new GridPane();
		gp.setPadding(new Insets(200,50,50,50));
		TextField titleTf =new TextField();
		TextField descTf =new TextField();
		TextField releaseYearTf =new TextField();
		TextField ratingTf =new TextField();

		Label titleLabel = new Label("Title:");
		titleLabel.setFont(new Font(20));
		Label descLabel = new Label("Description");
		descLabel.setFont(new Font(20));
		Label releaseYearLabel = new Label("releaseYear");
		releaseYearLabel.setFont(new Font(20));
		Label ratingLabel = new Label("rating");
		ratingLabel.setFont(new Font(20));



		gp.add(titleLabel, 0, 0);
		gp.add(titleTf, 1, 0);
		gp.add(descLabel, 0, 1);
		gp.add(descTf, 1, 1);
		gp.add(releaseYearLabel, 0, 2);
		gp.add(releaseYearTf, 1, 2);
		gp.add(ratingLabel, 0, 3);
		gp.add(ratingTf, 1,3);
		
		HBox hbox =new HBox(7);
		TextField tf =new TextField();
		Button edit =new Button("Edit");
		Label l=new Label("Enter Thie BookID:");
		l.setFont(new Font(20));
		hbox.getChildren().addAll(l,tf,edit);
		edit.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

		edit.setOnAction(a ->{
			try {
			ArrayList<Movie> list = new ArrayList<>();
			Movie movie=new Movie();
			list = Movie.movieList.get(tf.getText());
//			System.out.println("LIST***********");
//			for(int i=0;i<list.size();i++) {
//				System.out.println(list.get(i));
//			}
			movie=list.get(0);
			titleTf.setText(movie.getTitle());
			descTf.setText(movie.getDescription());
			releaseYearTf.setText(String.valueOf(movie.getReleaseYear()));
			ratingTf.setText(String.valueOf(movie.getRating()));
			}catch(Exception ex) {
				
			}

		});
		
		VBox vb1 =new VBox(200);
		vb1.getChildren().addAll(gp,hbox);
		
		VBox vb =new VBox(100);
		//ImageView img =new ImageView (new Image("library.JPG"));
//		img.setFitWidth(600);
//		img.setFitHeight(600);
		
		Button back =new Button("Back");
    	back.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
		back.setOnAction(a ->{
			primaryStage.setScene(oldScene);		
			primaryStage.setFullScreen(true);
		});

		Button saveEditing = new Button("Save Edit");
		saveEditing.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

		saveEditing.setOnAction(a ->{
			ArrayList<Movie> list = new ArrayList<>();
			Movie movie=new Movie();
			list = Movie.movieList.get(tf.getText());
//			System.out.println("LIST***********");
//			for(int i=0;i<list.size();i++) {
//				System.out.println(list.get(i));
//			}
			movie=list.get(0);
			if(!movie.getTitle().equalsIgnoreCase(titleTf.getText())) {
				Movie.movieList.delete(movie);
				Movie newMovie = new Movie(titleTf.getText(),descTf.getText(),Integer.parseInt(releaseYearTf.getText()),
						Double.parseDouble( ratingTf.getText()));
				Movie.movieList.insert(newMovie);
			}else {
				movie.setDescription(descTf.getText());
				movie.setReleaseYear(Integer.parseInt(releaseYearTf.getText()));
				movie.setRating(Double.parseDouble( ratingTf.getText()));
			}


			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("SUCCESS!");
			alert.setContentText("The information edited successfully");
			alert.showAndWait();

			descTf.clear();
			tf.clear();
			titleTf.clear();
			releaseYearTf.clear();
			ratingTf.clear();
			
		});
		
		HBox hb1 =new HBox(20);
		hb1.getChildren().addAll(back,saveEditing);
		
		vb.getChildren().addAll(hb1);

		HBox hb =new HBox(300);
		hb.setPadding(new Insets(50,50,50,50));
		hb.getChildren().addAll(vb,vb1);
		hb.setStyle("-fx-background-color: #F8EED9;");
		
		Scene scene =new Scene(hb,1100,700);
		primaryStage.setScene(scene);		
		primaryStage.setFullScreen(true);
		primaryStage.show();




	}


}