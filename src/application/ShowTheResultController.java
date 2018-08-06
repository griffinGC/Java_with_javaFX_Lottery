package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.Vector;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
	
	@FXML
	private VBox vboxLast;
	
	static GridPane FinalAuto = new GridPane();
	static GridPane FinalManual = new GridPane();
	@FXML
	private GridPane showWin;
	
	static public List<Integer> winning = new Vector<Integer>(); // 당첨번호 뽑기위해서 list로 생성 

	static public TreeSet<Integer> winning2; //equals를 비교하기 위해 생성 
	
	//전체를 넣을 하나의 리스트 필요!
	static public Vector<TreeSet<Integer>> totalNums = new Vector<TreeSet<Integer>>();
	
	static public Integer bonusReal; // 보너스 번호
	private Stage primaryStage2;
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage2 = primaryStage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		showWinningBonusNumber();
		resultAuto();
		resultManual();
		tilSecondorFirst();
	}

	// bonus랑 당첨숫자 보여줌
	public void showWinningBonusNumber() {

		winning = MyNumController.temp.subList(0, 6);
		bonusReal = MyNumController.temp.get(6);
		
		//equals를 성립하기 위해서 list를 treeset으로 바꿔줌 
		winning2 = new TreeSet<Integer> (winning);

		for(int i = 0; i<winning.size(); i++) {
			showWin.add(new Label(winning.get(i).toString()), i, 0);

		}
//		winningNumber.setText(winning.toString());
		bonusNumber.setText(bonusReal.toString());
		System.out.println(MyNumController.temp);
		System.out.println("당첨 번호 : " +winning);
		System.out.println("당첨번호 캐스팅 : " + winning2);
	}

	// 자동번호 결과
	public void resultAuto() {
		if (MainFirstController.autoTotalNums.size() > 0) {
			for (int i = 0; i < MainFirstController.autoTotalNums.size(); i++) {
				List<Integer> tempTree = new Vector<Integer>(MainFirstController.autoTotalNums.get(i));
				if (MainFirstController.autoTotalNums.get(i).equals(winning2)) {
					
					for(int j = 0; j<tempTree.size();j++) {
					FinalAuto.add(new Label(tempTree.get(j).toString() + "\t"), j, i);
					FinalAuto.add(new Label("\t 1등 당첨!"), 6,i);
					FinalAuto.setAlignment(Pos.BASELINE_CENTER);
				}

	
				} else if(secondWinner(winning, MainFirstController.autoTotalNums.get(i), bonusReal)) {
				
					for(int j = 0; j<tempTree.size();j++) {
						FinalAuto.add(new Label(tempTree.get(j).toString() + "\t"), j, i);
						FinalAuto.add(new Label("\t 2등 당첨!"), 6, i);
						FinalAuto.setAlignment(Pos.BASELINE_CENTER);
					}
					
					
				} else {
					for(int j = 0; j<tempTree.size();j++) {
						FinalAuto.add(new Label(tempTree.get(j).toString() + "\t"), j, i);
						FinalAuto.add(new Label("\t 꽝"), 6,i);
						FinalAuto.setAlignment(Pos.BASELINE_CENTER);
					}

				}
			}
			vboxAuto.getChildren().addAll(FinalAuto);
		}
		System.out.println(MainFirstController.autoTotalNums);
	}

	// 수동번호 결과
	public void resultManual() {
		if (MainFirstController.manualTotalNums.size() > 0) {
			for (int i = 0; i < MainFirstController.manualTotalNums.size(); i++) {
				List<Integer> tempTree = new Vector<Integer>(MainFirstController.manualTotalNums.get(i));
				if (MainFirstController.manualTotalNums.get(i).equals(winning2)) {
//					vboxManual.getChildren()
//							.addAll(new Label(MainFirstController.manualTotalNums.get(i).toString() + "\t" + "당첨!"));
					for(int j = 0; j<tempTree.size();j++) {
					FinalManual.add(new Label(tempTree.get(j).toString() + "\t"), j, i);
					FinalManual.add(new Label("\t 1등 당첨!"), 6,i);
					FinalManual.setAlignment(Pos.BASELINE_CENTER);
					}
					
				} else if(secondWinner(winning, MainFirstController.manualTotalNums.get(i), bonusReal)) {
//					vboxManual.getChildren()
//					.addAll(new Label(MainFirstController.manualTotalNums.get(i).toString() + "\t" + "2등 당첨!"));
					for(int j = 0; j<tempTree.size();j++) {
						FinalManual.add(new Label(tempTree.get(j).toString() + "\t"), j, i);
						FinalManual.add(new Label("\t 2등 당첨!"), 6, i);
						FinalManual.setAlignment(Pos.BASELINE_CENTER);
					}
					
					
				}
				else{
//					vboxManual.getChildren()
//							.addAll(new Label(MainFirstController.manualTotalNums.get(i).toString() + "\t" + "꽝!"));
					for(int j = 0; j<tempTree.size();j++) {
						FinalManual.add(new Label(tempTree.get(j).toString() + "\t"), j, i);
						FinalManual.add(new Label("\t 꽝"), 6,i);
						FinalManual.setAlignment(Pos.BASELINE_CENTER);
					}
					
				}

			}
			vboxManual.getChildren().addAll(FinalManual);
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
			if(temp.get(i) == bonus) {
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
		int count = 0;
		boolean value = false;
		boolean valueSecond = false;
		boolean valueFirst = false;
		int tempBonus = 0;
		int matchingNumber = 0;
		Vector<Integer> winningList = new Vector<Integer>(winning);
		totalNums.addAll(MainFirstController.autoTotalNums);
		totalNums.addAll(MainFirstController.manualTotalNums);
		System.out.println("모두 들어간 값 :" +totalNums);
		System.out.println(totalNums.size());
		while(value != true) {
			TreeSet<Integer> tempValue;// = new TreeSet<Integer> ();
			for(int i = 0; i<totalNums.size(); i++) {
				tempValue = totalNums.get(i);
				tempBonus = bonusReal;
				winningList.retainAll(tempValue);
				matchingNumber = winningList.size();
				count++;
				if(matchingNumber == 5 && tempValue.contains(tempBonus)) {
					value = true;
				}
				if(matchingNumber == 6) {
					value = true;
				}
			}
		}
		Label firstCount = new Label();
		Label secondCount = new Label();
		
		firstCount.setText("1등이 될때까지 걸리는 횟수 : " + count);
		secondCount.setText("2등이 될때까지 걸리는 횟수 : " + count);
		vboxLast.getChildren().add(firstCount);
		vboxLast.getChildren().add(secondCount);

		
	}
		
}


