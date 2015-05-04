package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.SKUtil;

/**
 * SKCONNECTコマンドに対応したクラス、SKCommandを継承
 * @author Skyley Networks, Inc.
 * @version 0.1
*/

public class SKConnect extends SKCommand {
	/** 接続先IPv6アドレス */
	private String ip6Address;
	/** 接続先ポート番号 */
	private int rport;
	/** 接続元ポート番号 */
	private int lport;

	/**
	 * コンストラクタ
	 * @param ip6Address 接続先IPv6アドレス
	 * @param rport 接続先ポート番号
	 * @param lport 接続元ポート番号
	 */
	public SKConnect(String ip6Address, int rport, int lport) {
		this.ip6Address = ip6Address;
		this.rport = rport;
		this.lport = lport;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		if (!SKUtil.isValidIP6Address(ip6Address)) {
			return false;
		}

		if (rport < 1 || rport > 0xFFFE) {
			return false;
		}

		if (lport < 1 || lport > 0xFFFE) {
			return false;
		}

		return true;
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKCONNECT " + ip6Address + " " + Integer.toHexString(rport) + " "
						+ Integer.toHexString(lport) + "\r\n";
	}

}
