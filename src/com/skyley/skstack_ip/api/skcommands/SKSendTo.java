package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.SKUtil;
import com.skyley.skstack_ip.api.skenums.SKSecOption;

/**
* SKSENDTOコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKSendTo extends SKCommand {
	/** UDPハンドル */
	private byte handle;
	/** 宛先IPv6アドレス */
	private String ip6Address;
	/** 宛先ポート番号 */
	private int port;
	/** 暗号化オプション */
	private SKSecOption sec;
	/** 送信データ */
	private String data;

	/**
	 * コンストラクタ
	 * @param handle 送信元UDPハンドル
	 * @param ip6Address 宛先IPv6アドレス
	 * @param port 宛先ポート番号
	 * @param sec 暗号化オプション
	 * @param data 送信データ
	 */
	public SKSendTo(byte handle, String ip6Address, int port, SKSecOption sec, String data) {
		this.handle = handle;
		this.ip6Address = ip6Address;
		this.port = port;
		this.sec = sec;
		this.data = data;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		if (!SKUtil.isValidIP6Address(ip6Address)) {
			return false;
		}

		if (handle < 1 || handle > 6) {
			return false;
		}

		if (port < 0 || port > 0xFFFF) {
			return false;
		}

		if (data == null || data == "") {
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
		String portString, lenString;

		portString = SKUtil.toPaddingHexString(port, 4);
		lenString = SKUtil.toPaddingHexString(data.length(), 4);

		StringBuilder sb = new StringBuilder();
		sb.append("SKSENDTO ");
		sb.append(Integer.toHexString(handle));
		sb.append(" ");
		sb.append(ip6Address);
		sb.append(" ");
		sb.append(portString);
		sb.append(" ");
		sb.append(sec.toString());
		sb.append(" ");
		sb.append(lenString);
		sb.append(" ");
		sb.append(data);
		sb.append("\r\n");
		commandString = sb.toString();
	}

}
