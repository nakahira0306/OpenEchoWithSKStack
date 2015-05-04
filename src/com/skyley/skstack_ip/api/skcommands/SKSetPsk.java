package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.SKUtil;

/**
* SKSETPSKコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKSetPsk extends SKCommand {
	/** PSKキー長 */
	private int len;
	/** PSKバイト列（32文字） */
	private String key;

	/**
	 * コンストラクタ
	 * @param key PSKバイト列（32文字）
	 */
	public SKSetPsk(String key) {
		this.len = 0x10;
		this.key = key;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		return SKUtil.isValidAsciiString(key, 32);
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKSETPSK " + Integer.toHexString(len) + " " + key + "\r\n";
	}

}
