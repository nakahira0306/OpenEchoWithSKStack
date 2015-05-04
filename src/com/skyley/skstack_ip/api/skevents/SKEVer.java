package com.skyley.skstack_ip.api.skevents;


/**
* EVERイベントに対応したクラス、SKEventを実装
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKEVer implements SKEvent {
	/** バージョン番号 */
	private String version;

	/**
	 * バージョン番号を取得
	 * @return バージョン番号
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * 受信文字列を解析、パラメータを格納
	 */
	@Override
	public boolean parse(String raw) {
		// TODO 自動生成されたメソッド・スタブ
		if (raw == null) {
			return false;
		}

		if(raw.indexOf("EVER ") == 0){
			String[] ary = raw.split(" ");
			if(ary.length == 2) {
				version = ary[1];
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
