package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.SKUtil;

/**
* SKSENDコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKSend extends SKCommand {
	/** ハンドル番号 */
	private byte handle;
	/** 送信データ */
	private String data;

	/**
	 * コンストラクタ
	 * @param handle ハンドル番号
	 * @param data 送信データ
	 */
	public SKSend(byte handle, String data) {
		this.handle = handle;
		this.data = data;
	}
	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		if (handle < 1 || handle > 6) {
			return false;
		}

		if (data == null || data == "") {
			return false;
		}

		if (data.length() > 0xFFFF) {
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
		String dataLenString;

		dataLenString = SKUtil.toPaddingHexString(data.length(), 4);
		commandString = "SKSEND " + Integer.toHexString(handle) + " " + dataLenString + " " + data + "\r\n";
	}

}
