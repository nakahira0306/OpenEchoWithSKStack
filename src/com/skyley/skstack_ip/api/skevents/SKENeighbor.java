package com.skyley.skstack_ip.api.skevents;


/**
 * ENEIGHBORイベントに対応したクラス、SKEventを実装
 * @author Skyley Networks, Inc.
 * @version 0.1
*/
public class SKENeighbor implements SKEvent {
	/** ネイバーキャッシュに登録されているIPv6アドレス */
	private String ip6Address;
	/** IPv6アドレスに対応する64bitアドレス（16進表現16文字） */
	private String longAddress;
	/** ショートアドレス */
	private int shortAddress;

	/**
	 * IPv6アドレスを取得
	 * @return IPv6アドレス
	 */
	public String getIP6Address() {
		return ip6Address;
	}

	/**
	 * 64bitアドレスを取得
	 * @return 64bitアドレス
	 */
	public String getLongAddress() {
		return longAddress;
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
		if (raw == null) {
			return false;
		}

		String[] ary = raw.split(" ");

		try {
			if (ary.length == 3) {
				ip6Address = ary[0];
				longAddress = ary[1];
				shortAddress = Integer.parseInt(ary[2], 16);
				return true;
			}
			else {
				return false;
			}
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

}
