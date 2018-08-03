package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage)throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_first.fxml"));
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("MyNum.fxml"));
		Parent root = loader.load();
		MainFirstController controller = loader.getController();
		//생성된 컨드롤러들을 리턴 
		controller.setPrimaryStage(primaryStage);
		//start 메소드에서 컨트롤러로 primaryStage전달 
		
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setTitle("Lottery Game");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
