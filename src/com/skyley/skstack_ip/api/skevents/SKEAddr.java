package com.skyley.skstack_ip.api.skevents;

import com.skyley.skstack_ip.api.SKUtil;

/**
 * EADDRイベントに対応したクラス、SKEventを実装
 * @author Skyley Networks, Inc.
 * @version 0.1
*/
public class SKEAddr implements SKEvent {
	/** IPv6アドレス */
	private String ip6Address;

	/**
	 * IPv6アドレスを取得
	 * @return IPv6アドレス
	 */
	public String getIP6Address() {
		return ip6Address;
	}

	/**
	 * 文字列を解析、パラメータを格納
	 */
	@Override
	public boolean parse(String raw) {
		// TODO 自動生成されたメソッド・スタブ
		if (raw == null) {
			return false;
		}

		String[] ary = raw.split(" ");
		if (ary.length != 1) {
			return false;
		}

		if (!SKUtil.isValidIP6Address(ary[0])) {
			return false;
		}

		ip6Address = ary[0];
		return true;
	}

}
