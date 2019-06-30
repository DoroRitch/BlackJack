package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BlackJack_main {
	public static void main(String[] args) {
		System.out.println("☆★☆★☆★BlackJackへようこそ！☆★☆★☆★");
		System.out.println("ディーラーがカードを配ります");

		//山札リスト
		List<String> bills = new ArrayList<String>();

		//得点計算用ハッシュマップ
		HashMap<String, Integer> points = new HashMap<String, Integer>();

		//カード生成用配列
		String[] strMark={"ダイヤ","ハート","スペード","クローバー"};
		String[] strNumber={"A","2","3","4","5","6","7","8","9","10","J","Q","K"};

		//For文でカード1枚を作成し、山札リストに追加
		//カードと点数をハッシュマップに追加
		for (int i=0;i<strMark.length;i++){
			for (int j=0;j<strNumber.length;j++){
				String card = strMark[i]+"の"+strNumber[j];
				bills.add(card);
				if(j>9) {
					points.put(card, 10);
				}else {
					points.put(card, j+1);
				}
			}
		}

		//山札のシャッフル
		Collections.shuffle(bills);

		//ゲームの開始
		//プレイヤーの手札
		ArrayList<String> handCard =new ArrayList<String>();
		handCard.add(bills.get(0));
		System.out.println("あなたの1枚目のカードは["+handCard.get(0)+"]です");
		int cardPoints = points.get(bills.get(0));
		bills.remove(0);
		handCard.add(bills.get(0));
		System.out.println("あなたの2枚目のカードは["+handCard.get(1)+"]です");
		cardPoints += points.get(bills.get(0));
		bills.remove(0);
		System.out.println("あなたの手札は"+handCard+"です");
		System.out.println("あなたの得点は"+cardPoints+"点です");

		//ディーラーの手札
		ArrayList<String> handCard_dealer = new ArrayList<String>();
		handCard_dealer.add(bills.get(0));
		System.out.println("ディーラーの1枚目のカードは["+handCard_dealer.get(0)+"]です");
		int cardPoints_dealer = points.get(bills.get(0));
		bills.remove(0);
		handCard_dealer.add(bills.get(0));
		System.out.println("ディーラーの2枚目のカードはわかりません");
		cardPoints_dealer += points.get(bills.get(0));
		bills.remove(0);

		//BlackJackかBurstかの処理
		JUDGE.judge(cardPoints,cardPoints_dealer);
		if(JUDGE.result!="") {
			return;
		}

		//Hit?Stand?
		Scanner scanner = new Scanner(System.in);
		int PlayerRoop = 0;
		while(PlayerRoop==0) {
			int choice_roop =0;
			String choicePlay ="";

			while (choice_roop==0) {
				System.out.print("Hitしますか？Standしますか？(H/S?)＞");
				String choice = scanner.next();

				if (choice.contains("H") || choice.contains("h")) {
					System.out.println("Hitを選択しました");
					choice_roop+=1;
					choicePlay="Hit";
				}else if(choice.contains("S") || choice.contains("s")){
					System.out.println("Standを選択しました");
					choice_roop+=1;
					choicePlay="Stand";
				}else {
					System.out.println("HitかStandかを選択してください");
				}
			}			//while文締め

			//行動選択結果
			if (choicePlay.contains("Hit")) {
				handCard.add(bills.get(0));
				System.out.println("引いたカードは["+handCard.get(handCard.size()-1)+"]です");
				cardPoints += points.get(handCard.get(handCard.size()-1));
				bills.remove(0);
				System.out.println("現在の点数は"+cardPoints+"点です");
				System.out.println("あなたの手札は"+handCard+"です");
				JUDGE.judge(cardPoints,cardPoints_dealer);
				if(JUDGE.result != "") {
					System.out.println("BlackJack終了！また遊んでね☆");
					return;
				}
			}else {
				System.out.println("ディーラーのターンに移ります");
				PlayerRoop+=1;
			}		//if文締め
		}			//while文締め

		scanner.close();

		System.out.println("ディーラーの2枚目のカードは["+handCard_dealer.get(1)+"]でした");
		System.out.println("ディーラーの手札は"+handCard_dealer+"です");
		System.out.println("ディーラーの得点は"+cardPoints_dealer+"点です");

		//ディーラーの行動
		while(cardPoints_dealer<15 || cardPoints_dealer<=cardPoints) {
			handCard_dealer.add(bills.get(0));
			System.out.println("ディーラーの引いたカードは["+handCard_dealer.get(handCard_dealer.size()-1)+"]です");
			cardPoints_dealer+=points.get(handCard_dealer.get(handCard_dealer.size()-1));
			bills.remove(0);
			System.out.println("ディーラーの得点は"+cardPoints_dealer+"点です");
			JUDGE.judge(cardPoints,cardPoints_dealer);
			if(JUDGE.result != "") {
				System.out.println("BlackJack終了！また遊んでね☆");
				return;
			}
		}		//while文締め

		//得点比較・勝敗
		System.out.println("ディーラーの手札は"+handCard_dealer+"です");
		System.out.println("あなたの得点は"+cardPoints+"点です");
		if(cardPoints==cardPoints_dealer) {
			System.out.println("////引き分けです。////");
			System.out.println("BlackJack終了！また遊んでね☆");
			return;
		}else if(cardPoints>cardPoints_dealer) {
			System.out.println("----あなたの勝ちです----");
			System.out.println("BlackJack終了！また遊んでね☆");
			return;
		}else{
			System.out.println("----あなたの負けです----");
			System.out.println("BlackJack終了！また遊んでね☆");
			return;
		}

	}		//main method締め
}		//class締め
