package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.SKUtil;

/**
* SKREGDEVコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKRegDev extends SKCommand {
	/** 登録対象のIPv6アドレス */
	private String ip6Address;

	/**
	 * コンストラクタ
	 * @param ip6Address 登録対象のIPv6アドレス
	 */
	public SKRegDev(String ip6Address) {
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
		commandString = "SKREGDEV " + ip6Address + "\r\n";
	}

}
