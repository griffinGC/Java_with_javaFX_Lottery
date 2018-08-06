package application;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
	
	static GridPane gridPaneForAuto = new GridPane();
	static GridPane gridPaneForManual = new GridPane();
	
	
	@FXML private VBox autoChoice;
	@FXML private VBox manualChoice;
	@FXML private Button checkWinning;
	static public Vector<Integer> temp = new Vector<Integer>();
	static public TreeSet<Integer> tempSet = new TreeSet<Integer>();

	
	//자동으로 선택된 목록 
	public void viewAuto() {
		if(MainFirstController.autoTotalNums.size()>0)
		{
			for(int i = 0; i<MainFirstController.autoTotalNums.size(); i++)
			{
//				autoChoice.getChildren().addAll(new Label(MainFirstController.autoTotalNums.get(i).toString()));
				List<Integer> tempTree = new Vector<Integer>(MainFirstController.autoTotalNums.get(i));
				for(int j = 0; j<tempTree.size();j++) {
					gridPaneForAuto.add(new Label(tempTree.get(j).toString() + "\t"), j, i);
					gridPaneForAuto.setAlignment(Pos.BASELINE_CENTER);

				}				
			}
			autoChoice.getChildren().addAll(gridPaneForAuto);

		}
		System.out.println(MainFirstController.autoTotalNums);
	}

	
	//수동으로 선택된 목록 
	public void viewManual() {
		if(MainFirstController.manualTotalNums.size()>0) {
			for(int i = 0; i<MainFirstController.manualTotalNums.size(); i++)
			{
//				manualChoice.getChildren().addAll(new Label(MainFirstController.manualTotalNums.get(i).toString()));
				List<Integer> tempTree = new Vector<Integer>(MainFirstController.manualTotalNums.get(i));
				for(int j = 0; j<tempTree.size();j++) {
					gridPaneForManual.add(new Label(tempTree.get(j).toString() + "\t"), j, i);
					gridPaneForManual.setAlignment(Pos.BASELINE_CENTER);
				}				
			}
			manualChoice.getChildren().addAll(gridPaneForManual);
		}
		System.out.println(MainFirstController.manualTotalNums);
	}
	
	public void makeWinningNumber() throws Exception {
		for(int i = 0; i< 7; i++)
		{
			tempSet.add((int)(Math.random()*45) +1);
		}
		//수가 중복되지 않게 하기 위해서 tempSet이라는 Set을 사용함 
		Iterator<Integer> ite = tempSet.iterator();
		while(ite.hasNext()) {
			temp.add(ite.next());
		}
		
//		//임시값 삽입 
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
