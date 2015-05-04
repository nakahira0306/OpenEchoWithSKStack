package com.skyley.skstack_ip.api.skevents;

/**
* ETCPイベントに対応したクラス、SKEventを実装
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKETcp implements SKEvent {
	/** TCP処理ステータス */
	private byte status;
	/** イベント対象となったハンドル番号 */
	private byte handle;
	/** 接続先/接続元IPv6アドレス */
	private String ip6Address;
	/** 相手側接続ポート番号 */
	private int rport;
	/** 時端末接続ポート番号 */
	private int lport;

	/**
	 * TCP処理ステータスを取得
	 * @return TCP処理ステータス
	 */
	public byte getStatus() {
		return status;
	}

	/**
	 * ハンドル番号を取得
	 * @return ハンドル番号
	 */
	public byte getHandle() {
		return handle;
	}

	/**
	 * 接続先/接続元IPv6アドレスを取得
	 * @return 接続先/接続元IPv6アドレス
	 */
	public String getIP6Address() {
		return ip6Address;
	}

	/**
	 * 相手側接続ポート番号を取得
	 * @return 相手側接続ポート番号
	 */
	public int getRPort() {
		return rport;
	}

	/**
	 * 自端末接続ポート番号を取得
	 * @return 自端末ポート番号
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
		try {
			if (ary.length == 3) {
				status = Byte.parseByte(ary[1], 16);
				handle = Byte.parseByte(ary[2], 16);
				return true;
			}
			else if (ary.length == 6) {
				status = Byte.parseByte(ary[1], 16);
				handle = Byte.parseByte(ary[2], 16);
				ip6Address = new String(ary[3]);
				rport = Integer.parseInt(ary[4], 16);
				lport = Integer.parseInt(ary[5], 16);
				return true;
			}
			else {
				return false;
			}
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

}
