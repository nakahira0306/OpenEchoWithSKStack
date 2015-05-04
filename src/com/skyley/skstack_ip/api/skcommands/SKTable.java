package com.skyley.skstack_ip.api.skcommands;


/**
* SKTABLEコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKTable extends SKCommand {
	/** テーブル種類 */
	private byte mode;

	/**
	 * コンストラクタ
	 * @param mode テーブル種類
	 */
	public SKTable(byte mode) {
		this.mode = mode;
	}

	/** 引数チェック */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		if (mode == 1 || mode == 2 || mode == 0x0F) {
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
		commandString = "SKTABLE " + Integer.toHexString(mode) + "\r\n";
	}

}
