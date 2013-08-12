/**
 * 四則演算
 */
package moscowmule2240.java008;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 入力内容を逆ポーランド記法に変換します。
 * 
 * @author moscowmule2240
 */
public class Analysis {

	/**
	 * 優先順位
	 */
	private static final List<String> priority;

	static {
		priority = new ArrayList<>();
		Analysis.priority.add("*");
		Analysis.priority.add("/");
		Analysis.priority.add("+");
		Analysis.priority.add("-");
	}

	/**
	 * 入力内容を逆ポーランド記法に変換します。
	 * 
	 * @param line
	 *            入力内容
	 * @return 逆ポーランド記法に変換した式
	 * @throws IllegalArgumentException
	 *             数式以外が入力されていた場合
	 */
	public static Deque<String> analysis(String line) throws IllegalArgumentException {

		Deque<String> returnDeque = new LinkedList<>();
		Deque<String> tempDeque = new LinkedList<>();
		boolean isNumber = false;

		// １文字取り出す
		for (char charOfLine : line.toCharArray()) {

			switch (charOfLine) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				String string = String.valueOf(charOfLine);
				// 複数桁時は前の数値と結合
				if (isNumber) {
					returnDeque.addLast(returnDeque.removeLast() + string);
				} else {
					returnDeque.addLast(string);
				}
				isNumber = true;
				break;
			case '(':
				tempDeque.addLast("(");
				isNumber = false;
				break;
			case ')':
				// "("まで出力 "("と")"は消す
				while (!"(".equals(tempDeque.getLast())) {
					returnDeque.addLast(tempDeque.removeLast());
				}
				tempDeque.removeLast();
				isNumber = false;
				break;
			case '+':
			case '-':
			case '*':
			case '/':
				while (true) {
					// "("が無ければ通常処理
					if (tempDeque.isEmpty() || "(".equals(tempDeque.getLast())) {
						tempDeque.addLast(String.valueOf(charOfLine));
						break;
					}

					// 最後の演算子より優先順位が高い演算子
					int indexCurrent = Analysis.priority.indexOf(String.valueOf(charOfLine));
					int indexLast = Analysis.priority.indexOf(String.valueOf(tempDeque.getLast()));
					if (indexCurrent < indexLast) {
						tempDeque.addLast(String.valueOf(charOfLine));
						break;
					}

					// 最後の演算子より優先順位が低い演算子
					returnDeque.addLast(tempDeque.removeLast());
				}
				isNumber = false;
				break;
			case ' ':
				isNumber = false;
				break;
			default:
				throw new IllegalArgumentException("不正な値：" + charOfLine);
			}
		}

		// 空になるまで繰り返す
		while (!tempDeque.isEmpty()) {
			returnDeque.addLast(tempDeque.removeLast());
		}

		return returnDeque;
	}
}
