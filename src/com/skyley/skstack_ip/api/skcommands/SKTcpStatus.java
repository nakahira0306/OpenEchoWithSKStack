package com.skyley.skstack_ip.api.skcommands;

/**
* TCP処理ステータスを列挙
* @author Skyley Networks, Inc.
* @version 0.1
*/
public enum SKTcpStatus {
	/** 接続完了 */
	CONNECT_DONE((byte)1),
	/** 切断完了（接続失敗、データ送信タイムアウトによる切断を含む） */
	CLOSE_DONE((byte)3),
	/** 指定した送信元ポート番号が使用中 */
	LPORT_USED((byte)4),
	/** データ送信完了 */
	TX_DONE((byte)5);

	/** ステータスraw value */
	private byte status;

	/**
	 * コンストラクタ
	 * @param status ステータスraw value
	 */
	private SKTcpStatus(byte status) {
		this.status = status;
	}

	/**
	 * ステータスのraw valueを取得
	 * @return ステータスraw value
	 */
	public byte getStatus() {
		return status;
	}

	/**
	 * ステータスraw valueに対応する名前を取得
	 * @param value ステータスraw value
	 * @return valueに対応する名前
	 */
	public static String getName(byte value) {
		for (SKTcpStatus s : SKTcpStatus.values()) {
			if (s.status == value) {
				return s.name();
			}
		}

		return "";
	}
}
