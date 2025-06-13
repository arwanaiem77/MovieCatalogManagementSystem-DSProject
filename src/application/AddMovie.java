package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddMovie extends Pane {

	public AddMovie(Stage primaryStage,Scene oldScene) {
		try {

			Button readFile =new Button("Read from file");
			readFile.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

			readFile.setOnAction(a ->{

			//	FileChooser file =new FileChooser();	
				File c = new File("moviee");
						//file.showOpenDialog(primaryStage);
				if (c != null) {
					int countLines=0;
					try  {
						Scanner scan = new Scanner(c);
						while (scan.hasNextLine()) {
							String [] str = scan.nextLine().split(",");
								
								Movie movie =new Movie(str[0].trim(),str[1].trim(),Integer.parseInt(str[2]),Double.parseDouble(str[3]));
								
								Movie.movieList.insert(movie);
								System.out.println("Inserted: " + movie.getTitle() + " to index: " + (int)(Movie.movieList.hash(movie.getTitle(), 5)));

								countLines++;
						
						}Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("SUCCESS!");
						alert.setContentText("The Movie added successfully");
						alert.showAndWait();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});

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

			VBox vb1 =new VBox(20);
			vb1.getChildren().addAll(gp,readFile);

			VBox vb =new VBox(100);
			//ImageView img =new ImageView (new Image("library.JPG"));
//			img.setFitWidth(600);
//			img.setFitHeight(600);

			Button back =new Button("Back");
			back.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

			back.setOnAction(a ->{
				primaryStage.setScene(oldScene);		
				primaryStage.setFullScreen(true);
			});

			Button add = new Button("Add");
			add.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

			add.setOnAction(a ->{
				if(titleTf.getText().isEmpty()||descTf.getText().isEmpty()||releaseYearTf.getText().isEmpty()||ratingTf.getText().isEmpty()) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setContentText("Please add all the information");
					alert.showAndWait();

				}
				else {

					Movie movie =new Movie(titleTf.getText(),descTf.getText(),Integer.parseInt(releaseYearTf.getText()),Double.parseDouble(ratingTf.getText()));
					Movie.movieList.insert(movie);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("SUCCESS!");
					alert.setContentText("The book added successfully");
					alert.showAndWait();
					System.out.println("Inserted: " + movie.getTitle() + " to index: " + (int)(Movie.movieList.hash(movie.getTitle(), 17)));

					titleTf.clear();
					descTf.clear();
					releaseYearTf.clear();
					ratingTf.clear();

				}
			});

			HBox hb1 =new HBox(30);

			Button tv =new Button("Table View");
			tv.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

			hb1.getChildren().addAll(back,add,tv);



			vb.getChildren().addAll(hb1);

			HBox hb =new HBox(300);
			hb.setPadding(new Insets(50,50,50,50));
			hb.getChildren().addAll(vb,vb1);
			hb.setStyle("-fx-background-color: #F8EED9;");

			Scene scene =new Scene(hb,1100,700);
			primaryStage.setScene(scene);		
			primaryStage.setFullScreen(true);
			primaryStage.show();

			tv.setOnAction(a ->{
					Scene scenee =new Scene(new MovieTableView(primaryStage,scene),1100,700);

			});
		}catch(Exception ex) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText(ex.getMessage());
			alert.showAndWait();

		}
	}
	//	 Alert alert = new Alert(AlertType.ERROR);
	//		    alert.setTitle("Error");
	//		    alert.setHeaderText(null);
	//		    alert.setContentText("Please add a photo for the employee.");
	//		    alert.showAndWait();


}
