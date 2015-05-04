package com.skyley.skstack_ip.api.skcommands;


/**
* SKTCPPORTコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKTcpPort extends SKCommand {
	/** 設定対象ポートのインデックス */
	private byte index;
	/** 待ち受けポート番号 */
	private int port;

	/**
	 * コンストラクタ
	 * @param index 設定対象ポートのインデックス
	 * @param port 待ち受けポート番号
	 */
	public SKTcpPort(byte index, int port) {
		this.index = index;
		this.port = port;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		if (index < 1 || index > 4) {
			return false;
		}

		if (port < 0 || port > 0xFFFF) {
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
		commandString = "SKTCPPORT " + Integer.toHexString(index) + " " + Integer.toHexString(port) + "\r\n";
	}

}
