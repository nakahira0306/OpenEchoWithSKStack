package com.skyley.skstack_ip.api.skevents;

import com.skyley.skstack_ip.api.skenums.SKDeviceModel;

/**
* ERXUDPイベントに対応したクラス、SKEventを実装
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKERxUdp implements SKEvent {
	/** デバイス機種 */
	private SKDeviceModel model;
	/** 送信元IPv6アドレス */
	private String senderIP6Address;
	/** 送信先IPv6アドレス */
	private String destIP6Address;
	/** 送信元ポート番号 */
	private int rport;
	/** 送信先ポート番号 */
	private int lport;
	/** 送信元MAC層アドレス（16進表現16文字または4文字） */
	private String senderLLA;
	/** MACフレーム暗号化フラグ */
	private boolean isSecured;
	/** 受信RSSIレベル */
	private short rssi;
	/** 受信データ長 */
	private int dataLength;
	/** 受信データ */
	private String data;

	/**
	 * コンストラクタ
	 * @param model デバイス機種
	 */
	public SKERxUdp(SKDeviceModel model) {
		this.model = model;
	}

	/**
	 * 送信元IPv6アドレスを取得
	 * @return 送信元IPv6アドレス
	 */
	public String getSenderIP6Address() {
		return senderIP6Address;
	}

	/**
	 * 送信先IPv6アドレスを取得
	 * @return 送信先IPv6アドレス
	 */
	public String getDestIP6Address() {
		return destIP6Address;
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
	 * 送信元MAC層アドレスを取得
	 * @return 送信元MAC層アドレス（16進表現16文字または4文字）
	 */
	public String getSenderLLA() {
		return senderLLA;

	}

	/**
	 * MACフレーム暗号化フラグを取得
	 * @return MACフレーム暗号化フラグ
	 */
	public boolean isSecured() {
		return isSecured;
	}

	/**
	 * 受信RSSIレベルを取得
	 * @return 受信RSSIレベル
	 */
	public short getRSSI() {
		return rssi;
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

		try {
			String[] ary = raw.split(" ");
			if (model == SKDeviceModel.GENERAL && ary.length == 9) {
				senderIP6Address = ary[1];
				destIP6Address = ary[2];
				rport = Integer.parseInt(ary[3], 16);
				lport = Integer.parseInt(ary[4], 16);
				senderLLA = ary[5];
				if (ary[6].compareTo("1") == 0) {
					isSecured = true;
				}
				else {
					isSecured = false;
				}
				dataLength = Integer.parseInt(ary[7], 16);
				data = ary[8];
			}
			else {
				return false;
			}

			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

}
