package BaseSystem.Animation;
/** アニメーションタイマークラス
 * アニメーション用のタイマーで，
 * 非リアルタイムアニメーション用．
 * 更新処理が必要 
 * 最小値，最大値，差分値を決めて，refreshメソッドを呼び出すごとに
 * 最小値から最大値，あるいは最大値から最小値に差分値ずつ近づき，
 * それぞれの数値を超えた時点でストップする
 * ループがtrueだと一度ストップしてもまた最初からになる
 * 最大値と最小値を逆にするとエラーフラグがtrueになる
 * また，ここでは時間，timeと書いてあるが，正確にはヴァーチャルタイムであり，
 * リアルタイムを使用してるわけではないので間違えないでください*/
public class Animationtime {

	private boolean loop;
	private double time;
	private double diftime;
	private double min;
	private double max;
	private boolean pdcheck;
	private boolean error;
	private boolean fin;
	private boolean looped;
	/** アニメーションコンストラクタ
	 * @param t　double 差分値
	 * @param min double 最小値
	 * @param max double 最大値
	 * @param loop boolean ループするかどうか
	 * */
	public Animationtime(double t,double min,double max,boolean loop) {
		diftime = t;
		this.loop = loop;
		this.setLooped(false);
		this.min = min;
		this.max = max;
		if(min >= max)
			error = true;
		if(diftime < 0.0){
			time = max;
			pdcheck = false;
		}else if(diftime > 0.0){
			time = min;
			pdcheck = true;
		}else{
			error = true;
		}
	}
	/**更新処理メソッド
	 * このメソッドを呼び出すことでこのアニメーションタイマーを進めることができる
	 * 毎回呼び出すところなどに書いてください
	 *  */
	public void refresh(){
		if(error || fin)
			return;
		time+=diftime;
		if(pdcheck){
			if(time > this.max)
				if(loop){
					setLooped(true);
					time = this.min;
				}else{
					fin = true;
				}
		}else{
			if(time < this.min)
				if(loop){
					setLooped(true);
					time = this.max;
				}else{
					fin = true;
				}
		}
	}
	/**タイム取得メソッド
	 * 現在のヴァータルタイムを取得します
	 *  */
	public double getTime() {
		return time;
	}
	/**停止メソッド
	 * このアニメーションタイマーを止めます
	 *  */
	public void stop(){
		fin = true;
	}
	/** 停止確認メソッド
	 * このアニメーションタイマーが止まってるかどうかを確認します
	 * @return boolean 止まっていればtrue
	 * */
	public boolean isStop(){
		return fin;
	}
	/** エラー確認メソッド
	 * エラーかどうかを確認します
	 * @return boolean エラーならtrue
	 * */
	public boolean isError() {
		return error;
	}
	/** ループ後確認メソッド
	 *  １度ループしたかどうかを確認します
	 *  また，ループを確認したら，ループ確認を初期化します
	 *  @return boolean １度でもループしていればtrue
	 *  */
	public boolean isLooped() {
		boolean l = looped;
		if(l){
			looped = false;
		}
		return l;
	}
	/** ループ確認変更メソッド
	 * ループの確認を強制的に変更します
	 * @param looped boolean 変更するboolean
	 *  */
	public void setLooped(boolean looped) {
		this.looped = looped;
	}
}
