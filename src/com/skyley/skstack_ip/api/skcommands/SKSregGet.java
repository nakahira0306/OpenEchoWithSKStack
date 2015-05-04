package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.skenums.SKRegister;

/**
* "SKSREG {SREG}"コマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKSregGet extends SKCommand {
	/** 仮想レジスタ */
	private SKRegister register;

	/**
	 * コンストラクタ
	 * @param register 仮想レジスタ
	 */
	public SKSregGet(SKRegister register) {
		this.register = register;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKSREG " + register.toString() + "\r\n";
	}

}
