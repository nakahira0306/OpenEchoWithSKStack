package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.SKUtil;

/**
* SKSETRBIDコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKSetRbid extends SKCommand {
	/** ID（ASCII32文字） */
	private String id;

	/**
	 * コンストラクタ
	 * @param id ID（ASCII32文字)
	 */
	public SKSetRbid(String id) {
		this.id = id;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		return SKUtil.isValidAsciiString(id, 32);
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKSETRBID " + id + "\r\n";
	}

}
