package com.skyley.skstack_ip.api.skevents;

/**
* ESREGイベントに対応したクラス、SKEventを実装
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKESreg implements SKEvent {
	/** 仮想レジスタの値 */
	private String value;

	/**
	 * 仮想レジスタの値を取得
	 * @return 仮想レジスタの値
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 受信文字列を解析、パラメータを格納
	 */
	@Override
	public boolean parse(String raw) {
		// TODO 自動生成されたメソッド・スタブ
		if(raw == null) {
			return false;
		}

		if(raw.indexOf("ESREG ") == 0){
			String[] ary = raw.split(" ");
			if(ary.length == 2) {
				value = ary[1];
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

}
