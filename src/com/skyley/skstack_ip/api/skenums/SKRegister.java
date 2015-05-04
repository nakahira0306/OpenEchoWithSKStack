package com.skyley.skstack_ip.api.skenums;

/**
 * 仮想レジスタを列挙
 * @author Skyley Netowkrs, Inc.
 * @version 0.1
 */
public enum SKRegister {
	/** IEEE 64bit MACアドレス */
	LONG_ADDRESS("S01"),
	/** 論理チャンネル番号 */
	CHANNEL("S02"),
	/** PAN ID */
	PAN_ID("S03"),
	/** MAC層セキュリティフレームカウンタ(Read only) */
	SEC_FRAME_COUNTER("S07"),
	/** Paring ID */
	PARING_ID("S0A"),
	/** ビーコン応答制御フラグ */
	BEACON_RESPONSE("S15"),
	/** PANAセッションライフタイム（単位 秒） */
	PANA_SESSION_LIFETIME("S16"),
	/** PANA自動再認証フラグ */
	PANA_AUTO_AUTH("S17"),
	/** MAC層ブロードキャストに対するセキュリティ制御フラグ */
	MAC_BROADCAST_SEC("SA0"),
	/** ICMPメッセージ受信処理制御フラグ */
	ICMP_RX("SA1"),
	/** 送信出力レベル */
	TX_POWER_LEVEL("SA2"),
	/** Ack待ち時間（単位 10usec） */
	ACK_WAITING_TIME("SA5"),
	/** データホワイトニング制御フラグ */
	DATA_WHITENING("SA7"),
	/** 低速データレートフラグ */
	LOW_DATA_RATE_FLAG("SA8"),
	/** CCCA閾値（高データレート） */
	CCA_THRESHOLD_HIGH_RATE("SF7"),
	/** CCCA閾値（低データレート） */
	CCA_THRESHOLD_LOW_RATE("SF8"),
	/** DSN多重チェック制御フラグ */
	DSN_MULTIPLE_CHECK("SF9"),
	/** 送信時間制限の制御フラグ */
	TX_LIMIT("SFA"),
	/** 送信時間制限中フラグ(Read only) */
	TX_LIMIT_WORKING("SFB"),
	/** 無線送信の積算時間（単位 ミリ秒、Read only） */
	CUMULARIVE_TX_TIME("SFD"),
	/** エコーバックフラグ */
	COMMAND_ECHO_BACK("SFE"),
	/** オートロードフラグ */
	AUTO_LOAD("SFF");

	/** レジスタ番号、Sで始まる文字列 */
	private String regNumber;

	/**
	 * コンストラクタ
	 * @param regNumber レジスタ番号、Sで始まる文字列
	 */
	private SKRegister(String regNumber) {
		this.regNumber =regNumber;
	}

	/**
	 * レジスタ番号を返す
	 */
	public String toString() {
		return regNumber;
	}
}
