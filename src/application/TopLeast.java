package application;

import java.util.ArrayList;
import java.util.Collections;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TopLeast extends Pane{
	ArrayList<Integer> specificYearList=new ArrayList<>();
	

	public TopLeast(Stage primaryStage,Scene oldScene) {
		BorderPane bp =new BorderPane();
		VBox vb =new VBox(10);
		vb.setAlignment(Pos.CENTER);
		ArrayList<Movie> movies = new ArrayList<>();
		for(int i=0;i<Movie.movieList.arr.length;i++) {
			ArrayList<Movie> list = Movie.movieList.arr[i].inOrder();
			for(int j=0;j<list.size();j++) {
				movies.add(list.get(j));
			}			
		}
		double maxCount = 0;
		double minCount = 11;

		for(int i=0;i<movies.size();i++) {
			if(movies.get(i).getRating()>maxCount)
				maxCount=movies.get(i).getRating();
			if(movies.get(i).getRating()<minCount)
				minCount=movies.get(i).getRating();	
		}
		ArrayList<Movie> maxList=new ArrayList<>();
		ArrayList<Movie> minList=new ArrayList<>();
		
		for(int i=0;i<movies.size();i++) {
			if(movies.get(i).getRating()==maxCount)
				maxList.add(movies.get(i));
			if(movies.get(i).getRating()==minCount)
				minList.add(movies.get(i));	
				
		}
		
		for(int i =0;i<maxList.size();i++) {
			Label l1 = new Label("Top Ranked Movie(s) : " + maxList.get(i) );
			l1.setFont(new Font(20));
			vb.getChildren().add(l1);
		}
		for(int i =0;i<minList.size();i++) {
			Label l1 = new Label("Least Ranked Movie(s) : " + minList.get(i));
			l1.setFont(new Font(20));
			vb.getChildren().add(l1);
		}
		bp.setCenter(vb);



		bp.setStyle("-fx-background-color: #F8EED9;");
		Button back =new Button("Back");
    	back.setStyle("-fx-background-color: #E65A1D; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
		back.setOnAction(a ->{
			primaryStage.setScene(oldScene);		
			primaryStage.setFullScreen(true);
		});
		bp.setBottom(back);
		Scene scene =new Scene(bp,1100,700);
		primaryStage.setScene(scene);		
		primaryStage.setFullScreen(true);
		primaryStage.show();
		
	}


}