package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.SKUtil;

/**
* SKPINGコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKPing extends SKCommand {
	/** 送信先IPv6アドレス */
	private String dstIP6Address;


	/**
	 * コンストラクタ
	 * @param dstIP6Address 送信先IPv6アドレス
	 */
	public SKPing(String dstIP6Address) {
		this.dstIP6Address = dstIP6Address;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		return SKUtil.isValidIP6Address(dstIP6Address);
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKPING " + dstIP6Address + "\r\n";
	}

}
