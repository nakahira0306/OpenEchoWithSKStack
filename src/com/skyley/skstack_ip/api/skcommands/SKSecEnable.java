package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.SKUtil;

/**
* SKSECENABLEコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKSecEnable extends SKCommand {
	/** セキュリティ適用/無効フラグ */
	private int mode;
	/** 対象IPv6アドレス */
	private String ip6Address;
	/** 対象IPv6アドレスに対応する64bitアドレス */
	private String macAddress;

	/**
	 * コンストラクタ
	 * @param mode セキュリティ適用/無効フラグ
	 * @param ip6Address 対象IPv6アドレス
	 */
	public SKSecEnable(int mode, String ip6Address) {
		this.mode = mode;
		this.ip6Address = ip6Address;
	}

	/**
	 * コンストラクタ
	 * @param mode セキュリティ適用/無効フラグ
	 * @param ip6Address 対象IPv6アドレス
	 * @param macAddress 対象IPv6アドレスに対応する64bitアドレス
	 */
	public SKSecEnable(int mode, String ip6Address, String macAddress) {
		this.mode = mode;
		this.ip6Address = ip6Address;
		this.macAddress = macAddress;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		if (mode != 0 && mode !=1) {
			return false;
		}

		if (!SKUtil.isValidIP6Address(ip6Address)) {
			return false;
		}

		if (mode == 0 && macAddress == null) {
			macAddress = "1234567890123456"; // dummy
		}

		return SKUtil.isValidLongAddress(macAddress);
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKSECENABLE " + Integer.toString(mode, 16) + " " + ip6Address + " " + macAddress + "\r\n";
	}

}
