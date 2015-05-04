package com.skyley.skstack_ip.api.skenums;

/**
* "EVENT"イベントのイベント番号を列挙
* @author Skyley Networks, Inc.
* @version 0.1
*/
public enum SKEventNumber {
	/** NSを受信した */
	NS_RECEIVED(0x01),
	/** NAを受信した */
	NA_RECEIVED(0x02),
	/** Echo Requestを受信した */
	ECHO_REQUEST_RECEIVED(0x05),
	/** EDスキャンが完了した */
	ED_SCAN_DONE(0x1F),
	/** Beaconを受信した */
	BEACON_RECEIVED(0x20),
	/** UDP送信処理が完了した */
	UDP_TX_DONE(0x21),
	/** アクティブスキャンが完了した */
	ACTIVE_SCAN_DONE(0x22),
	//* PANA接続が完了しなかった */
	PANA_CONNECT_FAIL(0x24),
	/** PANA接続が完了した */
	PANA_CONNECT_DONE(0x25),
	/** PANAセッションの終了要求を受信した */
	PANA_SESSION_CLOSE_REQUEST_RECEIVED(0x26),
	/** PANAセッションの終了に成功した */
	PANA_SESSION_CLOSE_DONE(0x27),
	/** PANAセッションの終了要求がタイムアウトした（セッションは終了） */
	PANA_SESSION_CLOSE_TIMEOUT(0x28),
	/** PANAセッションのライフタイムが期限切れになった */
	PANA_SESSION_LIFETIME_EXPIRED(0x29),
	/** 送信総和時間の制限が発動した */
	TX_LIMIT_START(0x32),
	/** 送信総和時間の制限が解除された */
	TX_LIMIT_END(0x33);

	/** イベント番号 */
	private short number;

	/**
	 * コンストラクタ
	 * @param number イベント番号
	 */
	private SKEventNumber(int number) {
		this.number = (short)number;
	}

	/**
	 * イベント番号を取得
	 * @return イベント番号
	 */
	public short getNumber() {
		return number;
	}

	/**
	 * イベント番号からイベント名を取得
	 * @param number イベント番号
	 * @return numberに対応するイベント名
	 */
	public static String getEventName(short number) {
		for (SKEventNumber en : SKEventNumber.values()) {
			if (en.number == number) {
				return en.name();
			}
		}

		return "";
	}

}
