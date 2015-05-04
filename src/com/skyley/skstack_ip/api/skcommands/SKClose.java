package com.skyley.skstack_ip.api.skcommands;

/**
 * SKCLOSEコマンドに対応したクラス、SKCommandを継承
 * @author Skyley Networks, Inc.
 * @version 0.1
 *
*/
public class SKClose extends SKCommand {
	/** ハンドル番号 */
	private byte handle;

	/**
	 * コンストラクタ
	 * @param handle ハンドル番号
	 */
	public SKClose(byte handle) {
		this.handle = handle;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		if (handle >= (byte)1 && handle <= (byte)6) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKCLOSE " + Integer.toHexString(handle) + "\r\n";
	}

}
