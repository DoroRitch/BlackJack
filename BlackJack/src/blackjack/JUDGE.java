package blackjack;

public class JUDGE {
	public static String result = "";

	public static void judge(int cardPoints,int cardPoints_dealer) {
		if(cardPoints>=22) {
			System.out.println("----Burst!!!----");
			System.out.println("----あなたの負けです----");
			result="lose";
		} else if(cardPoints==21){
			System.out.println("☆★☆★☆★☆★BLACKJACK!!!☆★☆★☆★☆★");
			System.out.println("----あなたの勝利です----");
			result="win";
		} else if(cardPoints_dealer>=22) {
			System.out.println("----DEALER'S Burst!!!----");
			System.out.println("ディーラーの点数が"+cardPoints_dealer+"点なのでバーストしました");
			System.out.println("----あなたの勝ちです----");
			result="win";
		} else if(cardPoints_dealer==21){
			System.out.println("☆★☆★☆★☆★DEALER'S BLACKJACK!!!☆★☆★☆★☆★");
			System.out.println("----あなたの負けです----");
			result="lose";
		}
	}		//judge method締め
}			//class締め
