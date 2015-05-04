package com.skyley.skstack_ip.api.skcommands;


/**
* SKUDPPORTコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKUdpPort extends SKCommand {
	/** UDPハンドル番号 */
	private byte handle;
	/** 待ち受けポート番号 */
	private int port;

	/**
	 * コンストラクタ
	 * @param handle UDPハンドル番号
	 * @param port 待ち受けポート番号
	 */
	public SKUdpPort(byte handle, int port) {
		this.handle = handle;
		this.port = port;
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
		int hdl = Byte.toUnsignedInt(handle);
		commandString = "SKUDPPORT " + Integer.toHexString(hdl) + " " + Integer.toHexString(port) + "\r\n";
	}

}
