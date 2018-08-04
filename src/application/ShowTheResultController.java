package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowTheResultController implements Initializable {

	@FXML
	private Label winningNumber; //당첨 번호 적는 라벨 
	@FXML
	private Label bonusNumber; // 보너스 번호 적는 라벨 
	@FXML
	private VBox vboxAuto; //자동 박스 
	@FXML
	private VBox vboxManual; // 수동 박스 
	
	static public List<Integer> winning = new Vector<Integer>(); // 당첨번호 뽑기위해서 list로 생성 

	static public TreeSet<Integer> winning2; //equals를 비교하기 위해 생성 
	
	Integer bonus; // 보너스 번호
	private Stage primaryStage2;
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage2 = primaryStage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showWinningBonusNumber();
		resultAuto();
		resultManual();
//		tilSecondorFirst();
	}

	// bonus랑 당첨숫자 보여줌
	public void showWinningBonusNumber() {

		winning = MyNumController.temp.subList(0, 6);
		bonus = MyNumController.temp.get(6);
		
		//equals를 성립하기 위해서 list를 treeset으로 바꿔줌 
		winning2 = new TreeSet<Integer> (winning);
//		StringTokenizer wi = 		
		winningNumber.setText(winning.toString());
		bonusNumber.setText(bonus.toString());
		System.out.println(MyNumController.temp);
		System.out.println("당첨 번호 : " +winning);
		System.out.println("당첨번호 캐스팅 : " + winning2);
	}

	// 자동번호 결과
	public void resultAuto() {
		if (MainFirstController.autoTotalNums.size() > 0) {
			for (int i = 0; i < MainFirstController.autoTotalNums.size(); i++) {
				if (MainFirstController.autoTotalNums.get(i).equals(winning2)) {
					vboxAuto.getChildren()
							.addAll(new Label(MainFirstController.autoTotalNums.get(i).toString() + "\t" + "당첨!"));
				} else if(secondWinner(winning, MainFirstController.autoTotalNums.get(i), bonus)) {
					vboxAuto.getChildren()
					.addAll(new Label(MainFirstController.autoTotalNums.get(i).toString() + "\t" + "2등 당첨!"));
				} else {
					vboxAuto.getChildren()
							.addAll(new Label(MainFirstController.autoTotalNums.get(i).toString() + "\t" + "꽝!"));
				}
			}
		}
		System.out.println(MainFirstController.autoTotalNums);
	}

	// 수동번호 결과
	public void resultManual() {
		if (MainFirstController.manualTotalNums.size() > 0) {
			for (int i = 0; i < MainFirstController.manualTotalNums.size(); i++) {
				if (MainFirstController.manualTotalNums.get(i).equals(winning2)) {
					vboxManual.getChildren()
							.addAll(new Label(MainFirstController.manualTotalNums.get(i).toString() + "\t" + "당첨!"));
				} else if(secondWinner(winning, MainFirstController.manualTotalNums.get(i), bonus)) {
					vboxManual.getChildren()
					.addAll(new Label(MainFirstController.manualTotalNums.get(i).toString() + "\t" + "2등 당첨!"));
				}
				else{
					vboxManual.getChildren()
							.addAll(new Label(MainFirstController.manualTotalNums.get(i).toString() + "\t" + "꽝!"));
				}

			}
		}
		System.out.println(MainFirstController.manualTotalNums);
	}

	// 2등 확인!
	public boolean secondWinner(List<Integer> winner, TreeSet<Integer> compare, Integer bonus) {
//		List<Integer> temp = (List) compare;
		
		List<Integer> temp = new Vector<Integer>(compare);
		int same = 5;
		boolean bonusCheck = false;
		for(int i = 0; i< 6; i++)
		{
			if(temp.get(i)==bonus) {
				bonusCheck=true;
				break;
			}
		}
		for(int i = 0; i<6; i++) 
		{
			for(int j = 0; j<=i; j++) {
				if(winner.get(i) == temp.get(j)) 
				{
					same --;
				}					
			}
		
		}
		if((same <= 0) && bonusCheck) {
			return true;
		}
		return false;
	}
	
	public void tilSecondorFirst() {
//		Vector<Integer> allTotal = new Vector<Integer>();
		
		
	}
	
	public int fact(int n) {
		if(n<=1) {
			return n;
		}
		return n*fact(n-1);
	}



}
