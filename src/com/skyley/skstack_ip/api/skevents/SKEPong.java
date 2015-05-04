package com.skyley.skstack_ip.api.skevents;


/**
* EPONGイベントに対応したクラス、SKEventを実装
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKEPong implements SKEvent {
	/** 送信元IPv6アドレス */
	private String senderAddress;

	/**
	 * 送信元IPv6アドレスを取得
	 * @return 送信元IPv6アドレス
	 */
	public String getSenderAddress(){
		return senderAddress;
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

		if (raw.indexOf("EPONG ") == 0) {
			String[] ary = raw.split(" ");
			if(ary.length == 2) {
				senderAddress = ary[1];
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
