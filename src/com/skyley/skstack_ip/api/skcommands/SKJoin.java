package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.SKUtil;

/**
* SKJOINコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKJoin extends SKCommand {
	/** 接続先Pv6アドレス */
	private String ip6Address;

	/**
	 * コンストラクタ
	 * @param ip6Address 接続先IPv6アドレス
	 */
	public SKJoin(String ip6Address) {
		this.ip6Address = ip6Address;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		return SKUtil.isValidIP6Address(ip6Address);
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKJOIN " + ip6Address + "\r\n";
	}

}
