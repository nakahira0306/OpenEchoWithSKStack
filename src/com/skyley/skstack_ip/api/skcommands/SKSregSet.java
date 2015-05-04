package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.skenums.SKRegister;

/**
* "SKSERG {SREG} {VAL}"コマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKSregSet extends SKCommand {
	/** 仮想レジスタ */
	private SKRegister register;
	/** 設定値 */
	private String value;

	/**
	 * コンストラクタ
	 * @param register 仮想レジスタ
	 * @param value 設定値
	 */
	public SKSregSet(SKRegister register, String value) {
		this.register = register;
		this.value = value;
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
		commandString = "SKSREG " + register.toString() + " " + value + "\r\n";
	}

}
