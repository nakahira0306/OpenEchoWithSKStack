package com.skyley.skstack_ip.api.skenums;

/**
* "Exxxx"系イベントの種類を列挙
* @author Skyley Networks, Inc.
* @version 0.1
*/
public enum SKEventType {
	ESREG(true),
	EINFO(true),
	ERXUDP(false),
	ERXTCP(false),
	EPONG(true),
	ETCP(false),
	EVER(true),
	EADDR(true),
	ENEIGHBOR(true),
	EHANDLE(true),
	EPANDESC(false),
	EEDSCAN(false),
	EHANLDE(true),
	EVENT(false);

	/**
	 * コマンド応答フラグ<br>
	 * コマンド応答として扱う場合はtrue, リスナーに通知する場合はfalse
	 */
	private boolean response;

	/**
	 * コンストラクタ
	 * @param response コマンド応答フラグ
	 */
	private SKEventType(boolean response) {
		this.response = response;
	}

	/**
	 * コマンド応答フラグを取得
	 * @return コマンド応答フラグ
	 */
	public boolean isResponse() {
		return response;
	}
}
