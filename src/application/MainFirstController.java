package application;

import java.net.URL;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainFirstController implements Initializable {

	@FXML
	private TextField number; // 게임 횟수

	@FXML
	private Button inputBtn; // 입력 버튼
	@FXML
	private Label restNumber;// 로또를 수행할 횟수

	@FXML
	private RadioButton auto;// 자동을 선택한 라디오 버튼
	@FXML
	private RadioButton manual;// 수동을 선택한 라디오 버튼
	@FXML
	private Button reset; // 초기화 버튼
	@FXML
	private Button register; // 6개 뽑은 수를 등록하는 버튼
	public String possible; // Text에 들어갈 남은 횟수
	public int possibleNumber = 0; // 가능한 횟수를 숫자로 변경한 것
	public int limitCheck = 6; // 6개 선택하기 위해서 설정

	// checkbox에 체크된 소스 얻기
//	TreeSet<Object> checkSource = new TreeSet<Object>();
//	TreeSet<CheckBox> checkSource = new TreeSet<CheckBox>();

	// 6개 선택하였을 경우 값 저장
	TreeSet<Integer> selectedNums = new TreeSet<Integer>();

	// 자동을 선택하였을 경우 6개로 묶인 값을 다시 저장
	static Vector<TreeSet<Integer>> autoTotalNums = new Vector<TreeSet<Integer>>();

	// 수동을 선택하였을 경우 6개로 묶인 값을 다시 저장
	static Vector<TreeSet<Integer>> manualTotalNums = new Vector<TreeSet<Integer>>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	// 입력버튼 실행하였을 경우!
	public void handleInputButton(ActionEvent e) throws Exception {
		// ActionEvent의 매개값을 아무거나 넣어도 상관 없음! 어차피 여기 안에서 쓰일 일이 없기 때문에!

		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(primaryStage);
		dialog.setTitle("확인");

		// 값 얻기
		possible = number.getText();
//		System.out.println(possible);
		Parent parent = null;
		if (isNumber(possible)) {
			parent = FXMLLoader.load(getClass().getResource("ChooseOne.fxml"));

		} else {
			parent = FXMLLoader.load(getClass().getResource("Error.fxml"));
		}

		// 횟수 설정
		restNumber.setText(possible);

		// dialog생성
		Button btn = (Button) parent.lookup("#btnOk");
		btn.setOnAction(even -> dialog.close());
		Scene scene = new Scene(parent);
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.show();

	}

	// 자동을 선택하였을 경우 알림 dialog
	public void AutoSelected(ActionEvent e) throws Exception {
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(primaryStage);
		dialog.setTitle("자동");
		Parent parent = FXMLLoader.load(getClass().getResource("ChooseAuto.fxml"));
		Button btn = (Button) parent.lookup("#btnOk");
		btn.setOnAction(even -> dialog.close());
		Scene scene = new Scene(parent);
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.show();

	}

	// 수동을 선택하였을 경우 알림 dialog
	public void ManualSelected(ActionEvent e) throws Exception {
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(primaryStage);
		dialog.setTitle("수동");
		Parent parent = FXMLLoader.load(getClass().getResource("ChooseManual.fxml"));
		Button btn = (Button) parent.lookup("#btnOk");
		btn.setOnAction(even -> dialog.close());
		Scene scene = new Scene(parent);
		dialog.setScene(scene);
		dialog.setResizable(false);
		dialog.show();

	}

	// 초기화 버튼 => 체크박스 모두 다 false로 만듬
	public void resetChBox(ActionEvent e) throws Exception {
		// 나중에 처리
//		
//		while(!checkSource.isEmpty()) {
//			CheckBox haveChecked = (CheckBox) checkSource.pollFirst();
//			haveChecked.setSelected(false);
//		}
	}

	// 등록 버튼
	public void registerNum(ActionEvent e) throws Exception {

		// 횟수 줄이기!
		possible = restNumber.getText(); // possible은 문자열상태
		possibleNumber = Integer.parseInt(possible);
//		System.out.println("총 남은 횟수 : " + possibleNumber);

		if (possibleNumber > 0) {
			possibleNumber--;
			if (auto.isSelected()) {
//				System.out.println("true");
				automaticalChoice();
				
				//수가 잘 들어갔는지 확인
//				System.out.println(autoTotalNums);
				
			} else {
				TreeSet<Integer> temp = new TreeSet<Integer>();
				Iterator<Integer> itr = selectedNums.iterator();
				while(itr.hasNext()) {
					temp.add(itr.next());
				}
				TreeSet<Integer> temp2 = new TreeSet<Integer>();
				while(!temp.isEmpty()) {
					temp2.add(temp.pollFirst());
				}
				manualTotalNums.add(temp2);
				temp.clear();
				
				//수가 잘 들어갔는지 확인용 
//				System.out.println(manualTotalNums);
				
			}
			if (possibleNumber == 0) {
//				Parent parent2 = FXMLLoader.load(getClass().getResource("MyNum.fxml"));
//				Scene scene2 = new Scene(parent2);
//
//				// primasyStage위에다가 scene을 올려버림
//				primaryStage.setScene(scene2);
//				System.out.println("횟수가 만료 되었습니다.");
//				
				System.out.println("횟수가 만료 되었습니다.");
			
				FXMLLoader loadMyNum = new FXMLLoader(getClass().getResource("MyNum.fxml"));
				Parent parent2 = loadMyNum.load();
				MyNumController myNumControl = loadMyNum.getController();
				

				myNumControl.setPrimaryStage(primaryStage);
				
				Scene scene2 = new Scene(parent2);

				// primasyStage위에다가 scene을 올려버림
				primaryStage.setScene(scene2);

				System.out.println("숫자확인창 끝 ");
				
			}
		}
		restNumber.setText(Integer.toString(possibleNumber));
	}

	// 자동 선택 숫자 6개 뽑기!
	public void automaticalChoice() {
		for (int i = 0; i < 6; i++) {
			selectedNums.add((int) (Math.random() * 45) + 1);

		}
		// 6개 뽑고 정렬함!
		NavigableSet<Integer> sorted = selectedNums.descendingSet().descendingSet();
		selectedNums = (TreeSet) sorted;
		
		//숫자 잘 들어 갔는지 확인용 
//		for (Integer nu : selectedNums) {
//			System.out.println(nu);
//		}

		// 뽑은 수를 전체 자동 수로 저장
		TreeSet<Integer> temp = new TreeSet<Integer>();
		while (!selectedNums.isEmpty()) {
			temp.add(selectedNums.pollFirst());
		}
		autoTotalNums.add(temp);
		selectedNums.clear();

	}

	// 수동 선택 숫자는 6개 이상 체크 못하도록!
	public void checkChBox(ActionEvent e) throws Exception {
		CheckBox chb = (CheckBox) e.getTarget();

		// getTarget함수는 이벤트의 타겟을 알려줌!

		String tempText = e.getSource().toString();

//		System.out.println(tempText);
//		String[] arr2 = tempText.split("@|\\[");
//		System.out.println(arr2[1]);

		String[] arr = tempText.split("\'");
		tempText = arr[1];
		int realNum = Integer.parseInt(tempText);

		// 선택된 값이 true일 경우
		if (chb.isSelected()) {
			// 남은 횟수를 하나 줄인다.
			limitCheck--;
			if (limitCheck < 0) {
				// 0보다 작으면 선택되어도 값을 false로 셋팅
				chb.setSelected(false);
				// 0으로 값을 초기화 함 아니면 계속 작아짐!
				limitCheck = 0;
			} else {
//				System.out.println("저장할 번호 : " + realNum);
//				System.out.println("남은 횟수 : " + limitCheck);
				selectedNums.add(realNum);
			}
		} else if (chb.isSelected() == false) {
			limitCheck++;
			selectedNums.remove(realNum);
			chb.setSelected(false);
//			System.out.println("남은횟수 : " + limitCheck);
//			System.out.println("뺄 번호 : " + realNum);
		}
		System.out.println(selectedNums);
//		TreeSet<Integer> temp = new TreeSet<Integer>();
//
//		while(!selectedNums.isEmpty()) {
//			temp.add(selectedNums.pollFirst());			
//		}
//
//		manualTotalNums.add(temp);
//		selectedNums.clear();



	}

	public static boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
