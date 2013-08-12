/**
 * 四則演算
 */
package moscowmule2240.java008;

/**
 * 分数の計算を行います。
 * 
 * @author moscowmule2240
 */
public class Rational {
	/**
	 * 分子。
	 */
	private int numerator;

	/**
	 * 分母。
	 */
	private int denominator;

	/**
	 * コンストラクタ。
	 * 
	 * @param numerator
	 *            分子
	 * @param denominator
	 *            分母
	 */
	public Rational(int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("分母に0は指定できません。");
		}

		this.numerator = numerator;
		this.denominator = denominator;
	}

	/**
	 * 約分して返します。
	 * 
	 * @return 約分した結果
	 */
	public Rational normal() {
		int divisor = this.gcd(this.numerator, this.denominator);
		return new Rational(this.numerator / divisor, this.denominator / divisor);
	}

	/**
	 * 最大公約数を求める。（ユークリッドの互除法）
	 * 
	 * @param value1
	 *            比較数値１
	 * @param value2
	 *            比較数値２
	 * @return 最大公約数
	 */
	private int gcd(int value1, int value2) {
		if (value2 == 0) {
			return value1;
		}
		return this.gcd(value2, value1 % value2);
	}

	/**
	 * 現在の分数と指定した分数の加算結果を返します。
	 * 
	 * @param rational
	 *            指定した分数
	 * @return 加算結果
	 */
	public Rational add(Rational rational) {
		return new Rational((this.numerator * rational.denominator) + (rational.numerator * this.denominator), this.denominator * rational.denominator).normal();
	}

	/**
	 * 現在の分数と指定した分数の減算結果を返します。
	 * 
	 * @param rational
	 *            指定した分数
	 * @return 減算結果
	 */
	public Rational subtract(Rational rational) {
		return new Rational((this.numerator * rational.denominator) - (rational.numerator * this.denominator), this.denominator * rational.denominator).normal();
	}

	/**
	 * 現在の分数と指定した分数の積算結果を返します。
	 * 
	 * @param rational
	 *            指定した分数
	 * @return 積算結果
	 */
	public Rational multiply(Rational rational) {
		return new Rational(this.numerator * rational.numerator, this.denominator * rational.denominator).normal();
	}

	/**
	 * 現在の分数と指定した分数の除算結果を返します。
	 * 
	 * @param rational
	 *            指定した分数
	 * @return 除算結果
	 */
	public Rational divide(Rational rational) {
		return new Rational(this.numerator * rational.denominator, this.denominator * rational.numerator).normal();
	}

	/**
	 * 分数を表す文字列を返します。
	 * 
	 * @return 分数を表す文字列
	 */
	public String rationalValue() {
		return this.numerator + "/" + this.denominator;
	}

	/**
	 * 計算結果（少数）を返します。
	 * 
	 * @return 計算結果（少数）
	 */
	public double doubleValue() {
		return (double) this.numerator / this.denominator;
	}
}
