package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyNumController implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println("연동완료!");

		//자동으로 선택된 수들 보여줄 예정 
		viewAuto();
		
		//수동으로 선택된 수들 보여줄 예정 
		viewManual();
		
	}
	
	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	
	@FXML private VBox autoChoice;
	@FXML private VBox manualChoice;
	@FXML private Button checkWinning;
	public Vector<Integer> winning = new Vector<Integer>();
	
	//자동으로 선택된 목록 
	public void viewAuto() {
		if(MainFirstController.autoTotalNums.size()>0)
		{
			for(int i = 0; i<MainFirstController.autoTotalNums.size(); i++)
			{
				autoChoice.getChildren().addAll(new Label(MainFirstController.autoTotalNums.get(i).toString()));
			}

		}
		System.out.println(MainFirstController.autoTotalNums);
	}

	
	//수동으로 선택된 목록 
	public void viewManual() {
		if(MainFirstController.manualTotalNums.size()>0) {
			for(int i = 0; i<MainFirstController.manualTotalNums.size(); i++)
			{
				manualChoice.getChildren().addAll(new Label(MainFirstController.manualTotalNums.get(i).toString()));
			}
		}
		System.out.println(MainFirstController.manualTotalNums);
	}
	
	public void makeWinningNumber() throws Exception {
		for(int i = 0; i< 6; i++)
		{
			winning.add((int)(Math.random()*45) +1);
		}
		
		System.out.println(winning);
		FXMLLoader result = new FXMLLoader(getClass().getResource("ShowTheResult.fxml"));
		Parent forResult = result.load();
		ShowTheResultController rcontroller = result.getController();
		
		Scene last = new Scene(forResult);
		primaryStage.setTitle("최종결과");
		primaryStage.setScene(last);

		
		
		
		
		
	}
}
