package com.skyley.skstack_ip.api.skevents;


/**
* EVENTイベントに対応したクラス、SKEventを実装
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKGeneralEvent implements SKEvent {
	/** イベント番号 */
	private byte eventNumber;
	/** 送信元IPv6アドレス */
	private String senderAddress;
	/** イベント固有パラメータ */
	private byte param;

	/**
	 * イベント番号を取得
	 * @return イベント番号
	 */
	public byte getEventNumber() {
		return eventNumber;
	}

	/**
	 * 送信元IPv6アドレスを取得
	 * @return 送信元IPv6アドレス
	 */
	public String getSenderAddress() {
		return senderAddress;
	}

	/**
	 * イベント固有パラメータを取得
	 * @return イベント固有パラメータ
	 */
	public byte getParam() {
		return param;
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

		try {
			String[] ary = raw.trim().split(" ");
			if (ary.length == 3 || ary.length == 4) {
				eventNumber = Byte.parseByte(ary[1], 16);
				senderAddress = ary[2];

				if (ary.length == 4) {
					param = Byte.parseByte(ary[3], 16);
				}

				return true;
			}
			else {
				return false;
			}
		}
		catch (Exception e) {
			return false;
		}
	}

}
