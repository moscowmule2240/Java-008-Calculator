/**
 * 四則演算
 */
package moscowmule2240.java008;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 入力内容を逆ポーランド記法に変換し、計算結果を表示します。
 * 
 * @author moscowmule2240
 */
public class Calculator {

	/**
	 * エントリーポイント。
	 * 
	 * @param args
	 *            １番目：オプション(-d) ２番目：数式
	 */
	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("数式を入力してください。");
			return;
		}

		boolean isDouble;
		String line;

		if (args.length == 1) {
			isDouble = false;
			line = args[0];
		} else if ((args.length == 2) && "-d".equals(args[0])) {
			isDouble = true;
			line = args[1];
		} else {
			System.out.println("オプションが無効です。");
			return;
		}

		try {
			// 解析
			Deque<String> deque = Analysis.analysis(line);

			// 計算
			Rational rational = Calculator.calculation(deque);

			if (isDouble) {
				System.out.println(rational.doubleValue());
			} else {
				System.out.println(rational.rationalValue());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 逆ポーランド記法を計算します。
	 * 
	 * @param deque
	 *            逆ポーランド記法に変換した数式
	 * @return 計算結果（分数）
	 */
	private static Rational calculation(Deque<String> deque) {

		Deque<Rational> tempDeque = new LinkedList<>();

		for (String value : deque) {

			Rational rational1;
			Rational rational2;

			switch (value) {
			case "+":
				rational1 = tempDeque.removeLast();
				rational2 = tempDeque.removeLast();
				tempDeque.addLast(rational2.add(rational1));
				break;
			case "-":
				rational1 = tempDeque.removeLast();
				rational2 = tempDeque.removeLast();
				tempDeque.addLast(rational2.subtract(rational1));
				break;
			case "*":
				rational1 = tempDeque.removeLast();
				rational2 = tempDeque.removeLast();
				tempDeque.addLast(rational2.multiply(rational1));
				break;
			case "/":
				rational1 = tempDeque.removeLast();
				rational2 = tempDeque.removeLast();
				tempDeque.addLast(rational2.divide(rational1));
				break;
			default:
				tempDeque.addLast(new Rational(Integer.valueOf(value), 1));
			}
		}

		return tempDeque.getLast();
	}
}
