package com.skyley.skstack_ip.api.skevents;


/**
 * EINFOイベントに対応したクラス、SKEventを実装
 * @author Skyley Networks, Inc.
 * @version 0.1
*/
public class SKEInfo implements SKEvent {
	/** デバイスのIPv6アドレス */
	private String ip6Address;
	/** デバイスのIEEE 64bit MACアドレス（16進表現16文字） */
	private String longAddress;
	/** 使用している周波数の論理チャンネル番号 */
	private byte channel;
	/** 現在のPAN ID */
	private int panID;
	/** 現在のショートアドレス */
	private int shortAddress;

	/**
	 * IPv6アドレスを取得
	 * @return IPv6アドレス
	 */
	public String getIP6Address() {
		return ip6Address;

	}

	/**
	 * IEEE 64bit MACアドレスを取得
	 * @return IEEE 64bit MACアドレス（16進表現16文字）
	 */
	public String getLongAddres() {
		return longAddress;
	}

	/**
	 * チャンネル番号を取得
	 * @return チャンネル番号
	 */
	public byte getChannel() {
		return channel;
	}

	/**
	 * PAN IDを取得
	 * @return PAN ID
	 */
	public int getPanID() {
		return panID;
	}

	/**
	 * ショートアドレスを取得
	 * @return ショートアドレス
	 */
	public int getShortAddress() {
		return shortAddress;
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

		if (raw.indexOf("EINFO ") == 0) {
			String[] ary = raw.split(" ");
			if (ary.length == 6) {
				ip6Address = ary[1];
				longAddress = ary[2];
				channel = Byte.parseByte(ary[3], 16);
				panID = Integer.parseInt(ary[4], 16);
				shortAddress = Integer.parseInt(ary[5], 16);
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
