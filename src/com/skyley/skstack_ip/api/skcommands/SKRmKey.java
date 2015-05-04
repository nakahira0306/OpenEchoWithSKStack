package com.skyley.skstack_ip.api.skcommands;


/**
* SKRMKEYコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKRmKey extends SKCommand {
	/** 削除対象のキーインデックス */
	private short index;


	/**
	 * コンストラクタ
	 * @param index 削除対象のキーインデックス
	 */
	public SKRmKey(short index) {
		this.index = index;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		if (index >= 0 || index <= 0xFF) {
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
		int idx = Short.toUnsignedInt(index);
		commandString = "SKRMKEY " + Integer.toHexString(idx) + "\r\n";
	}

}
