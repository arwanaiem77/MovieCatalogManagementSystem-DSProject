package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainScene extends HBox{
	


	public MainScene(Stage primaryStage) {
		  VBox root = new VBox();
	        root.setPadding(new Insets(20));
	        root.setSpacing(10);
	        root.setStyle("-fx-background-color: #F8EED9;");
		
		HBox hb =new HBox(170);
		hb.setPadding(new Insets(50,50,50,50));
		MenuBar mb = new MenuBar();
		Menu open =new Menu("Open");
	;
		MenuItem openItem = new MenuItem("Load Movies");
		openItem.setOnAction(a -> {
		    Movie.movieList.loadMoviesFromFile();
		});
		open.getItems().add(openItem);

		Menu savee = new Menu("Save");
		MenuItem saveItem = new MenuItem("Save Movies");
		saveItem.setOnAction(a -> {
		    Movie.movieList.saveMovieToFile();
		});
		savee.getItems().add(saveItem);

		Menu exit = new Menu("Exit");
		MenuItem exitItem = new MenuItem("Exit App");
		exitItem.setOnAction(a -> {
		   System.exit(0);
		});
		exit.getItems().add(exitItem);
		mb.getMenus().addAll(open,savee,exit);

		ImageView mainPhoto =new ImageView(new Image("main.png"));
		mainPhoto.setFitHeight(600);
		mainPhoto.setFitWidth(300);	
		ImageView addImg=new ImageView(new Image("add.png"));
		addImg.setFitHeight(300);
		addImg.setFitWidth(300);
		ImageView searchImg=new ImageView(new Image("search.png"));
		searchImg.setFitHeight(300);
		searchImg.setFitWidth(300);
		ImageView editImg=new ImageView(new Image("edit.png"));
		editImg.setFitHeight(300);
		editImg.setFitWidth(300);
		ImageView table=new ImageView(new Image("table.png"));
		table.setFitHeight(300);
		table.setFitWidth(300);	
		ImageView saveImg=new ImageView(new Image("save.png"));
		saveImg.setFitHeight(300);
		saveImg.setFitWidth(300);	
		ImageView deleteImg=new ImageView(new Image("delete.png"));
		deleteImg.setFitHeight(300);
		deleteImg.setFitWidth(300);
//		
		Button add =new Button("",addImg);
		add.setPrefHeight(300);
		add.setPrefWidth(300);
		Button search =new Button("",searchImg);
		search.setPrefHeight(300);
		search.setPrefWidth(300);
		Button edit =new Button("",editImg);
		edit.setPrefHeight(300);
		edit.setPrefWidth(300);
		Button stat =new Button("",table);
		stat.setPrefHeight(300);
		stat.setPrefWidth(300);
		Button save =new Button("",saveImg);
		save.setPrefHeight(300);
		save.setPrefWidth(300);
		Button delete =new Button("",deleteImg);
		delete.setPrefHeight(300);
		delete.setPrefWidth(300);
		
		GridPane gp =new GridPane();
		gp.add(add, 700, 100);
		gp.add(search, 800, 100);
		gp.add(edit, 1000, 100);
		gp.add(stat, 700, 200);
		gp.add(save, 800, 200);
		gp.add(delete, 1000, 200);
		
		
		hb.getChildren().addAll(mainPhoto,gp);
		root.getChildren().addAll(mb,hb);
		hb.setStyle("-fx-background-color: #F8EED9;");
		Scene scene =new Scene(root,1100,700);
		primaryStage.setScene(scene);			
		primaryStage.setFullScreen(true);
		primaryStage.show();


		add.setOnAction(a ->{
			Scene scenee =new Scene(new AddMovie(primaryStage,scene),1100,700);
		
		});
		search.setOnAction(a ->{
			Scene scenee =new Scene(new Search(primaryStage,scene),1100,700);

		});
		edit.setOnAction(a ->{
		Scene scenee =new Scene(new UpdateMovie(primaryStage,scene),1100,700);

		});
		stat.setOnAction(a ->{
			Scene scenee =new Scene(new MovieTableViewNextPrev(primaryStage,scene),1100,700);
		});
		save.setOnAction(a ->{
			
			Scene scenee =new Scene(new TopLeast(primaryStage,scene),1100,700);

		});
		delete.setOnAction(a ->{
			Scene scenee =new Scene(new DeleteMovie(primaryStage,scene),1100,700);

		});

		
	}
	}