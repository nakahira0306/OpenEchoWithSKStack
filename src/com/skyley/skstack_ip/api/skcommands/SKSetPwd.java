package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.SKUtil;

/**
* SKSETPWDコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKSetPwd extends SKCommand {
	/** パスワード長 */
	private int len;
	/** パスワード */
	private String password;

	/**
	 * コンストラクタ
	 * @param password パスワード
	 */
	public SKSetPwd(String password) {
		this.password = password;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		len = password.length();
		if (len <1 || len > 32) {
			return false;
		}

		return SKUtil.isValidAsciiString(password, len);
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKSETPWD " + Integer.toHexString(len) + " " + password + "\r\n";
	}

}
