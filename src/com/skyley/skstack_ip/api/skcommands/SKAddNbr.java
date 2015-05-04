package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.SKUtil;

/**
 * SKADDNBRコマンドに対応したクラス、SKCommandを継承
 * @author Skyley Networks, Inc.
 * @version 0.1
 *
*/
public class SKAddNbr extends SKCommand {
	/** 登録対象のIPv6アドレス */
	private String ip6Address;
	/** 登録対象のIPv6アドレスに対応した64bitアドレス（16進表現16文字) */
	private String macAddress;

	/**
	 * コンストラクタ
	 * @param ip6Address 登録対象のIPv6アドレス
	 * @param macAddress 登録対象のIPv6アドレスに対応した64bitアドレス（16進表現16文字）
	 */
	public SKAddNbr(String ip6Address, String macAddress) {
		this.ip6Address = ip6Address;
		this.macAddress = macAddress;
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

		return SKUtil.isValidLongAddress(macAddress);
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKADDNBR " + ip6Address + " " + macAddress + "\r\n";
	}

}
