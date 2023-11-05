package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application{
	
	FlowPane root;
	
	@Override
	public void start(Stage stage) throws Exception {
		try {
			root = (FlowPane)FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
			stage.setTitle("LOGIN");
			Scene scene = new Scene(root,400,200);
			stage.setScene(scene);
			stage.setMinWidth(400);
			stage.setMinHeight(200);
			stage.show();	
		}catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
