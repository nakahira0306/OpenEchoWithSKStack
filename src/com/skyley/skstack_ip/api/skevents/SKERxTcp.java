package com.skyley.skstack_ip.api.skevents;


/**
* ERXTCPイベントに対応したクラス、SKEventを実装
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKERxTcp implements SKEvent {
	/** 送信元IPv6アドレス */
	private String senderIP6Address;
	/** 送信元ポート番号 */
	private int rport;
	/** 送信先ポート番号 */
	private int lport;
	/** 受信データ長 */
	private int dataLength;
	/** 受信データ */
	private String data;

	/**
	 * 送信元IPv6アドレスを取得
	 * @return 送信元IPv6アドレス
	 */
	public String getSenderIP6Address() {
		return senderIP6Address;
	}

	/**
	 * 送信元ポート番号を取得
	 * @return 送信元ポート番号
	 */
	public int getRPort() {
		return rport;
	}

	/**
	 * 送信先ポート番号を取得
	 * @return 送信先ポート番号
	 */
	public int getLPort() {
		return lport;
	}

	/**
	 * 受信データ長を取得
	 * @return 受信データ長
	 */
	public int getDataLength() {
		return dataLength;
	}

	/**
	 * 受信データを取得
	 * @return 受信データ
	 */
	public String getData() {
		return data;
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

		String[] ary = raw.split(" ");
		if (ary.length != 6) {
			return false;
		}

		try {
			senderIP6Address = ary[1];
			rport = Integer.parseInt(ary[2], 16);
			lport = Integer.parseInt(ary[3], 16);
			dataLength = Integer.parseInt(ary[4], 16);
			data = ary[5];
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

}
