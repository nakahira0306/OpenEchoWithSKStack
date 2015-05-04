package com.skyley.skstack_ip.api.skenums;

/**
* UDP送信時の暗号化オプションを列挙
* @author Skyley Networks, Inc.
* @version 0.1
*/
public enum SKSecOption {
	/** 平文で送信 */
	PLAIN("0"),
	/** 送信先がセキュリティ有効で登録されていれば暗号化して送信、そうでなければ送信しない */
	SEC_OR_NO_TX("1"),
	/** 送信先がセキュリティ有効で登録されていれば暗号化して送信、そうでなければ平文で送信 */
	SEC_OR_PALIN("2");

	/** 暗号化オプションのraw value */
	private String optionString;

	/**
	 * コンストラクタ
	 * @param optionString 暗号化オプションのraw vallue
	 */
	private SKSecOption(String optionString) {
		this.optionString = optionString;
	}

	/**
	 * 暗号化オプションのraw stirngを取得
	 */
	public String toString() {
		return optionString;
	}
}
