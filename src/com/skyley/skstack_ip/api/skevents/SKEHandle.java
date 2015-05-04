package com.skyley.skstack_ip.api.skevents;


/**
 * EHANDLEイベントに対応したクラス、SKEventを実装
 * @author Skyley Networks, Inc.
 * @version 0.1
*/
public class SKEHandle implements SKEvent {
	/** ハンドル番号 */
	private byte handle;
	/** 接続先IPv6アドレス */
	private String ip6Address
	;/** 接続先ポート番号 */
	private int rport;
	/** 接続元ポート番号 */
	private int lport;

	/**
	 * ハンドル番号を取得
	 * @return ハンドル番号
	 */
	public byte getHandle() {
		return handle;
	}

	/**
	 * 接続先IPv6アドレスを取得
	 * @return 接続先IPv6アドレス
	 */
	public String getIP6Address() {
		return ip6Address;
	}

	/**
	 * 接続先ポート番号を取得
	 * @return 接続先ポート番号
	 */
	public int getRPort() {
		return rport;
	}

	/**
	 * 接続元ポート番号を取得
	 * @return 接続元ポート番号
	 */
	public int getLPort() {
		return lport;
	}

	/**
	 * 受信文字列を解析、パラメータを格納
	 */
	@Override
	public boolean parse(String raw) {
		// TODO 自動生成されたメソッド・スタブ
		if (raw == null) {
			return false;
		}

		String[] ary = raw.split(" ");
		if (ary.length != 4) {
			return false;
		}

		try {
			handle = Byte.parseByte(ary[0], 16);
			ip6Address = ary[1];
			rport = Integer.parseInt(ary[2], 16);
			lport = Integer.parseInt(ary[3], 16);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

}
