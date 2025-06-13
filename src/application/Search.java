package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Search extends Pane {

	public Search(Stage primaryStage,Scene oldScene) {
		BorderPane bp =new BorderPane();
//		ImageView searchByKeyTitleImg=new ImageView(new Image("ktitle.png"));
//		searchByKeyTitleImg.setFitHeight(300);
//		searchByKeyTitleImg.setFitWidth(300);
//		ImageView searchByKeyAuthorImg=new ImageView(new Image("kauthor.png"));
//		searchByKeyAuthorImg.setFitHeight(300);
//		searchByKeyAuthorImg.setFitWidth(300);
//		ImageView searchByBookIDImg=new ImageView(new Image("id.png"));
//		searchByBookIDImg.setFitHeight(300);
//		searchByBookIDImg.setFitWidth(300);
//		ImageView searchByTitleImg=new ImageView(new Image("title.png"));
//		searchByTitleImg.setFitHeight(300);
//		searchByTitleImg.setFitWidth(300);
		
		Button searchByTitle =new Button("Search By Title");
		searchByTitle.setPrefHeight(300);
		searchByTitle.setPrefWidth(300);
		Button searchByYear =new Button("Search By Year");
		searchByYear.setPrefHeight(300);
		searchByYear.setPrefWidth(300);
	
		
		GridPane gp =new GridPane();
		gp.add(searchByTitle, 700, 100);
		gp.add(searchByYear, 800, 100);
		
		
		gp.setAlignment(Pos.CENTER);
		
		bp.setCenter(gp);
		bp.setStyle("-fx-background-color: #F8EED9;");
		Button back =new Button("Back");
    	back.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
		back.setOnAction(a ->{
			primaryStage.setScene(oldScene);		
			primaryStage.setFullScreen(true);
		});
		bp.setBottom(back);
		bp.setStyle("-fx-background-color: #F8EED9;");

		Scene scene =new Scene(bp,1100,700);
		primaryStage.setScene(scene);		
		primaryStage.setFullScreen(true);
		primaryStage.show();
		
		searchByTitle.setOnAction(a ->{
			Scene scenee =new Scene(new SearchMovie(primaryStage,scene),1100,700);
		});
		searchByYear.setOnAction(a ->{
			Scene scenee =new Scene(new SearchYear(primaryStage,scene),1100,700);

		});
	
	}
	
	

}
