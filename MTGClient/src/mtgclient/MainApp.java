package mtgclient;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mtgclient.model.MagicCard;

public class MainApp extends Application {
	
	private static MTGClient client;
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Joe and Fletcher Final Project, CSI-235");
		
		initLayout();
		showCardOverview();
	}

	private void initLayout() {
		try{
			//load the root container scene, card overview to be added
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/Root.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			//display the scene containing the root layout to the stage
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch(IOException e){
			e.printStackTrace();
		}	
	}

	private void showCardOverview() {
		try{
			//load the card overview
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/CardOverview.fxml"));
			AnchorPane cardOverview = (AnchorPane) loader.load();

			//add the cardoverview to the root layout
			cardOverview.setPrefWidth(rootLayout.getWidth());
			rootLayout.setCenter(cardOverview);
			
			
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	 public static void main(String[] args) {
	        launch(args);
	 }

	public static MagicCard search(String text) {
		return client.run(text);
	}
}
