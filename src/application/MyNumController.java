package application;

import java.net.URL;
import java.util.List;
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
	static public Vector<Integer> temp = new Vector<Integer>();

	
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
		for(int i = 0; i< 7; i++)
		{
			temp.add((int)(Math.random()*45) +1);
		}
		
		//임시값 삽입 
//		temp.add(1);
//		temp.add(2);
//		temp.add(3);
//		temp.add(4);
//		temp.add(5);
//		temp.add(6);
//		temp.add(7);

		FXMLLoader result = new FXMLLoader(getClass().getResource("ShowTheResult.fxml"));
		Parent forResult = result.load();
		ShowTheResultController rcontroller = result.getController();
		
		rcontroller.setPrimaryStage(primaryStage);
		
		Scene last = new Scene(forResult);
		primaryStage.setTitle("최종결과");
		primaryStage.setScene(last);
		
	}
}
