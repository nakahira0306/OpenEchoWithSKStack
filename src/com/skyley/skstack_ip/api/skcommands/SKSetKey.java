package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.SKUtil;

/**
* SKSETKEYコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKSetKey extends SKCommand {
	/** キーインデックス */
	private short index;
	/** 128bit NWK暗号化キー（ASCII32文字） */
	private String key;

	/**
	 * コンストラクタ
	 * @param index キーインデックス
	 * @param key 128bit NWK暗号化キー（ASCII32文字）
	 */
	public SKSetKey(short index, String key) {
		this.index = index;
		this.key = key;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		if (index < 0 || index > 0xFF) {
			return false;
		}

		return SKUtil.isValidAsciiString(key, 32);
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		int idx = Short.toUnsignedInt(index);
		commandString = "SKSETKEY " + Integer.toHexString(idx) + " " + key + "\r\n";
	}

}
