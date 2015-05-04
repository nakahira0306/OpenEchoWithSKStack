package com.skyley.skstack_ip.api.skenums;

/**
* スキャンモードを列挙
* @author Skyley Networks, Inc.
* @version 0.1
*/
public enum SKScanMode {
	/** EDスキャン */
	ED_SCAN("0"),
	/** アクティブスキャン、IEあり */
	ACTIVE_SCAN_WITH_IE("2"),
	/** アクティブスキャン、IEなし */
	ACTIVE_SCAN_WITHOUT_IE("3");

	/** スキャンモードのraw value */
	private String mode;

	/**
	 * コンストラクタ
	 * @param mode スキャンモードのraw value
	 */
	private SKScanMode(String mode) {
		this.mode = mode;
	}

	/**
	 * スキャンモードのraw valueを取得
	 */
	public String toString() {
		return mode;
	}
}
